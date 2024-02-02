package org.home.ec.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.ITransferCost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class ReportingServiceImplTest {
	
	@TestConfiguration
	static class ReportingServiceImplTestConfiguration {
		
		@Bean
		public ReportingService reportingService() {
			return new ReportingServiceImpl();
		}
	}
	
	@Autowired
	private ReportingService reportingService;
	
	@Test
	public void testTransferCost() {
		long locationId=643007579000222511L;
		Date fromDate=Date.valueOf("2023-11-01");
		Date toDate=Date.valueOf("2023-11-30");
		List<ITransferCost> transferCosts=reportingService.getTransferCost(locationId, fromDate, toDate);
		for(ITransferCost c : transferCosts) {
			System.out.println(c.getId()+"\t"+c.getDescription()+"\t"+c.getConsumption()+"\t"+c.getUnitPriceCt()+"\t"+c.getTransferCostEUR());
		}
		assertThat(transferCosts.size()>0);
	}
	
}
