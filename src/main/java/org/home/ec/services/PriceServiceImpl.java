package org.home.ec.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.home.ec.data.Consumption;
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
	private final CSVFormat csvFormat=getCSVFormat();
	private final DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private final NumberFormat numberFormat=NumberFormat.getInstance(Locale.FRANCE);
	
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
	public void setPrices(List<Price> prices) {
		// TODO Auto-generated method stub
		
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
			BigDecimal priceIncludingTax=BigDecimal.valueOf(priceResponse.getPrice());
			BigDecimal priceExcludingTax=priceIncludingTax.multiply(BigDecimal.valueOf(0.76));
			//Price price=new Price(new HourId(day,h),priceIncludingTax);
			Price price=new Price(new HourId(day,h),priceExcludingTax);
			prices.add(price);
		}
		
		repository.saveAllAndFlush(prices);
		
	}

	@Override
	public List<Price> checkForMissingData(Date fromDate, Date toDate) {
		List<Price> missingData=new ArrayList<Price>();
		List<Price> prices=repository.findByIdKeyDateBetween(fromDate, toDate);
		System.out.println("Checking range "+fromDate.toString()+" - "+toDate.toString());
		for(LocalDate date=fromDate.toLocalDate();date.isBefore(toDate.toLocalDate());date=date.plusDays(1)){
			for(int h=0;h<24;h++) {
				System.out.println(date.toString()+"\t"+h);
				HourId checkId=new HourId(Date.valueOf(date),h);
				boolean found=false;
				for(Price p : prices) {
					if(p.getId().equals(checkId)) {
						found=true;
						break;
					}
				}
				if(!found) {
					missingData.add(new Price(checkId));
				}
			}
		}
		return missingData;
	}

	@Override
	public void processPriceFile(InputStream fileData, String fileName, long contentLength, String mimeType) {
		System.out.println("Processing file: "+fileName);
		BufferedReader fileReader=new BufferedReader(new InputStreamReader(fileData));
		Iterable<CSVRecord> records=null;
		
		try {
			records=csvFormat.parse(fileReader);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Price> priceList=new ArrayList<Price>();
		
		for(CSVRecord rec : records) {
			LocalDateTime dt=LocalDateTime.parse(rec.get("time"),timeFormatter);
			Date keyDate=Date.valueOf(dt.toLocalDate());
			int keyHour=dt.getHour();
			BigDecimal price=null;
			try {
				price=BigDecimal.valueOf(numberFormat.parse(rec.get("price")).doubleValue());
			} 
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Price consumption=new Price(keyDate,keyHour,price);
			//consumption.setLocation(new Location(consumption.getId().getLocationId()));
			priceList.add(consumption);
		}
		repository.saveAllAndFlush(priceList);
		
	}
	
	private final CSVFormat getCSVFormat() {
		String headers[]={
				"time",
				"price"
		};
		CSVFormat csvFormat=CSVFormat.DEFAULT.builder()
				.setHeader(headers)
				.setSkipHeaderRecord(true)
				.setDelimiter(';')
				.build();
		return csvFormat;
	}

}
