package com.osgi.transporter.server;

import java.util.ArrayList;
import java.util.List;

import com.osgi.transporter.api.consumer.Consumer;
import com.osgi.transporter.api.sink.Sink;
import com.osgi.transporter.model.DataRecord;
import com.osgi.transporter.model.DataService;

public class DataServiceImpl implements DataService {
	private final List<DataRecord> recentTenDataList;
	private final List<Sink> sinks;
	private final Consumer consumer;

	public DataServiceImpl(Consumer consumer) {
		this.sinks = new ArrayList<>();
		this.consumer = consumer;
		this.recentTenDataList = new ArrayList<>();
	}

	@Override
	public DataRecord[] getAll() {
		return recentTenDataList.toArray(new DataRecord[]{});
	}

	public void onBindService(final Sink sink) {
		if(sink != null) {
			System.out.println("Registered sink in Consumer");
			sinks.add(sink);
		}
	}

	@Override
	public void addDataRecord(DataRecord data) {
		consumer.consume(data);
		if(recentTenDataList.size() > 10) {
			recentTenDataList.remove(0);
		}
		recentTenDataList.add(data);
	}

}
