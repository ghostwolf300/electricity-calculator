package org.home.ec.services;

import java.sql.Date;
import java.util.List;

import org.home.ec.data.Consumption;
import org.home.ec.data.ConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {
	
	@Autowired
	private ConsumptionRepository repository;
	
	@Override
	public List<Consumption> getConsumption(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateData(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		
	}

}
