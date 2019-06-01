package de.davelee.trams.vehicles.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class represents a request object for a bus in the TraMS Vehicle API.
 * @author Dave Lee
 */
@ApiModel
public class BusRequest extends VehicleRequest {

    private String registrationNumber;

    @ApiModelProperty(position=1, required=true, value="registration number of the bus")
    /**
     * Return the registration number for this bus.
     * @return a <code>String</code> object with the registration number.
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Set the registration number for this bus.
     * @param registrationNumber a <code>String</code> object with the registration number.
     */
    public void setRegistrationNumber(final String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
