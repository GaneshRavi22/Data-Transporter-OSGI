package com.osgi.transporter.api.sink;

public interface Sink {

	public void connect();
	
	public boolean isConnected();
	
	public void disconnect();
	
	public void writeNext(String data);
	
}
