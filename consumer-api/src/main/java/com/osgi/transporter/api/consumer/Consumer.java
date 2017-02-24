package com.osgi.transporter.api.consumer;

import com.osgi.transporter.model.DataRecord;

public interface Consumer {

	public void consume(DataRecord data);
	
}
