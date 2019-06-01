package de.davelee.trams.vehicles.api;

import java.util.List;

/**
 * This class represents a vehicle response in the TraMS Vehicle API.
 * @author Dave Lee
 */
public class VehicleResponse {

    private int vehicleCompanyId;
    private String company;
    private String deliveryDate;
    private String inspectionDate;
    private int seatingCapacity;
    private int standingCapacity;
    private String type;
    private String livery;
    private String assignedRouteSchedule;
    private String vehicleStatus;
    private List<VehicleHistoryResponse> vehicleHistoryList;

    /**
     * Get the fleet number of the vehicle.
     * @return a <code>int</code> containing the fleet number of the vehicle.
     */
    public int getVehicleCompanyId() {
        return vehicleCompanyId;
    }

    /**
     * Set the fleet number of the vehicle.
     * @param vehicleCompanyId a <code>int</code> containing the fleet number of the vehicle.
     */
    public void setVehicleCompanyId(final int vehicleCompanyId) {
        this.vehicleCompanyId = vehicleCompanyId;
    }

    /**
     * Get the company of the vehicle.
     * @return a <code>String</code> containing the company of the vehicle.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the company of the vehicle.
     * @param company a <code>String</code> containing the company of the vehicle.
     */
    public void setCompany(final String company) {
        this.company = company;
    }

    /**
     * Get the delivery date of the vehicle.
     * @return a <code>String</code> containing the delivery date of the vehicle.
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Set the delivery date of the vehicle.
     * @param deliveryDate a <code>String</code> containing the delivery date of the vehicle.
     */
    public void setDeliveryDate(final String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Get the last inspection date of this vehicle.
     * @return a <code>String</code> containing the last inspection date of this vehicle in form dd-MM-yyyy
     */
    public String getInspectionDate() {
        return inspectionDate;
    }

    /**
     * Set the last inspection date of this vehicle.
     * @param inspectionDate containing the last inspection date of this vehicle in form dd-MM-yyyy
     */
    public void setInspectionDate(final String inspectionDate) {
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
    public void setAssignedRouteSchedule(String assignedRouteSchedule) {
        this.assignedRouteSchedule = assignedRouteSchedule;
    }

    /**
     * Get the current status of this vehicle e.g. delivered.
     * @return a <code>String</code> containing the current status of this vehicle.
     */
    public String getVehicleStatus() {
        return vehicleStatus;
    }

    /**
     * Set the current status of this vehicle.
     * @param vehicleStatus a <code>String</code> containing the current status of this vehicle.
     */
    public void setVehicleStatus(final String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    /**
     * Get the list of history entries for this vehicle.
     * @return a <code>List</code> of <code>VehicleHistoryResponse</code> objects containing the list of history entries for this vehicle.
     */
    public List<VehicleHistoryResponse> getVehicleHistoryList() {
        return vehicleHistoryList;
    }

    /**
     * Set the list of history entries for this vehicle.
     * @param vehicleHistoryList a <code>List</code> of <code>VehicleHistoryResponse</code> objects containing the list of history entries for this vehicle.
     */
    public void setVehicleHistoryList(final List<VehicleHistoryResponse> vehicleHistoryList) {
        this.vehicleHistoryList = vehicleHistoryList;
    }

}
