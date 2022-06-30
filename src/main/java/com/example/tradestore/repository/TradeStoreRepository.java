package com.example.tradestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tradestore.model.TradeStore;
/**
 * 
 * @author Sapna Girdhani
 *
 */
@Repository
public interface TradeStoreRepository extends JpaRepository<TradeStore,String>{

}
