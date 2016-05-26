package com.shivam.sensorapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.sensorapi.service.AlertService;

@RestController
@RequestMapping("/alertsservice")
public class AlertController {
	@Autowired private AlertService alertservice;
	
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public ResponseEntity<?> read(){
		return new ResponseEntity<>(alertservice.read(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/readAlertByTimeRange/{starttime}/{endtime}",method=RequestMethod.GET)
	public ResponseEntity<?> readAlertByTimeRange(@PathVariable Long starttime, @PathVariable Long endtime){
		return new ResponseEntity<>(alertservice.readAlertByTimeRange(starttime, endtime),HttpStatus.OK);
	}
	
}
