package org.home.ec.data;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="t_location")
public class Location {
	
	@Id
	@Column(name="id")
	private long id;
	@Column(name="street")
	private String street;
	@Column(name="postcode")
	private String postcode;
	@Column(name="city")
	private String city;
	
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Contract> contracts;
	
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Consumption> consumption;
	
	public Location() {
		super();
	}
	
	public Location(long id) {
		super();
		this.id=id;
	}

	public Location(long id, String street, String postcode, String city) {
		super();
		this.id = id;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", " + (street != null ? "street=" + street + ", " : "")
				+ (postcode != null ? "postcode=" + postcode + ", " : "") + (city != null ? "city=" + city : "") + "]";
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	public void addContract(Contract contract) {
		contracts.add(contract);
		contract.setLocation(this);
	}
	
	public void removeContract(Contract contract) {
		contracts.remove(contract);
		contract.setLocation(null);
	}
	
	
	
	

}
