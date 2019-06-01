package de.davelee.trams.vehicles.api;

/**
 * This class represents a train response.
 * @author Dave Lee
 */
public class TrainResponse extends VehicleResponse implements java.io.Serializable {

    private String operatingMode;

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

    /**
     * Return a string representation of this train consisting of the operating mode, type, livery and fleet number.
     * @return a <code>String</code> with the string representation.
     */
    public String toString () {
        String formattedRouteSchedule = "";
        if ( getAssignedRouteSchedule() != null ) {
            formattedRouteSchedule = getAssignedRouteSchedule();
        }
        return "Operating Mode: " + getOperatingMode() + ", Type: " + getType() + ", Livery: " + getLivery() + ", Fleet Number: " + getVehicleCompanyId() + ", Assigned Schedule: " +  formattedRouteSchedule;
    }

}
