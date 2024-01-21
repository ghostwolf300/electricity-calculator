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
	
	//@EmbeddedId
	//private HourId id;
	
	@EmbeddedId
	private ConsumptionId id;
	
	@Column(name="consumption")
	private double consumption;
	
	@ManyToOne
	@MapsId("locationId")
	private Location location;
	
//	@OneToOne(fetch=FetchType.LAZY,mappedBy="consumption",cascade=CascadeType.ALL)
//	@PrimaryKeyJoinColumn
//	private Price price;
	
	public Consumption() {
		super();
	}
	
	public Consumption(Date keyDate, int keyHour, long locationId,double consumption) {
		super();
		this.id=new ConsumptionId(keyDate,keyHour,locationId);
		this.consumption=consumption;
	}
	
	public Consumption(String keyDate,int keyHour,long locationId,double consumption) {
		super();
		this.id=new ConsumptionId(Date.valueOf(keyDate),keyHour,locationId);
		this.consumption=consumption;
	}
	
	/*
	 * public Consumption(HourId id, double consumption) { super(); this.id=id;
	 * this.consumption=consumption; }
	 * 
	 * public Consumption(String keyDate, int keyHour, double consumption) {
	 * this.id=new HourId(Date.valueOf(keyDate),keyHour);
	 * this.consumption=consumption; }
	 */

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

	/*
	 * public HourId getId() { return id; }
	 * 
	 * public void setId(HourId id) { this.id = id; }
	 */
//	public Price getPrice() {
//		return price;
//	}
//
//	public void setPrice(Price price) {
//		this.price = price;
//	}
}
