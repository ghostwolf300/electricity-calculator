package org.home.ec.data;

import java.sql.Date;

public class FingridDTO {
	
	private long locationId;
	private Date keyDate;
	private int keyHour;
	private double consumption;
	
	public FingridDTO() {
		super();
	}

	public FingridDTO(long locationId, Date keyDate, int keyHour, double consumption) {
		super();
		this.locationId = locationId;
		this.keyDate = keyDate;
		this.keyHour = keyHour;
		this.consumption = consumption;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public Date getKeyDate() {
		return keyDate;
	}

	public void setKeyDate(Date keyDate) {
		this.keyDate = keyDate;
	}

	public int getKeyHour() {
		return keyHour;
	}

	public void setKeyHour(int keyHour) {
		this.keyHour = keyHour;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
}
