package de.davelee.trams.vehicles.api;

/**
 * Class representing a request to delete an assignment in the TraMS Vehicle API.
 * @author Dave Lee
 */
public class DeleteAssignRequest {

    private String assignedRouteSchedule;
    private String company;

    /**
     * Return the route schedule for which the assignment should be removed.
     * @return a <code>String</code> containing the route schedule id.
     */
    public String getAssignedRouteSchedule() {
        return assignedRouteSchedule;
    }

    /**
     * Set the route schedule for which the assignment should be removed.
     * @param assignedRouteSchedule a <code>String</code> containing the route schedule id.
     */
    public void setAssignedRouteSchedule(final String assignedRouteSchedule) {
        this.assignedRouteSchedule = assignedRouteSchedule;
    }

    /**
     * Return the company for which the assignment should be removed.
     * @return a <code>String</code> containing the company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the company for which the assignment should be removed.
     * @param company a <code>String</code> containing the company.
     */
    public void setCompany(final String company) {
        this.company = company;
    }
}
