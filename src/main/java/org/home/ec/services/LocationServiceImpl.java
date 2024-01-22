package org.home.ec.services;

import java.util.List;

import org.home.ec.data.Location;
import org.home.ec.data.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository repository;
	
	public LocationServiceImpl() {
		super();
	}
	
	@Override
	public List<Location> getLocations() {
		List<Location> locations=repository.findAll();
		return locations;
	}

	@Override
	public void addLocation(Location location) {
		repository.saveAndFlush(location);
	}

	@Override
	public void removeLocation(Location location) {
		repository.delete(location);

	}

}
