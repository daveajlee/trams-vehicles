package de.davelee.trams.vehicles.services;

import de.davelee.trams.vehicles.data.Tram;
import de.davelee.trams.vehicles.data.VehicleHistory;
import de.davelee.trams.vehicles.data.VehicleIdentifier;
import de.davelee.trams.vehicles.data.VehicleStatus;
import de.davelee.trams.vehicles.repository.TramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * This class controls access to the tram database and calls appropriate operations.
 * @author Dave Lee
 */
@Service
public class TramService {

    @Autowired
    private TramRepository tramRepository;

    @Transactional
    /**
     * Add a tram to the database.
     * @param tram a <code>Tram</code> object containing the tram data to add to the database.
     */
    public Tram addTram ( final Tram tram ) {
        return tramRepository.save(tram);
    }

    /**
     * Return the number of trams in the database.
     * @return a <code>long</code> containing the number of trams.
     */
    public long getNumTrams ( ) {
        return tramRepository.count();
    }

    /**
     * Get the tram by fleet number and company.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number.
     * @param company a <code>String</code> containing the company.
     * @return a <code>Tram</code> object containing the tram object found or null if no tram is found.
     */
    public Tram findByVehicleCompanyIdAndCompany ( final int vehicleCompanyId, final String company ) {
        return tramRepository.findByVehicleIdentifier(new VehicleIdentifier(company, vehicleCompanyId));
    }

    /**
     * Return all trams for a company.
     * @param company a <code>String</code> containing the company.
     * @return a <code>List</code> of <code>Tram</code> objects belonging to the specified company.
     */
    public List<Tram> findByCompany (final String company ) {
        return tramRepository.findByVehicleIdentifierCompany(company);
    }

    /**
     * Find a tram with the assigned route schedule and company.
     * @param assignedRouteSchedule a <code>String</code> containing the assigned route schedule.
     * @param company a <code>String</code> containing the company.
     * @return a <code>Tram</code> object containing the tram object found or null if no tram is found.
     */
    public Tram findByAssignedRouteScheduleAndCompany(final String assignedRouteSchedule, final String company) {
        return tramRepository.findByAssignedRouteScheduleAndVehicleIdentifierCompany(assignedRouteSchedule, company);
    }

    @Transactional
    /**
     * Increment hours by a specified number for a specified tram.
     * @param tram a <code>Tram</code> object containing the tram to increment the hours for.
     * @param hours a <code>int</code> containing the hours to add.
     */
    public void incrementHours ( final Tram tram, final int hours ) {
        tram.incrementVehicleHours(LocalDate.now(), hours);
        tramRepository.saveAndFlush(tram);
    }

    @Transactional
    /**
     * Sell a tram by adding a comment to its history.
     * @param tram a <code>Tram</code> object to add the history to.
     */
    public void sellTram( final Tram tram ) {
        tram.setVehicleStatus(VehicleStatus.SOLD);
        VehicleHistory vehicleHistory = new VehicleHistory();
        vehicleHistory.setComment("Sold vehicle");
        vehicleHistory.setDate(LocalDate.now());
        vehicleHistory.setStatus(VehicleStatus.SOLD);
        tram.addHistory(vehicleHistory);
        tramRepository.saveAndFlush(tram);
    }

    @Transactional
    /**
     * Put a tram through an inspection by adding a comment to its history.
     * @param tram a <code>Tram</code> object to add the history to.
     */
    public void inspectTram( final Tram tram ) {
        tram.setInspectionDate(LocalDate.now());
        VehicleHistory vehicleHistory = new VehicleHistory();
        vehicleHistory.setComment("Vehicle Inspected");
        vehicleHistory.setDate(LocalDate.now());
        vehicleHistory.setStatus(VehicleStatus.INSPECTED);
        tram.addHistory(vehicleHistory);
        tramRepository.saveAndFlush(tram);
    }

    @Transactional
    /**
     * Assign a route schedule to a tram.
     * @param tram a <code>Tram</code> object to add the route schedule to.
     * @param routeSchedule a <code>String</code> containing the route schedule.
     */
    public void assignTram( final Tram tram, final String routeSchedule ) {
        tram.setAssignedRouteSchedule(routeSchedule);
        tramRepository.saveAndFlush(tram);
    }

    @Transactional
    /**
     * Remove the route schedule for the specified tram so that the tram is once again unallocated.
     * @param tram a <code>Tram</code> object to remove the route schedule from.
     */
    public void removeAllocation ( final Tram tram ) {
        tram.setAssignedRouteSchedule(null);
        tramRepository.saveAndFlush(tram);
    }

}
