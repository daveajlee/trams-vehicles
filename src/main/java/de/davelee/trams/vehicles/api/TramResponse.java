package de.davelee.trams.vehicles.api;

/**
 * This class represents a tram response.
 * @author Dave Lee
 */
public class TramResponse extends VehicleResponse implements java.io.Serializable {

    private boolean bidirectional;

    /**
     * Return a boolean value whether or not the tram is bidirectional.
     * @return a <code>boolean</code> which is true iff the tram is bidirectional.
     */
    public boolean isBidirectional() {
        return bidirectional;
    }

    /**
     * Set a boolean value whether or not the tram is bidirectional.
     * @param bidirectional a <code>boolean</code> which is true iff the tram is bidirectional.
     */
    public void setBidirectional(final boolean bidirectional) {
        this.bidirectional = bidirectional;
    }

    /**
     * Return a string representation of this train consisting of bidirectional, type, livery and fleet number.
     * @return a <code>String</code> with the string representation.
     */
    public String toString () {
        String formattedRouteSchedule = "";
        if ( getAssignedRouteSchedule() != null ) {
            formattedRouteSchedule = getAssignedRouteSchedule();
        }
        return "Bidirectional: " + isBidirectional() + ", Type: " + getType() + ", Livery: " + getLivery() + ", Fleet Number: " + getVehicleCompanyId() + ", Assigned Schedule: " +  formattedRouteSchedule;
    }

}
