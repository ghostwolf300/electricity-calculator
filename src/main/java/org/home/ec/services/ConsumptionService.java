package org.home.ec.services;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.home.ec.data.Consumption;

public interface ConsumptionService {
	public List<Consumption> getConsumption(long locationId,Date fromDate,Date toDate);
	public void updateData(Date fromDate,Date toDate);
	public void processConsumptionFile(InputStream fileData,String fileName,long contentLength,String mimeType);
}
