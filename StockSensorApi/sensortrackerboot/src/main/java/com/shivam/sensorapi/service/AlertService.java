package com.shivam.sensorapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.sensorapi.dao.AlertDAO;
import com.shivam.sensorapi.entity.Alert;

@Service
public class AlertService {

	@Autowired AlertDAO alertdao;
	
	public List<Alert> read(){
		return alertdao.read();
	}

	public List<Alert> readAlertByTimeRange(Long starttime,Long endtime){
		return alertdao.readAlertByTimeRange(starttime, endtime);
	}
}
