package org.home.ec.data;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Consumption {
	
	@EmbeddedId
	private HourId id;
	
	@Column(name="consumption")
	private double consumption;
	
	@OneToOne(mappedBy="consumption")
	@PrimaryKeyJoinColumn
	//@JoinColumn(name="day", referencedColumnName="day")
    //@JoinColumn(name="hour", referencedColumnName="hour")
	private Price price;
	
	public Consumption() {
		super();
	}
	
	public Consumption(HourId id, double consumption) {
		super();
		this.id=id;
		this.consumption=consumption;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public HourId getId() {
		return id;
	}

	public void setId(HourId id) {
		this.id = id;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
}
