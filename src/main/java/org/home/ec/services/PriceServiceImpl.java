package org.home.ec.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.home.ec.data.HourId;
import org.home.ec.data.Price;
import org.home.ec.data.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import reactor.core.publisher.Mono;

@Service
public class PriceServiceImpl implements PriceService {
	
	private final WebClient webClient;
	
	@Autowired
	private PriceRepository repository;
	
	
	public PriceServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient=webClientBuilder.baseUrl("https://api.porssisahko.net/v1").build();
	}
	
	@Override
	public List<Price> getPrices(Date fromDate, Date toDate) {
		System.out.println("Prices from local database.");
		List<Price> prices=repository.findByIdKeyDateBetween(fromDate, toDate);
		return prices;
	}

	@Override
	public void updateData(Date fromDate, Date toDate) {
		System.out.println("Query prices...");
		Calendar c=Calendar.getInstance();
		Date keyDate=fromDate;
		
		do {
			System.out.println("Retrieving prices for date "+keyDate.toString());
			updateDayData(keyDate);
			c.setTime(keyDate);
			c.add(Calendar.DATE, 1);
			keyDate=new Date(c.getTimeInMillis());
		}
		while(keyDate.compareTo(toDate)<=0);
		
		
	}
	
	private void updateDayData(Date day) {
		
		List<Price> prices=new ArrayList<Price>();
		
		for(int h=0;h<24;h++) {
			//System.out.println("hour is: "+h);
			String hour=String.valueOf(h);
			ResponseSpec response=webClient.get()
					.uri(uriBuilder -> uriBuilder
							.path("/price.json")
							.queryParam("date",day.toString())
							.queryParam("hour",hour)
							.build())
					.retrieve();
					//.bodyToMono(String.class)
					//.onErrorResume(e->Mono.empty())
					//.block();
			Mono<PriceResponse> mono=response.bodyToMono(PriceResponse.class);
			PriceResponse priceResponse=mono.block();
			//System.out.println("Price is: "+priceResponse.getPrice());
			Price price=new Price(new HourId(day,h),BigDecimal.valueOf(priceResponse.getPrice()));
			prices.add(price);
		}
		
		repository.saveAllAndFlush(prices);
		
	}

}
