package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_price")
public class Price {
	
	@EmbeddedId
	private HourId id;
	
	@Column(name="price", precision=8, scale=4)
	private BigDecimal price;
	
//	@OneToOne(fetch=FetchType.LAZY)
//    @MapsId("id")
//	@JoinColumn(name="key_date", referencedColumnName="key_date")
//    @JoinColumn(name="key_hour", referencedColumnName="key_hour")
//	private Consumption consumption;
	
	public Price() {
		super();
	}
	
	public Price(HourId id) {
		super();
		this.id=id;
	}

	public Price(HourId id, BigDecimal price) {
		super();
		this.id = id;
		this.price = price;
	}
	
	public Price(String keyDate,int keyHour,double price) {
		super();
		this.id=new HourId(Date.valueOf(keyDate),keyHour);
		this.price=BigDecimal.valueOf(price);
	}
	
	public Price(Date keyDate, int keyHour, BigDecimal price) {
		super();
		this.id=new HourId(keyDate,keyHour);
		this.price=price;
	}

	public HourId getId() {
		return id;
	}

	public void setId(HourId id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

//	public Consumption getConsumption() {
//		return consumption;
//	}
//
//	public void setConsumption(Consumption consumption) {
//		this.consumption = consumption;
//	}

	@Override
	public String toString() {
		return id.getKeyDate().toString()+" "+id.getKeyHour()+" "+price;
	}
	
	
	
}
