package com.example.tradestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tradestore.exception.InvalidTradeException;
import com.example.tradestore.model.TradeStore;
import com.example.tradestore.service.TradeStoreService;

/**
 * 
 * @author Sapna Girdhani
 *
 */

@RestController
public class TradeStoreController {
	
	    @Autowired
	    TradeStoreService tradeService;
	   
	    @PostMapping("/create-trade")
	    public ResponseEntity<String> createTrade(@RequestBody TradeStore trade){
	    	if(tradeService.validateMaturityDate(trade)) {
	    		if(tradeService.isValid(trade)){
	    			tradeService.saveTrade(trade);
	 	       }else{
	 	           throw new InvalidTradeException("Version of trade id-"+trade.getTradeId()+"  is lower than the existing one");
	 	       }
	    	}else {
	    		  throw new InvalidTradeException("Maturity date of trade id-"+trade.getTradeId()+"  is lower than the current date");
	    	}
	        return ResponseEntity.status(HttpStatus.OK).build();
	    }

	    @GetMapping("/trades")
	    public List<TradeStore> findAllTrades(){
	        return tradeService.findAll();
	    }

}
