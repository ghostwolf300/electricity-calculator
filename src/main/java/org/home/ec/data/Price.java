package org.home.ec.data;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Price {
	
	@EmbeddedId
	private HourId id;
	
	@Column(name="price")
	private BigDecimal price;
	
	@OneToOne
    @MapsId("id")
	@JoinColumn(name="key_date", referencedColumnName="key_date")
    @JoinColumn(name="key_hour", referencedColumnName="key_hour")
	private Consumption consumption;
	
	public Price() {
		super();
	}

	public Price(HourId id, BigDecimal price) {
		super();
		this.id = id;
		this.price = price;
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

	public Consumption getConsumption() {
		return consumption;
	}

	public void setConsumption(Consumption consumption) {
		this.consumption = consumption;
	}
	
	
	
}
