package com.osgi.transporter.producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.osgi.transporter.api.producer.Producer;
import com.osgi.transporter.api.source.Source;
import com.osgi.transporter.model.DataRecord;
import com.osgi.transporter.model.DataService;

public class ProducerImpl implements Producer{

	private final DataService dataService;
	private final ExecutorService execService;

	public ProducerImpl(DataService dataService) {
		this.dataService = dataService;
		execService = Executors.newCachedThreadPool();
	}

	public void produce(Source source) {
		execService.submit(() -> {
			source.connect();
			while (source.isConnected() && !source.isEndOfStream()) {
				String next = source.readNext();
				DataRecord dr = new DataRecord();
				dr.setData(next);
				dataService.addDataRecord(dr);
			}
			source.disconnect();
		});
	}
	
	public void onBindService(final Source source) {
		if(source != null) {
			System.out.println("Registered source in Producer");
			produce(source);
		}
	}

}
