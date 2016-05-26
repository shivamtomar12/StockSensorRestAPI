package com.shivam.sensorapi.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shivam.sensorapi.entity.Alert;

@Repository
public class AlertDaoImpl implements AlertDAO{

	@Autowired Datastore datastore;
	@Override
	public List<Alert> read() {
		return datastore.find(Alert.class).asList();
	}

	@Override
	public List<Alert> readAlertByTimeRange(Long starttime,Long endtime) {
		List<Alert> alertlist = datastore.createQuery(Alert.class).field("timeStamp").greaterThan(starttime).field("timeStamp").lessThan(endtime).asList();
		return alertlist;
	}

	@Override
	public boolean create(Alert alert) {
		// TODO Auto-generated method stub
		return datastore.save(alert).getId()!=null;
	}

}
