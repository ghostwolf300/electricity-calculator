package org.home.ec.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.home.ec.data.Price;
import org.home.ec.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadController {
	
	@Autowired
	private PriceService priceService;
	
	@GetMapping("/prices")
	public ResponseEntity<Resource> downloadPrices(
			@RequestParam String fromDate, 
			@RequestParam String toDate){
		
		Date fromDt=Date.valueOf(fromDate);
		Date toDt=Date.valueOf(toDate);
		
		System.out.println("Prices from period: "+fromDate.toString()+" - "+toDate.toString());
		
		List<Price> prices=priceService.getPrices(fromDt, toDt);
		StringWriter writer=new StringWriter();
		String[] HEADERS= {"KeyDate","KeyHour","Price"};
		
		CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
		        .setHeader(HEADERS)
		        .build();
		
		CSVPrinter printer=null;
		try {
			printer = new CSVPrinter(writer, csvFormat);
			for(Price p : prices) {
				printer.printRecord(p.getId().getKeyDate().toString(),p.getId().getKeyHour(),p.getPrice().toString());
			}
			printer.flush();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(printer!=null) {
				try {
					printer.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		byte[] data=writer.getBuffer().toString().getBytes();
		ByteArrayResource resource=new ByteArrayResource(data);
		
		HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prices_"+fromDate+"-"+toDate+".csv");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
		
		return ResponseEntity.ok()
	            .headers(header)
	            .contentLength(data.length)
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}
	
}
