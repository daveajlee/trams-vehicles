package de.davelee.trams.vehicles.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>BusRequest</code> class.
 * @author Dave Lee
 */
public class BusRequestTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        BusRequest busRequest = new BusRequest();
        busRequest.setRegistrationNumber("ZZZ22-333");
        assertEquals(busRequest.getRegistrationNumber(), "ZZZ22-333");
        busRequest.setDeliveryDate("07-09-2016");
        assertEquals(busRequest.getDeliveryDate(), "07-09-2016");
        busRequest.setSeatingCapacity(30);
        assertEquals(busRequest.getSeatingCapacity(), 30);
        busRequest.setStandingCapacity(5);
        assertEquals(busRequest.getStandingCapacity(), 5);
        busRequest.setVehicleCompanyId(12);
        assertEquals(busRequest.getVehicleCompanyId(), 12);
        busRequest.setCompany("Lee Buses");
        assertEquals(busRequest.getCompany(), "Lee Buses");
        busRequest.setType("Single Decker");
        assertEquals(busRequest.getType(), "Single Decker");
        busRequest.setLivery("LeeBusesAdvert");
        assertEquals(busRequest.getLivery(), "LeeBusesAdvert");
    }

}
