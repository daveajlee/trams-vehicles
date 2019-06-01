package de.davelee.trams.vehicles.repository;

import de.davelee.trams.vehicles.data.Train;
import de.davelee.trams.vehicles.data.VehicleIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This class represents the database operations automatically generated using Spring Data JPA.
 * @author Dave Lee
 */
public interface TrainRepository extends JpaRepository<Train, Long> {

    /**
     * Return a train matching the vehicle identifier made of fleet number and company.
     * @param vehicleIdentifier a <code>VehicleIdentifier</code> containing the vehicle identifier that the train should have.
     * @return a <code>Train</code> object representing the train which may be null if no train found.
     */
    public Train findByVehicleIdentifier (VehicleIdentifier vehicleIdentifier);

    /**
     * Return filtered trains based on their company.
     * @param company a <code>String</code> containing the company to search trains for.
     * @return a <code>Train</code> list representing the trains which may be null if no trains found.
     */
    public List<Train> findByVehicleIdentifierCompany(String company);

    /**
     * Return a train matching the company and assigned route schedule.
     * @param assignedRouteSchedule a <code>String</code> containing the assigned route schedule.
     * @param company a <code>String</code> containing the company to search trains for.
     * @return a <code>Train</code> object representing the train which may be null if no train found.
     */
    public Train findByAssignedRouteScheduleAndVehicleIdentifierCompany(String assignedRouteSchedule, String company);

}
