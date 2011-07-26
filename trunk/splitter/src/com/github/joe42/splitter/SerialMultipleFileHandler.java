package com.github.joe42.splitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class SerialMultipleFileHandler implements MultipleFileHandler {
	public SerialMultipleFileHandler() {
	}
	public int writeFilesAsByteArrays(HashMap<String, byte[]> files){
		int nr_of_files_written = 0;
		if(files == null || files.size() == 0){
			return nr_of_files_written;
		}
		for (String file_name : files.keySet()) {
			if(write(file_name, files.get(file_name))){
				nr_of_files_written++;
			}
		}
		return nr_of_files_written;
	}

	private boolean write(String fileName, byte[] bytes) {
		OutputStream out;
		try {
			out = new FileOutputStream(fileName);
			out.write(bytes);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<byte[]> getFilesAsByteArrays(String[] file_names, int files_needed){
		List<byte[]> ret = new ArrayList<byte[]>();
		if(file_names == null || file_names.length == 0){
			return ret;
		}
		for (String file_name : file_names) {
			ret.add(getFileAsByteArray(file_name));
			if(ret.size() >= files_needed){
				break;
			}
		}
		return ret;
	}
	public List<byte[]> getFilesAsByteArrays(String[] file_names){
		return getFilesAsByteArrays(file_names, file_names.length);
	}

	private byte[] getFileAsByteArray(String file_name) {
		byte[] ret = null;
		File file = new File(file_name);
		if (file.exists()) {
			try {
				FileInputStream in = new FileInputStream(file);
				ret = new byte[(int) file.length()];
				in.read(ret);
				in.close();
			} catch (IOException e) {
				//don't care
				e.printStackTrace();
			}
		}
		return ret;
	}
}
