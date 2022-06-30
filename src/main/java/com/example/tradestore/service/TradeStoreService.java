package com.example.tradestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tradestore.model.TradeStore;
import com.example.tradestore.repository.TradeStoreRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sapna Girdhani
 *
 */
@Service
public class TradeStoreService {
	
	    private static final Logger log = LoggerFactory.getLogger(TradeStoreService.class);

	    @Autowired
	    TradeStoreRepository tradeStoreRepository;

	    public boolean isValid(TradeStore trade){
	        	//Checking if trade already exists
	            Optional<TradeStore> exsitingTrade = tradeStoreRepository.findById(trade.getTradeId());
	             if (exsitingTrade.isPresent()) {
	            	 // if present, validating version
	                 return validateVersion(trade, exsitingTrade.get());
	             }else{
	                 return true;
	             }
	    }

	    private boolean validateVersion(TradeStore trade,TradeStore oldTrade) {
	        //validation 1  During transmission if the
	        // lower version is being received by the store it will reject the trade and throw an exception.
	        if(trade.getVersion() >= oldTrade.getVersion()){
	            return true;
	        }
	        return false;
	    }

	    //2.	Store should not allow the trade which has less maturity date than current date
	    public boolean validateMaturityDate(TradeStore trade){
	        return trade.getMaturityDate().isBefore(LocalDate.now())  ? false:true;
	    }

	    public void saveTrade(TradeStore trade){
	        trade.setCreatedDate(LocalDate.now());
	        tradeStoreRepository.save(trade);
	    }

	    public List<TradeStore> findAll(){
	       return  tradeStoreRepository.findAll();
	    }

	    public void updateExpiredTrades(){
	    	tradeStoreRepository.findAll().stream().forEach(t -> {
	                if (!validateMaturityDate(t)) {
	                    t.setExpiredFlag("Y");
	                    log.info("Trade id "+ t.getTradeId() + " expired and set the expired flag as Y");
	                    tradeStoreRepository.save(t);
	                }
	            });
	        }


	}


