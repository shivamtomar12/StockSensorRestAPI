package com.shivam.sensorapi.dao;

import java.util.List;

import org.jboss.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.shivam.sensorapi.entity.Stock;
@Repository
public class StockDaoImpl implements StockDAO {

	@Autowired Datastore datastore;
	private static final Logger logger=Logger.getLogger(StockDaoImpl.class);

	@Override
	public List<Stock> read() {
		logger.info("Inside read method");
		return datastore.find(Stock.class).asList();
	}

	@Override
	public Stock readStockByValue(String value) {
		logger.info("Inside readStockByValue method");
		return datastore.createQuery(Stock.class).field("value").equal(value).get();
	}

	@Override
	public boolean updateStockByValue(String oldValue, String newValue) {
		logger.info("Inside updateStockByValue method");
		Query<Stock> filter = datastore.createQuery(Stock.class).filter("value", oldValue);

		if(filter.asList().size()!=0){
			UpdateOperations<Stock> set = datastore.createUpdateOperations(Stock.class).set("value", newValue);

			datastore.update(filter, set);

			return true;
		}
		return false;
	}

	@Override
	public boolean createStock(Stock stock) {
		logger.info("Inside createStock method");
		Key<Stock> save = datastore.save(stock);
		return save.getId() != null;
	}

	@Override
	public boolean deleteStockByValue(String value) {
		Query<Stock> filter = datastore.createQuery(Stock.class).filter("value", value);
		
		WriteResult delete = datastore.delete(filter);
		return delete.getN()>0;
	}
	
	public Stock getBaseWeight(){
		Stock stock = datastore.createQuery(Stock.class).order("timeStamp").get();
		return stock;
	}

}
