package org.home.ec.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.Consumption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class ConsumptionServiceImplTest {
	
	@TestConfiguration
	static class ConsumptionServiceImplTestConfiguration{
		@Bean
		public ConsumptionService consumptionService() {
			return new ConsumptionServiceImpl();
		}
	}
	
	@Autowired
	private ConsumptionService consumptionService;
	
	@Test
	public void testCheckMissingData() {
		long locationId=643007579000222511L;
		Date fromDate=Date.valueOf("2023-11-01");
		Date toDate=Date.valueOf("2023-12-31");
		List<Consumption> missingConsumption=consumptionService.checkForMissingData(locationId, fromDate, toDate);
		assertThat(missingConsumption.size()).isEqualTo(0);
	}
}
