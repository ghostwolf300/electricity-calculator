package org.home.ec.services;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.home.ec.data.Price;

public interface PriceService {
	public List<Price> getPrices(Date fromDate, Date toDate);
	public void setPrices(List<Price> prices);
	public void updateData(Date fromDate, Date toDate);
	public void processPriceFile(InputStream fileData,String fileName,long contentLength,String mimeType);
	public List<Price> checkForMissingData(Date fromDate,Date toDate);
}
