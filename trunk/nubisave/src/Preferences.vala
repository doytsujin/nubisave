//  
//  Copyright (C) 2011 Rico Tzschichholz
// 
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
// 
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
// 
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
// 

namespace NubiSave
{
	public interface PrefsSerializable : GLib.Object
	{
		public abstract string prefs_serialize ();
		public abstract void prefs_deserialize (string s);
	}
	
	public abstract class Preferences : GLib.Object
	{
		public signal void deleted ();
		
		public Preferences ()
		{
			notify.connect (handle_notify);
		}
		
		~Preferences ()
		{
			stop_monitor ();
		}
		
		void handle_notify (Object sender, ParamSpec property)
		{
			notify.disconnect (handle_notify);
			verify (property.name);
			notify.connect (handle_notify);
			
			if (backing_file != null)
				save_prefs ();
		}
		
		protected virtual void verify (string prop)
		{
			// do nothing, this isnt abstract because we dont
			// want to force subclasses to implement this
		}
		
		public void save ()
		{
			if (backing_file != null)
				save_prefs ();
		}
		
		File backing_file;
		FileMonitor backing_monitor;
		
		public Preferences.with_file (string filename)
		{
			init_from_file (filename);
		}
		
		protected void init_from_file (string filename)
		{
			backing_file = Paths.UserConfigFolder.get_child (filename);
			
			// ensure the preferences file exists
			Paths.ensure_directory_exists (backing_file.get_parent ());
			if (!backing_file.query_exists ())
				save_prefs ();
			
			load_prefs ();
			
			start_monitor ();
		}
		
		public virtual void delete ()
		{
			try {
				backing_file.delete ();
			} catch { }
		}
		
		void stop_monitor ()
		{
			if (backing_monitor == null)
				return;
			
			backing_monitor.changed.disconnect (backing_file_changed);
			backing_monitor.cancel ();
			backing_monitor = null;
		}
		
		void start_monitor ()
		{
			if (backing_monitor != null)
				return;
			
			try {
				backing_monitor = backing_file.monitor (0);
				backing_monitor.set_rate_limit (500);
				backing_monitor.changed.connect (backing_file_changed);
			} catch {
				backing_error ("Unable to watch the preferences file '%s'");
			}
		}
		
		void backing_file_changed (File f, File? other, FileMonitorEvent event)
		{
			// only watch for change or delete events
			if ((event & FileMonitorEvent.CHANGES_DONE_HINT) != FileMonitorEvent.CHANGES_DONE_HINT &&
				(event & FileMonitorEvent.DELETED) != FileMonitorEvent.DELETED)
				return;
			
			if ((event & FileMonitorEvent.DELETED) == FileMonitorEvent.DELETED)
				deleted ();
			else
				load_prefs ();
		}
		
		void load_prefs ()
		{
			Logger.debug<Preferences> ("Loading preferences from file '%s'".printf (backing_file.get_path ()));
			
			notify.disconnect (handle_notify);
			try {
				KeyFile file = new KeyFile ();
				file.load_from_file (backing_file.get_path (), 0);
				
				var obj_class = (ObjectClass) get_type ().class_ref ();
				var properties = obj_class.list_properties ();
				foreach (var prop in properties) {
					var group_name = prop.owner_type.name ();
					
					if (!file.has_group (group_name) || !file.has_key (group_name, prop.name))
						continue;
					
					var type = prop.value_type;
					var val = Value (type);
					
					if (type == typeof (uint64))
						val.set_uint64 (file.get_uint64 (group_name, prop.name));
					else if (type == typeof (int))
						val.set_int (file.get_integer (group_name, prop.name));
					else if (type == typeof (double))
						val.set_double (file.get_double (group_name, prop.name));
					else if (type == typeof (string))
						val.set_string (file.get_string (group_name, prop.name));
					else if (type == typeof (bool))
						val.set_boolean (file.get_boolean (group_name, prop.name));
					else if (type.is_enum ())
						val.set_enum (file.get_integer (group_name, prop.name));
					else if (type.is_a (typeof (PrefsSerializable))) {
						get_property (prop.name, ref val);
						(val.get_object () as PrefsSerializable).prefs_deserialize (file.get_string (group_name, prop.name));
						continue;
					} else {
						backing_error ("Unsupported preferences type '%s'");
						continue;
					}
					
					set_property (prop.name, val);
					verify (prop.name);
				}
			} catch {
				Logger.warn<Preferences> ("Unable to load preferences from file '%s'".printf (backing_file.get_path ()));
				deleted ();
			}
			notify.connect (handle_notify);
		}
		
		void save_prefs ()
		{
			stop_monitor ();
			
			KeyFile file = new KeyFile ();
			
			try {
				file.set_comment (null, null, "This file auto-generated by NubiSave.\n" + new DateTime.now_utc ().to_string ());
			} catch { }
			
			var obj_class = (ObjectClass) get_type ().class_ref ();
			var properties = obj_class.list_properties ();
			foreach (var prop in properties) {
				var group_name = prop.owner_type.name ();
				
				var type = prop.value_type;
				var val = Value (type);
				get_property (prop.name, ref val);
				
				if (type == typeof (uint64))
					file.set_uint64 (group_name, prop.name, val.get_uint64 ());
				else if (type == typeof (int))
					file.set_integer (group_name, prop.name, val.get_int ());
				else if (type == typeof (double))
					file.set_double (group_name, prop.name, val.get_double ());
				else if (type == typeof (string))
					file.set_string (group_name, prop.name, val.get_string ());
				else if (type == typeof (bool))
					file.set_boolean (group_name, prop.name, val.get_boolean ());
				else if (type.is_enum ())
					file.set_integer (group_name, prop.name, val.get_enum ());
				else if (type.is_a (typeof (PrefsSerializable)))
					file.set_string (group_name, prop.name, (val.get_object () as PrefsSerializable).prefs_serialize ());
				else {
					backing_error ("Unsupported preferences type '%s'");
					continue;
				}
				
				var blurb = prop.get_blurb ();
				if (blurb != null && blurb != "" && blurb != prop.name)
					try {
						file.set_comment (group_name, prop.name, blurb);
					} catch { }
			}
			
			Logger.debug<Preferences> ("Saving preferences '%s'".printf (backing_file.get_path ()));
			
			try {
				DataOutputStream stream;
				if (backing_file.query_exists ())
					stream = new DataOutputStream (backing_file.replace (null, false, 0));
				else
					stream = new DataOutputStream (backing_file.create (0));
				
				stream.put_string (file.to_data ());
			} catch {
				backing_error ("Unable to create the preferences file '%s'");
			}
			
			start_monitor ();
		}
		
		void backing_error (string err)
		{
			Logger.fatal<Preferences> (err.printf (backing_file.get_path ()));
		}
	}
}
