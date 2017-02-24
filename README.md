Data Transporter App
---------------------
Data transporter application is used to transport data from one or more sources to one or more sinks.
By default file source (implementing producer-api) and kafka sink (implementing consumer-api) are implemented. But other sources and sinks can be implemented and deployed easily.
By default, data from all sources are transported to all sinks.


Installing the app via KAR file in a Apache Karaf
-------------------------------------------------
Open the Karaf console and run the following command:

kar:install file:/path/to/kar-generator-1.0.0.kar


Modifying properties via Webconsole
------------------------------------
Go to the following url to access the Karaf webconsole => http://localhost:8181/system/console/configMgr  
Modify the following configurations shown in the webpage:

1. com.osgi.transporter.source
	* fileUrl

2. com.osgi.transporter.sink
	* kafkaHostname
	* kafkaPort
	* kafkaTopicName

Modules will automatically be reloaded when configurations are changed.


Modifying properties via cfg files
------------------------------------
1. <KARAF_HOME>/etc/com.osgi.transporter.source.cfg
	* fileUrl

2. <KARAF_HOME>/etc/com.osgi.transporter.sink.cfg
	* kafkaHostname
	* kafkaPort
	* kafkaTopicName
	
Modules will automatically be reloaded when configurations are changed.