package org.home.ec.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsumptionRepository extends JpaRepository<Consumption, HourId>,JpaSpecificationExecutor<Consumption> {

}
