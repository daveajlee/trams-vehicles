package de.davelee.trams.vehicles.data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a vehicle.
 * @author Dave Lee
 */
@MappedSuperclass
public class Vehicle {

    @EmbeddedId
    private VehicleIdentifier vehicleIdentifier;

    @Column
    private LocalDate deliveryDate;

    @Column
    private LocalDate inspectionDate;

    @Column
    private int seatingCapacity;

    @Column
    private int standingCapacity;

    @Column
    private String type;

    @Column
    private String livery;

    @Column
    private String assignedRouteSchedule;

    @Column
    private VehicleStatus vehicleStatus;

    @OneToMany(targetEntity=VehicleHistory.class, fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<VehicleHistory> vehicleHistoryList = new ArrayList<>();

    @ElementCollection
    private Map<LocalDate, Integer> vehicleHoursMap = new HashMap<>();

    /**
     * Get the vehicle identifier consisting of a fleet number and company.
     * @return a <code>VehicleIdentifier</code> object containing the identification data.
     */
    public VehicleIdentifier getVehicleIdentifier() {
        return vehicleIdentifier;
    }

    /**
     * Set the vehicle identifier consisting of a fleet number and company.
     * @param vehicleIdentifier a <code>VehicleIdentifier</code> object containing the identification data.
     */
    public void setVehicleIdentifier(final VehicleIdentifier vehicleIdentifier) {
        this.vehicleIdentifier = vehicleIdentifier;
    }

    /**
     * Get the delivery date of the vehicle.
     * @return a <code>LocalDate</code> containing the delivery date of the vehicle.
     */
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Set the delivery date of the vehicle.
     * @param deliveryDate a <code>LocalDate</code> containing the delivery date of the vehicle.
     */
    public void setDeliveryDate(final LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Get the last inspection date of this vehicle.
     * @return a <code>LocalDate</code> containing the last inspection date of this vehicle.
     */
    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    /**
     * Set the last inspection date of this vehicle.
     * @param inspectionDate containing the last inspection date of this vehicle.
     */
    public void setInspectionDate(final LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    /**
     * Get the seating capacity of this vehicle.
     * @return a <code>int</code> containing the seating capacity of this vehicle.
     */
    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    /**
     * Set the seating capacity of the vehicle.
     * @param seatingCapacity a <code>int</code> containing the seating capacity of this vehicle.
     */
    public void setSeatingCapacity(final int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    /**
     * Get the standing capacity of the vehicle.
     * @return a <code>int</code> containing the standing capacity of this vehicle.
     */
    public int getStandingCapacity() {
        return standingCapacity;
    }

    /**
     * Set the standing capacity of this vehicle.
     * @param standingCapacity a <code>int</code> containing the standing capacity of this vehicle.
     */
    public void setStandingCapacity(final int standingCapacity) {
        this.standingCapacity = standingCapacity;
    }

    /**
     * Get the type / model of this vehicle.
     * @return a <code>String</code> containing the type / model of this vehicle.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type / model of this vehicle.
     * @param type a <code>String</code> containing the type / model of this vehicle.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Get the livery which this vehicle has.
     * @return a <code>String</code> containing the livery which this vehicle has.
     */
    public String getLivery() {
        return livery;
    }

    /**
     * Set the livery which this vehicle has.
     * @param livery a <code>String</code> containing the livery which this vehicle has.
     */
    public void setLivery(final String livery) {
        this.livery = livery;
    }

    /**
     * Get the schedule id that this vehicle is currently assigned to.
     * @return a <code>String</code> containing the schedule id that this vehicle is currently assigned to.
     */
    public String getAssignedRouteSchedule() {
        return assignedRouteSchedule;
    }

    /**
     * Set the schedule id that this vehicle is currently assigned to.
     * @param assignedRouteSchedule a <code>String</code> containing the schedule id that this vehicle is currently assigned to.
     */
    public void setAssignedRouteSchedule(final String assignedRouteSchedule) {
        this.assignedRouteSchedule = assignedRouteSchedule;
    }

    /**
     * Get the current status of this vehicle e.g. delivered.
     * @return a <code>VehicleStatus</code> containing the current status of this vehicle.
     */
    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    /**
     * Set the current status of this vehicle.
     * @param vehicleStatus a <code>VehicleStatus</code> containing the current status of this vehicle.
     */
    public void setVehicleStatus(final VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    /**
     * Get the list of history entries for this vehicle.
     * @return a <code>List</code> of <code>VehicleHistory</code> objects containing the list of history entries for this vehicle.
     */
    public List<VehicleHistory> getVehicleHistoryList() {
        return vehicleHistoryList;
    }

    /**
     * Add a history entry to the list of history entries for this vehicle.
     * @param vehicleHistory a <code>VehicleHistory</code> containing the history entry to add.
     */
    public void addHistory(final VehicleHistory vehicleHistory) {
        this.vehicleHistoryList.add(vehicleHistory);
    }

    /**
     * This method increments the vehicle hours for the specified date by the specified hours.
     * @param date a <code>LocalDate</code> with the date that the hours were worked.
     * @param hours a <code>int</code> with the number of hours to increase by.
     */
    public void incrementVehicleHours ( final LocalDate date, final int hours ) {
        Integer currentHours = vehicleHoursMap.get(date);
        if ( currentHours == null ) {
            vehicleHoursMap.put(date, hours);
        } else {
            vehicleHoursMap.put(date, currentHours.intValue() + hours);
        }
    }

    /**
     * Retrieve the number of hours worked for this vehicle for the specified date.
     * @param date a <code>LocalDate</code> object representing the date to find hours worked for.
     * @return a <code>Integer</code> with the number of hours worked.
     */
    public Integer getHoursWorkedForDate ( final LocalDate date ) {
        return vehicleHoursMap.get(date);
    }

}
