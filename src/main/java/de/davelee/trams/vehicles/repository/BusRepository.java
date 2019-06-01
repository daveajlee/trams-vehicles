package de.davelee.trams.vehicles.repository;

import de.davelee.trams.vehicles.data.Bus;
import de.davelee.trams.vehicles.data.VehicleIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * This class represents the database operations automatically generated using Spring Data JPA.
 * @author Dave Lee
 */
public interface BusRepository extends JpaRepository<Bus, Long> {

    /**
     * Return a bus matching the vehicle identifier made of fleet number and company.
     * @param vehicleIdentifier a <code>VehicleIdentifier</code> containing the vehicle identifier that the bus should have.
     * @return a <code>Bus</code> object representing the bus which may be null if no bus found.
     */
    public Bus findByVehicleIdentifier(@Param("vehicleIdentifier") VehicleIdentifier vehicleIdentifier);

    /**
     * Return filtered buses based on their company.
     * @param company a <code>String</code> containing the company to search buses for.
     * @return a <code>Bus</code> list representing the buses which may be null if no buses found.
     */
    public List<Bus> findByVehicleIdentifierCompany(@Param("company") String company);

    /**
     * Return a bus matching the company and assigned route schedule.
     * @param assignedRouteSchedule a <code>String</code> containing the assigned route schedule.
     * @param company a <code>String</code> containing the company to search buses for.
     * @return a <code>Bus</code> object representing the bus which may be null if no bus found.
     */
    public Bus findByAssignedRouteScheduleAndVehicleIdentifierCompany(String assignedRouteSchedule, String company);

}

