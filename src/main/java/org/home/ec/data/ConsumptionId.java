package org.home.ec.data;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ConsumptionId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5336190173280721205L;
	
	@Column(name="key_date")
	private Date keyDate;
	@Column(name="key_hour")
	private int keyHour;
	@Column(name="key_minute")
	private int keyMinute;
	@Column(name="location_id")
	private long locationId;
	
	public ConsumptionId() {
		super();
	}
	
	public ConsumptionId(Date keyDate,int keyHour,int keyMinute,long locationId) {
		this.keyDate=keyDate;
		this.keyHour=keyHour;
		this.keyMinute=keyMinute;
		this.locationId=locationId;
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

	public int getKeyMinute() {
		return keyMinute;
	}

	public void setKeyMinute(int keyMinute) {
		this.keyMinute = keyMinute;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(keyDate, keyHour, keyMinute, locationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ConsumptionId))
			return false;
		ConsumptionId other = (ConsumptionId) obj;
		return Objects.equals(keyDate, other.keyDate) && keyHour == other.keyHour && keyMinute == other.keyMinute
				&& locationId == other.locationId;
	}
	
	
}
