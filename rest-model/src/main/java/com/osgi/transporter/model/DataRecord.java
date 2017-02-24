package com.osgi.transporter.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataRecord {
	String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
