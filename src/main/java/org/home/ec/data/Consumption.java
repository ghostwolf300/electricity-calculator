package org.home.ec.data;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="t_consumption")
public class Consumption {
	
	@EmbeddedId
	private ConsumptionId id;
	
	@Column(name="consumption")
	private double consumption;
	
	@ManyToOne
	@MapsId("locationId")
	private Location location;
	
	public Consumption() {
		super();
	}
	
	public Consumption(Date keyDate, int keyHour,int keyMinute, long locationId,double consumption) {
		super();
		this.id=new ConsumptionId(keyDate,keyHour,keyMinute,locationId);
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
