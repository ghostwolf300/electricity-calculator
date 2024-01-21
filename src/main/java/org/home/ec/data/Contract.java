package org.home.ec.data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_contract")
public class Contract {
	
	@Id
	@Column(name="id")
	private long id;
	@Column(name="supplier")
	private String supplier;
	@Column(name="from_date")
	private Date fromDate;
	@Column(name="to_date")
	private Date toDate;
	@Column(name="margin")
	private BigDecimal margin;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Location location;
	
	public Contract() {
		super();
	}

	public Contract(long id, String supplier, Date fromDate, Date toDate, double margin) {
		super();
		this.id = id;
		this.supplier = supplier;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.margin = BigDecimal.valueOf(margin);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Contract))
			return false;
		Contract other = (Contract) obj;
		return id == other.id;
	}
	
	
	
	
}
