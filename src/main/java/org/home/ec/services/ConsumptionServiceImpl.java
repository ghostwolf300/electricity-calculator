package org.home.ec.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.home.ec.data.Consumption;
import org.home.ec.data.ConsumptionId;
import org.home.ec.data.ConsumptionRepository;
import org.home.ec.data.FingridDTO;
import org.home.ec.data.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {
	
	private final CSVFormat csvFormat=getCSVFormat();
	private final DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private final NumberFormat numberFormat=NumberFormat.getInstance(Locale.FRANCE);
	
	@Autowired
	private ConsumptionRepository repository;
	
	@Override
	public List<Consumption> getConsumption(long locationId,Date fromDate, Date toDate) {
		System.out.println("Get consumption from DB. "+fromDate.toString()+" - "+toDate.toString());
		List<Consumption> consumption=repository.findByIdLocationIdAndIdKeyDateBetween(locationId,fromDate, toDate);
		return consumption;
	}
	
	@Override
	public void setConsumption(List<Consumption> consumption) {
		repository.saveAllAndFlush(consumption);
	}

	@Override
	public void updateData(Date fromDate, Date toDate) {
		System.out.println("Updating DB. "+fromDate.toString()+" - "+toDate.toString());
		
	}

	@Override
	public void processConsumptionFile(InputStream fileData, String fileName, long contentLength, String mimeType) {
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
		
		List<Consumption> consumptionList=new ArrayList<Consumption>();
		
		for(CSVRecord rec : records) {
			long locationId=Long.valueOf(rec.get("locationId"));
			ZonedDateTime utcTime=ZonedDateTime.parse(rec.get("startTimeUTC"));
			ZonedDateTime fiTime=utcTime.withZoneSameInstant(ZoneId.of("Europe/Helsinki"));
			//ZonedDateTime fiTime=utcTime.withZoneSameInstant(ZoneId.of("Europe/Stockholm"));
			Date keyDate=new Date(Date.from(fiTime.toInstant()).getTime());
			Timestamp startTimeUTC=Timestamp.valueOf(utcTime.toLocalDateTime());
			System.out.println(startTimeUTC.toString());
			int keyHour=fiTime.getHour();
			int keyMinute=fiTime.getMinute();
			double amount=-1.0;
			try {
				amount=numberFormat.parse(rec.get("amount")).doubleValue();
			} 
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println(locationId+"\t"+keyDate.toString()+"\t"+keyHour+"\t"+amount);
			Consumption consumption=new Consumption(keyDate,keyHour,keyMinute,startTimeUTC,locationId,amount);
			//consumption.setLocation(new Location(consumption.getId().getLocationId()));
			consumptionList.add(consumption);
		}
		repository.saveAllAndFlush(consumptionList);
	
	}
	
	private final CSVFormat getCSVFormat() {
		String headers[]={
				"locationId",
				"productType",
				"resolution",
				"unit",
				"measuringType",
				"startTimeUTC",
				"amount",
				"quality"
		};
		CSVFormat csvFormat=CSVFormat.DEFAULT.builder()
				.setHeader(headers)
				.setSkipHeaderRecord(true)
				.setDelimiter(';')
				.build();
		return csvFormat;
	}

	@Override
	public List<Consumption> checkForMissingData(long locationId, Date fromDate, Date toDate) {
		List<Consumption> missingData=new ArrayList<Consumption>();
		List<Consumption> consumption=repository.findByIdLocationIdAndIdKeyDateBetween(locationId,fromDate, toDate);
		//System.out.println("Checking period "+fromDate.toString()+" - "+toDate.toString());
		for(LocalDate date=fromDate.toLocalDate();date.isBefore(toDate.toLocalDate());date=date.plusDays(1)){
			for(int h=0;h<24;h++) {
				for(int min=0;min<60;min+=15) {
					//System.out.println("Checking for: "+date.toString()+"\th: "+h+"\tmin: "+min);
					ConsumptionId checkId=new ConsumptionId(Date.valueOf(date),h,min,locationId);
					boolean found=false;
					for(Consumption c : consumption) {
						if(c.getId().equals(checkId)) {
							found=true;
							break;
						}
					}
					if(!found) {
						missingData.add(new Consumption(checkId));
					}
				}
			}
		}
		return missingData;
	}

}
