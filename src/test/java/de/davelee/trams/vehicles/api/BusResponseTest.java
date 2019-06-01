package de.davelee.trams.vehicles.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>BusResponse</code> class.
 * @author Dave Lee
 */
public class BusResponseTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        BusResponse busResponse = new BusResponse();
        busResponse.setRegistrationNumber("DE34567");
        assertEquals("DE34567", busResponse.getRegistrationNumber());
        busResponse.setVehicleCompanyId(101);
        assertEquals(101, busResponse.getVehicleCompanyId());
        busResponse.setDeliveryDate("24-07-2017");
        assertEquals("24-07-2017", busResponse.getDeliveryDate());
        busResponse.setSeatingCapacity(30);
        assertEquals(30, busResponse.getSeatingCapacity());
        busResponse.setStandingCapacity(60);
        assertEquals(60, busResponse.getStandingCapacity());
        busResponse.setLivery("Advert");
        assertEquals("Advert", busResponse.getLivery());
        busResponse.setType("SingleDecker");
        assertEquals("SingleDecker", busResponse.getType());
        busResponse.setCompany("DaveBuses");
        assertEquals("DaveBuses", busResponse.getCompany());
    }

}
