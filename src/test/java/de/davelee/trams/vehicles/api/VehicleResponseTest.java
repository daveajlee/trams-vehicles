package de.davelee.trams.vehicles.api;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test the <code>VehicleResponse</code> class.
 * @author Dave Lee
 */
public class VehicleResponseTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setAssignedRouteSchedule("1/1");
        vehicleResponse.setCompany("DaveBuses");
        assertEquals("DaveBuses", vehicleResponse.getCompany());
        vehicleResponse.setDeliveryDate("24-07-2017");
        assertEquals("24-07-2017", vehicleResponse.getDeliveryDate());
        vehicleResponse.setInspectionDate("26-07-2017");
        assertEquals("26-07-2017", vehicleResponse.getInspectionDate());
        vehicleResponse.setLivery("Advert");
        assertEquals("Advert", vehicleResponse.getLivery());
        vehicleResponse.setSeatingCapacity(50);
        assertEquals(50, vehicleResponse.getSeatingCapacity());
        vehicleResponse.setStandingCapacity(30);
        assertEquals(30, vehicleResponse.getStandingCapacity());
        vehicleResponse.setType("SingleDecker");
        assertEquals("SingleDecker", vehicleResponse.getType());
        vehicleResponse.setVehicleCompanyId(101);
        assertEquals(101, vehicleResponse.getVehicleCompanyId());
        vehicleResponse.setVehicleStatus("Delivered");
        assertEquals("Delivered", vehicleResponse.getVehicleStatus());
        vehicleResponse.setVehicleHistoryList(new ArrayList<>());
        assertNotNull(vehicleResponse.getVehicleHistoryList());
    }

}
