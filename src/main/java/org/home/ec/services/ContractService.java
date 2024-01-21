package org.home.ec.services;

import java.util.List;

import org.home.ec.data.Contract;

public interface ContractService {
	
	public List<Contract> getContracts(long locationId);
	public void addContract(Contract contract);
	public void removeContract(long contractId);
}
