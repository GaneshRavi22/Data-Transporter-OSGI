package com.osgi.transporter.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.stream.Stream;

import com.osgi.transporter.api.source.Source;

public class FileSource implements Source {
	
	private Iterator<String> fileIterator;
	private Stream<String> fileStream;
	private String filePath;

	public FileSource(String filePath) {
		this.filePath = filePath;
	}
	
	public static void main(String[] args) {
		FileSource fs = new FileSource("D:\\all_squelched.txt");
		fs.connect();
		while(!fs.isEndOfStream()) {
			System.out.println(fs.readNext());
		}
		fs.disconnect();
	}
	
	@Override
	public void connect() {
		if(filePath == null || filePath.isEmpty()) {
			throw new IllegalArgumentException("Source file name cannot be empty");
		}
		File sourceFile = new File(filePath);
		if(!sourceFile.exists()) {
			throw new IllegalArgumentException("Source file does not exist: " + filePath);
		}
		try {
			fileStream = Files.lines(sourceFile.toPath());
			fileIterator = fileStream.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isConnected() {
		return fileIterator != null;
	}

	@Override
	public void disconnect() {
		if(fileStream != null) {
			fileStream.close();
		}
	}

	@Override
	public String readNext() {
		if(fileStream == null || fileIterator == null) {
			return null;
		}
		return fileIterator.next();
	}

	@Override
	public boolean isEndOfStream() {
		if(fileStream == null || fileIterator == null) {
			return true;
		}
		return !fileIterator.hasNext();
	}

}
