package com.shivam.sensorapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.sensorapi.entity.Stock;
import com.shivam.sensorapi.exception.NoStockFoundException;
import com.shivam.sensorapi.exception.UpdateFailedException;
import com.shivam.sensorapi.service.StockService;

@RestController
@RequestMapping("/stockservice")
public class StockController {

	@Autowired StockService stockService;
	
	@RequestMapping(value="/read",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> read() throws NoStockFoundException{
		List<Stock> stocklist = stockService.read();
		return new ResponseEntity<List<Stock>>(stocklist,HttpStatus.OK);
	}


	@RequestMapping(value="/readByValue/{value}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> readStockByValue(@PathVariable String value) throws NoStockFoundException{
		return new ResponseEntity<Stock>(stockService.readStockByValue(value),HttpStatus.OK);
	}

	@RequestMapping(value="/update/{oldValue}/{newValue}",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStockByValue(@PathVariable String oldValue,@PathVariable String newValue) throws NoStockFoundException{
		boolean isUpdated = stockService.updateStockByValue(oldValue, newValue);
		return new ResponseEntity<Boolean>(isUpdated,HttpStatus.OK);
	}

	@RequestMapping(value="/create",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createStock(@RequestBody Stock stock) throws UpdateFailedException{
		boolean isCreated = stockService.createStock(stock);
		return new ResponseEntity<Boolean>(isCreated,HttpStatus.CREATED);
	}

	@RequestMapping(value="/delete/{value}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteStockByValue(@PathVariable String value) throws UpdateFailedException{
		boolean isDeleted = stockService.deleteStockByValue(value);
		return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);
	}

}
