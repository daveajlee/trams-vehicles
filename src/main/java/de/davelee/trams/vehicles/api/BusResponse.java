package de.davelee.trams.vehicles.api;

/**
 * This class represents a response object for a bus in the TraMS Vehicle API.
 * @author Dave Lee
 */
public class BusResponse extends VehicleResponse implements java.io.Serializable {

    private String registrationNumber;

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

    /**
     * Return a string representation of this bus consisting of the reg no, type, livery and fleet number.
     * @return a <code>String</code> with the string representation.
     */
    public String toString () {
        String formattedRouteSchedule = "";
        if ( getAssignedRouteSchedule() != null ) {
            formattedRouteSchedule = getAssignedRouteSchedule();
        }
        return "Registration Number: " + registrationNumber + ", Type: " + getType() + ", Livery: " + getLivery() + ", Fleet Number: " + getVehicleCompanyId() + ", Assigned Schedule: " +  formattedRouteSchedule;
    }

}
