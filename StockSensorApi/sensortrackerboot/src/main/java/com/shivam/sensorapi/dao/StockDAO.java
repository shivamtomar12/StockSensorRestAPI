package com.shivam.sensorapi.dao;

import java.util.List;

import com.shivam.sensorapi.entity.Stock;

public interface StockDAO {

	public List<Stock> read();
	
	public Stock readStockByValue(String value);
	
	public boolean updateStockByValue(String oldValue,String newValue);
	
	public boolean createStock(Stock stock);
	
	public boolean deleteStockByValue(String value);
	
	public Stock getBaseWeight();

}
