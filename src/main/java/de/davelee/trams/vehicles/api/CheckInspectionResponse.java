package de.davelee.trams.vehicles.api;

/**
 * This class represents a check inspection response to be returned by the Rest API.
 * @author Dave Lee
 */
public class CheckInspectionResponse {

    private boolean inspectionDue;

    private long remainingDaysUntilNextInspection;

    /**
     * Return a boolean value indicating whether an inspection is due.
     * @return a <code>boolean</code> which is true iff an inspection is due.
     */
    public boolean isInspectionDue() {
        return inspectionDue;
    }

    /**
     * Set a boolean value indicating whether an inspection is due.
     * @param inspectionDue a <code>boolean</code> which is true iff an inspection is due.
     */
    public void setInspectionDue(final boolean inspectionDue) {
        this.inspectionDue = inspectionDue;
    }

    /**
     * Return the number of days until the next inspection.
     * @return a <code>long</code> containing the number of days until the next inspection.
     */
    public long getRemainingDaysUntilNextInspection() {
        return remainingDaysUntilNextInspection;
    }

    /**
     * Set the number of days until the next inspection.
     * @param remainingDaysUntilNextInspection a <code>long</code> containing the number of days until the next inspection.
     */
    public void setRemainingDaysUntilNextInspection(final long remainingDaysUntilNextInspection) {
        this.remainingDaysUntilNextInspection = remainingDaysUntilNextInspection;
    }
}
