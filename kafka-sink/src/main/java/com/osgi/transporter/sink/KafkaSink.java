package com.osgi.transporter.sink;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.osgi.transporter.api.sink.Sink;

public class KafkaSink implements Sink {

	private final Properties connProperties;
	private Producer<String, String> producer;
	private long counter;
	private final String topic;

	public KafkaSink(String hostname, int port, String topic) {
		Properties props = new Properties();
		props.put("bootstrap.servers", hostname + ":" + port);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		this.connProperties = props;
		this.counter = 0L;
		this.topic = topic;
	}

	@Override
	public void connect() {
		producer = new KafkaProducer<>(connProperties);
	}

	@Override
	public boolean isConnected() {
		return producer != null;
	}

	@Override
	public void disconnect() {
		if(producer != null) {
			producer.close();
		}
	}

	@Override
	public void writeNext(String data) {
		if(producer != null) {
			producer.send(new ProducerRecord<String, String>(topic, Long.toString(++counter), data));
		}
	}

}
