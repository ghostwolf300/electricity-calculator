package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;

public class HourCost {
	
	private Date keyDate;
	private int keyHour;
	private double hourConsumption;
	private double minConsumption;
	private double maxConsumption;
	private BigDecimal price;
	private BigDecimal averagePrice;
	private BigDecimal hourCost;

	public HourCost() {
		super();
	}

	public HourCost(Date keyDate, int keyHour, double hourConsumption, double minConsumption, double maxConsumption,
			BigDecimal price, BigDecimal averagePrice, BigDecimal hourCost) {
		super();
		this.keyDate = keyDate;
		this.keyHour = keyHour;
		this.hourConsumption = hourConsumption;
		this.minConsumption = minConsumption;
		this.maxConsumption = maxConsumption;
		this.price = price;
		this.averagePrice = averagePrice;
		this.hourCost = hourCost;
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

	public double getHourConsumption() {
		return hourConsumption;
	}

	public void setHourConsumption(double hourConsumption) {
		this.hourConsumption = hourConsumption;
	}

	public double getMinConsumption() {
		return minConsumption;
	}

	public void setMinConsumption(double minConsumption) {
		this.minConsumption = minConsumption;
	}

	public double getMaxConsumption() {
		return maxConsumption;
	}

	public void setMaxConsumption(double maxConsumption) {
		this.maxConsumption = maxConsumption;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getHourCost() {
		return hourCost;
	}

	public void setHourCost(BigDecimal hourCost) {
		this.hourCost = hourCost;
	}

	
}
