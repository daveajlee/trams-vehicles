package de.davelee.trams.vehicles.repository;

import de.davelee.trams.vehicles.data.Tram;
import de.davelee.trams.vehicles.data.VehicleIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This class represents the database operations automatically generated using Spring Data JPA.
 * @author Dave Lee
 */
public interface TramRepository extends JpaRepository<Tram, Long> {

    /**
     * Return a tram matching the vehicle identifier made of fleet number and company.
     * @param vehicleIdentifier a <code>VehicleIdentifier</code> containing the vehicle identifier that the tram should have.
     * @return a <code>Tram</code> object representing the tram which may be null if no tram found.
     */
    public Tram findByVehicleIdentifier (VehicleIdentifier vehicleIdentifier);

    /**
     * Return filtered trams based on their company.
     * @param company a <code>String</code> containing the company to search trams for.
     * @return a <code>Tram</code> list representing the trams which may be null if no trams found.
     */
    public List<Tram> findByVehicleIdentifierCompany(String company);

    /**
     * Return a tram matching the company and assigned route schedule.
     * @param assignedRouteSchedule a <code>String</code> containing the assigned route schedule.
     * @param company a <code>String</code> containing the company to search trams for.
     * @return a <code>Tram</code> object representing the tram which may be null if no tram found.
     */
    public Tram findByAssignedRouteScheduleAndVehicleIdentifierCompany(String assignedRouteSchedule, String company);

}
