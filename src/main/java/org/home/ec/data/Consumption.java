package org.home.ec.data;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="t_consumption")
public class Consumption {
	
	@EmbeddedId
	private ConsumptionId id;
	
	@Column(name="start_time_utc")
	private Timestamp startTimeUTC;
	
	@Column(name="consumption")
	private double consumption;
	
	@ManyToOne
	@MapsId("locationId")
	private Location location;
	
	public Consumption() {
		super();
	}
	
	public Consumption(ConsumptionId id) {
		super();
		this.id=id;
	}
	
	public Consumption(Date keyDate, int keyHour,int keyMinute, long locationId,double consumption) {
		super();
		this.id=new ConsumptionId(keyDate,keyHour,keyMinute,locationId);
		this.consumption=consumption;
		this.location=new Location(locationId);
	}
	
	public Consumption(Date keyDate, int keyHour,int keyMinute,Timestamp startTimeUTC,long locationId,double consumption) {
		super();
		this.id=new ConsumptionId(keyDate,keyHour,keyMinute,locationId);
		this.startTimeUTC=startTimeUTC;
		this.consumption=consumption;
		this.location=new Location(locationId);
	}
	
	public Consumption(String keyDate,int keyHour,int keyMinute,long locationId,double consumption) {
		super();
		this.id=new ConsumptionId(Date.valueOf(keyDate),keyHour,keyMinute,locationId);
		this.consumption=consumption;
		this.location=new Location(locationId);
	}

	public ConsumptionId getId() {
		return id;
	}

	public void setId(ConsumptionId id) {
		this.id = id;
	}

	public Timestamp getStartTimeUTC() {
		return startTimeUTC;
	}

	public void setStartTimeUTC(Timestamp startTimeUTC) {
		this.startTimeUTC = startTimeUTC;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


}
