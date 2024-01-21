package org.home.ec.services;

import java.util.List;

import org.home.ec.data.Location;

public interface LocationService {
	public List<Location> getLocations();
	public void addLocation(Location location);
	public void removeLocation(Location location);
}
