package de.davelee.trams.vehicles.api;

import io.swagger.annotations.ApiModelProperty;

/**
 * This class represents a vehicle request in the TraMS Vehicle API.
 * @author Dave Lee
 */
public abstract class VehicleRequest {

    private int vehicleCompanyId;

    private String company;

    private String deliveryDate;

    private int seatingCapacity;

    private int standingCapacity;

    private String type;

    private String livery;

    @ApiModelProperty(position=2, required=true, value="the company id for this vehicle")
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
    public void setVehicleCompanyId(int vehicleCompanyId) {
        this.vehicleCompanyId = vehicleCompanyId;
    }

    @ApiModelProperty(position=3, required=true, value="the company for this vehicle")
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
    public void setCompany(String company) {
        this.company = company;
    }

    @ApiModelProperty(position=4, required=true, value="the delivery date for this vehicle in format: dd-MM-yyyy")
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

    @ApiModelProperty(position=5, required=true, value="seating capacity for this vehicle")
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

    @ApiModelProperty(position=6, required=true, value="standing capacity for this vehicle")
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

    @ApiModelProperty(position=7, required=true, value="type for this vehicle")
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

    @ApiModelProperty(position=8, required=true, value="livery for this vehicle")
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
}
