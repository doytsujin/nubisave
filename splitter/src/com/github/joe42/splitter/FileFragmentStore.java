package com.github.joe42.splitter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**Manages file fragments of complete files.
 * Complete files are refered to by the pathname path, whereas their fragments are lists of pathnames to each file fragment.
 * It is used to keep track of the files which are dispersed into fragments or glued together in the class Splitter.*/
public class FileFragmentStore {
	private Map<String, FileFragments> fileFragmentsMap;
	private static final Logger  log = Logger.getLogger("FileFragmentStore");

	/**Create a new FileFragmentStore instance
	 * @param storages */
	public FileFragmentStore() {
		this.fileFragmentsMap = new HashMap<String, FileFragments>();
	}

	/**Removes all fragments for the file
	 * @param fileName the pathname to the whole file whose fragments are deleted*/
	public void removeFragments(String fileName){
		fileFragmentsMap.put(fileName, null);
	}

	/** Gets the fragments of the file
	 * @param fileName the path to the whole file 
	 * @return the paths to all fragments of the file*/
	public ArrayList<String> getFragments(String fileName){
		return fileFragmentsMap.get(fileName).getPaths();
	}
	
	/** Check if the store has fragments of the file fileName
	 * @param fileName the path to the whole file 
	 * @return true iff the store contains fragments of the file*/
	public boolean hasFragments(String fileName){
		return fileFragmentsMap.get(fileName) != null;
	}

	/** Rename the file
	 * @param from a path to a whole file 
	 * @param to the new path of a whole file*/
	public void moveFragments(String from, String to){
		fileFragmentsMap.put(to, fileFragmentsMap.get(from));
		fileFragmentsMap.remove(from);
	}

	/**Sets a list of fragment paths for a whole file
	 * @param fileName the whole file
	 * @param fragmentPaths the fragments of the file 
	 */
	public void setFragment(String fileName, ArrayList<String> fragmentPaths, int requiredFragments){
		fileFragmentsMap.put(fileName, new FileFragments(fragmentPaths, requiredFragments));
	}

	/**Get the number of fragments required to reconstruct the file
	 * @param fileName the file to reconstruct
	 * @return the number of fragments required to reconstruct the file or 0 if the file is not yet associated with any fragments
	 */
	public int getNrOfRequiredFragments(String fileName){
		FileFragments fragments = fileFragmentsMap.get(fileName);
		if(fragments == null){
			return 0;
		}
		return fragments.getNrOfRequiredFragments();
	}
	
	/**Get the number of fragments of the file
	 * @param fileName the complete file
	 * @return the number of all fragments for the complete file or 0 if the file is not yet associated with any fragments
	 */
	public int getNrOfFragments(String fileName){
		FileFragments fragments = fileFragmentsMap.get(fileName);
		if(fragments == null){
			return 0;
		}
		return fragments.getNrOfFragments();
	}

	/**Set the number of fragments required to reconstruct the file
	 * @param fileName the file to reconstruct
	 * @param requiredFragments the number of fragments required to reconstruct the file
	 */
	public void setNrOfRequiredFragments(String fileName, int requiredFragments) {
		fileFragmentsMap.get(fileName).setNrOfRequiredFragments(requiredFragments);		
	}
}