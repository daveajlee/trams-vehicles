package de.davelee.trams.vehicles.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>VehicleAssignRequest</code> class.
 * @author Dave Lee
 */
public class VehicleAssignRequestTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        VehicleAssignRequest vehicleAssignRequest = new VehicleAssignRequest();
        vehicleAssignRequest.setVehicleCompanyId(101);
        assertEquals(101, vehicleAssignRequest.getVehicleCompanyId());
        vehicleAssignRequest.setCompany("DaveBuses");
        assertEquals("DaveBuses", vehicleAssignRequest.getCompany());
        vehicleAssignRequest.setAssignedRouteSchedule("1/1");
        assertEquals("1/1", vehicleAssignRequest.getAssignedRouteSchedule());
    }

}
