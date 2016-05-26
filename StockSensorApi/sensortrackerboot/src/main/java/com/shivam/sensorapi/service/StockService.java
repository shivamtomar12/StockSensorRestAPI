package com.shivam.sensorapi.service;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.sensorapi.dao.StockDAO;
import com.shivam.sensorapi.entity.Stock;
import com.shivam.sensorapi.exception.NoStockFoundException;
import com.shivam.sensorapi.exception.UpdateFailedException;
import com.shivam.sensorapi.rules.StockAboveThreshholdRule;
import com.shivam.sensorapi.rules.StockBelowThreshholdRule;

@Service
public class StockService {

	@Autowired StockDAO stockdao;
	@Autowired StockBelowThreshholdRule underWtRule;
	@Autowired StockAboveThreshholdRule overWtRule;

	public List<Stock> read() throws NoStockFoundException{
		List<Stock> stocklist = stockdao.read();
		
		if(stocklist.size()==0){
			throw new NoStockFoundException();
		}
		return stocklist;
	}

	public Stock readStockByValue(String value) throws NoStockFoundException{
		Stock stock = stockdao.readStockByValue(value);
		if(stock==null){
			throw new NoStockFoundException();
		}
		return stock;
	}

	public boolean updateStockByValue(String oldValue,String newValue) throws NoStockFoundException{
		if(stockdao.updateStockByValue(oldValue, newValue)){
			return true;
		}else{
			throw new NoStockFoundException();
		}
		
	}

	private static RulesEngine getRuleEngine(){
		return RulesEngineBuilder.aNewRulesEngine().build();
	}
	public boolean createStock(Stock stock) throws UpdateFailedException{
		
		
		Stock baseWeightStockObj = stockdao.getBaseWeight();
		
		
		if(baseWeightStockObj!=null){
		
		
		underWtRule.setBaseWeight(Double.parseDouble(baseWeightStockObj.getValue()));
		underWtRule.setStock(stock);
		
		overWtRule.setBaseWeight(Double.parseDouble(baseWeightStockObj.getValue()));
		overWtRule.setStock(stock);
		
		RulesEngine ruleEngine=getRuleEngine();
		ruleEngine.registerRule(underWtRule);
		ruleEngine.registerRule(overWtRule);
		
		ruleEngine.fireRules();
		}
		if(stockdao.createStock(stock)){
			return true;
		}else{
			throw new UpdateFailedException();
		}
		
	}

	public boolean deleteStockByValue(String value) throws UpdateFailedException{
		if(stockdao.deleteStockByValue(value)){
			return true;
		}else{
			throw new UpdateFailedException();
		}
	}
}
