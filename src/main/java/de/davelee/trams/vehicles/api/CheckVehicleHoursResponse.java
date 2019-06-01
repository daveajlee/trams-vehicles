package de.davelee.trams.vehicles.api;

/**
 * This class represents a check vehicle hours response to be returned by the Rest API.
 * @author Dave Lee
 */
public class CheckVehicleHoursResponse {

    private boolean furtherHoursAllowed;

    private int remainingHours;

    /**
     * Return a boolean value indicating whether or not the driver may work further hours.
     * @return a <code>boolean</code> which is true iff the driver may work further hours.
     */
    public boolean isFurtherHoursAllowed() {
        return furtherHoursAllowed;
    }

    /**
     * Set a boolean value indicating whether or not the driver may work further hours.
     * @param furtherHoursAllowed a <code>boolean</code> which is true iff the driver may work further hours.
     */
    public void setFurtherHoursAllowed(final boolean furtherHoursAllowed) {
        this.furtherHoursAllowed = furtherHoursAllowed;
    }

    /**
     * Get the number of hours that a driver may still work.
     * @return a <code>int</code> containing the number of hours that a driver may still work.
     */
    public int getRemainingHours() {
        return remainingHours;
    }

    /**
     * Set the number of hours that a driver may still work.
     * @param remainingHours a <code>int</code> containing the number of hours that a driver may still work.
     */
    public void setRemainingHours(final int remainingHours) {
        this.remainingHours = remainingHours;
    }
}
