package com.shivam.sensorapi.config;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

@Configuration
@PropertySource(value="classpath:config.properties")
public class MongoConfiguration {

	private Datastore datastore;
	public MongoConfiguration() {
		// TODO Auto-generated constructor stub
	}
	@Autowired private Environment env;
	private static final Logger logger=Logger.getLogger(MongoConfiguration.class);
	@Bean
	public Datastore getDatastore() {
		logger.info("Entered getDatastore method");
		String host=env.getProperty("mongodb.host");
		String port=env.getProperty("mongodb.port");
		MongoClientOptions options=MongoClientOptions.builder().socketTimeout(60000).connectTimeout(15000).maxConnectionIdleTime(600000).readPreference(ReadPreference.primaryPreferred()).build();
		MongoClient mongoClient=null;
		try {
			logger.info("Host:"+env.getProperty("mongodb.host")+" Port:"+env.getProperty("mongodb.port"));
			mongoClient=new MongoClient(new ServerAddress(host,Integer.parseInt(port)),options);
		} catch (UnknownHostException e) {
			logger.debug("Exception occured while connecting:"+e.getMessage());
		}
		datastore=new Morphia().createDatastore(mongoClient, env.getProperty("mongodb.database"));
		datastore.ensureCaps();
		datastore.ensureIndexes();
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}


}
