package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class PriceServiceImplTest {
	
	@TestConfiguration
	static class PriceServiceImplTestConfiguration{
		@Bean
		public PriceService priceService() {
			return new PriceServiceImpl(WebClient.builder());
		}
	}
	
	@Autowired
	private PriceService priceService;
	
	@Test
	public void testCheckMissingData() {
		Date fromDate=Date.valueOf("2023-11-01");
		Date toDate=Date.valueOf("2023-12-31");
		List<Price> missingPrices=priceService.checkForMissingData(fromDate, toDate);
	}
}
