package com.example.democrud.model;

import org.springframework.web.multipart.MultipartFile;

public class Document {
	private byte[] document;
	private String name;
	private String typemime;
	private long size;
	private String code;
	
	private MultipartFile file;
	
	public Document() {}

	public Document(MultipartFile file, String code) {
		this.file = file;
		this.code = code;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypemime() {
		return typemime;
	}

	public void setTypemime(String typemime) {
		this.typemime = typemime;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
