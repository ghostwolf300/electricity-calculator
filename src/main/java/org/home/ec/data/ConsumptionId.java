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
	
	private HourId hourId;
	@Column(name="location_id")
	private long locationId;
	
	public ConsumptionId() {
		super();
	}
	
	public ConsumptionId(Date keyDate,int keyHour,long locationId) {
		this.hourId=new HourId(keyDate,keyHour);
		this.locationId=locationId;
	}

	public HourId getHourId() {
		return hourId;
	}

	public void setHourId(HourId hourId) {
		this.hourId = hourId;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	
	public Date getKeyDate() {
		return this.hourId.getKeyDate();
	}
	
	public int getKeyHour() {
		return this.hourId.getKeyHour();
	}

	@Override
	public int hashCode() {
		return Objects.hash(hourId, locationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ConsumptionId))
			return false;
		ConsumptionId other = (ConsumptionId) obj;
		return Objects.equals(hourId, other.hourId) && locationId == other.locationId;
	}
	
	
}
