package com.osgi.transporter.consumer;

import java.util.ArrayList;
import java.util.List;

import com.osgi.transporter.api.consumer.Consumer;
import com.osgi.transporter.api.sink.Sink;
import com.osgi.transporter.model.DataRecord;

public class ConsumerImpl implements Consumer {

	private List<Sink> sinks;

	public ConsumerImpl() {
		sinks = new ArrayList<>();
	}

	public void consume(DataRecord data) {
		if(sinks.isEmpty()) {
			System.out.println("No sinks bound to the consumer");
		} else {
			for(Sink sink : sinks) {
				if(!sink.isConnected()) {
					sink.connect();
				}
				sink.writeNext(data.getData());
			}
		}
	}

	public void onBindService(final Sink sink) {
		if(sink != null) {
			System.out.println("Registered sink in Consumer");
			sinks.add(sink);
		}
	}

}
