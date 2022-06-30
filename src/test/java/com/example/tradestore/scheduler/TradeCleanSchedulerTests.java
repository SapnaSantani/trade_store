package com.example.tradestore.scheduler;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.awaitility.Duration;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import com.example.tradestore.TradestoreApplication;

/**
 * @author Sapna Girdhani
 */

@SpringJUnitConfig(TradestoreApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradeCleanSchedulerTests {
	 @SpyBean
	    private TradeCleanScheduler tradeCleanScheduler;

	    @Test
	    public void whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() {
	        await()
	                .atMost(Duration.ONE_MINUTE)
	                .untilAsserted(() -> verify(tradeCleanScheduler,atLeast(2)).flagExpiredTrades());
	    }
}
