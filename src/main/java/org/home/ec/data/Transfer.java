package org.home.ec.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="t_transfer")
public class Transfer {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price",precision=5,scale=3)
	private BigDecimal price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Contract contract;
	
	@OneToMany(mappedBy="transfer",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<TransferPeriod> periodsApplied;
	
	public Transfer() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<TransferPeriod> getPeriodsApplied() {
		return periodsApplied;
	}

	public void setPeriodsApplied(List<TransferPeriod> periodsApplied) {
		this.periodsApplied = periodsApplied;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Transfer))
			return false;
		Transfer other = (Transfer) obj;
		return id == other.id;
	}
	
}
