package com.shivam.sensorapi.dao;

import java.util.List;

import com.shivam.sensorapi.entity.Alert;

public interface AlertDAO {

	public List<Alert> read();
	
	public List<Alert> readAlertByTimeRange(Long starttime,Long endtime);
	
	public boolean create(Alert alert);
}
