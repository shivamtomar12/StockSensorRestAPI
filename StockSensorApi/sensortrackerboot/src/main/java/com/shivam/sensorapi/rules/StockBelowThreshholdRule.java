package com.shivam.sensorapi.rules;

import org.easyrules.core.BasicRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shivam.sensorapi.dao.AlertDAO;
import com.shivam.sensorapi.entity.Alert;
import com.shivam.sensorapi.entity.Stock;
@Component
public class StockBelowThreshholdRule extends BasicRule {

	@Autowired private AlertDAO alertdao;
	
	private Stock stock;
	private Double baseWeight;
	
	public StockBelowThreshholdRule(){
		super("StockBelowThreshholdRule","This rule will be triggered if stock value goes down by 10 percent of the basevalue of the stock");
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Double getBaseWeight() {
		return baseWeight;
	}
	public void setBaseWeight(Double baseWeight) {
		this.baseWeight = baseWeight;
	}
	@Override
	public boolean evaluate() {
		return Double.parseDouble(stock.getValue()) < (this.baseWeight-(this.baseWeight*0.10));
	}
	@Override
	public void execute() throws Exception {
		Alert alert=new Alert();
		alert.setAlertMessage("Stock Below Threshhold!!");
		alert.setTimeStamp(stock.getTimeStamp());
		alert.setValue(stock.getValue());
		alertdao.create(alert);
		super.execute();
	}
	
	
}
