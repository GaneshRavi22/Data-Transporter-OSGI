package com.osgi.transporter.api.source;

public interface Source {

	public void connect();
	
	public boolean isConnected();
	
	public void disconnect();
	
	public String readNext();
	
	public boolean isEndOfStream();
	
}
