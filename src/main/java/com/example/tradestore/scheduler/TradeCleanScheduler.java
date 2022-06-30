package com.example.tradestore.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.tradestore.service.TradeStoreService;
/**
 * 
 * @author Sapna Girdhani
 *
 */
@Component
public class TradeCleanScheduler {
	private static final Logger log = LoggerFactory.getLogger(TradeCleanScheduler.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	TradeStoreService tradeService;

	@Scheduled(cron = "0/30 * * * * ?") //just set this cron for testing.can be changed later.
	public void flagExpiredTrades() {
		log.info("Scheduler run :", dateFormat.format(new Date()));
		tradeService.updateExpiredTrades();
	}

}
