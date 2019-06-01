package de.davelee.trams.vehicles.api;

import io.swagger.annotations.ApiModelProperty;

/**
 * This class represents a train request.
 * @author Dave Lee
 */
public class TrainRequest extends VehicleRequest {

    private String operatingMode;

    @ApiModelProperty(position=1, required=true, value="operating mode (electric, diesel) of the train")
    /**
     * Return the operating mode (electric, diesel) of the train.
     * @return a <code>String</code> containing the operating mode.
     */
    public String getOperatingMode() {
        return operatingMode;
    }

    /**
     * Set the operating mode (electric, diesel) of the train.
     * @param operatingMode a <code>String</code> containing the operating mode.
     */
    public void setOperatingMode(final String operatingMode) {
        this.operatingMode = operatingMode;
    }
}
