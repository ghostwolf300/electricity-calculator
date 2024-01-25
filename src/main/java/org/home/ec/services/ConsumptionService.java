package org.home.ec.services;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.home.ec.data.Consumption;

public interface ConsumptionService {
	public List<Consumption> getConsumption(long locationId,Date fromDate,Date toDate);
	public void setConsumption(List<Consumption> consumption);
	public void updateData(Date fromDate,Date toDate);
	public void processConsumptionFile(InputStream fileData,String fileName,long contentLength,String mimeType);
	public List<Consumption> checkForMissingData(long locationId,Date fromDate,Date toDate);
}
