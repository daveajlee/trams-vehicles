package de.davelee.trams.vehicles.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the <code>TramRequest</code> class.
 * @author Dave Lee
 */
public class TramRequestTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        TramRequest tramRequest = new TramRequest();
        tramRequest.setBidirectional(true);
        assertTrue(tramRequest.isBidirectional());
        tramRequest.setDeliveryDate("07-09-2016");
        assertEquals(tramRequest.getDeliveryDate(), "07-09-2016");
        tramRequest.setSeatingCapacity(30);
        assertEquals(tramRequest.getSeatingCapacity(), 30);
        tramRequest.setStandingCapacity(40);
        assertEquals(tramRequest.getStandingCapacity(), 40);
        tramRequest.setVehicleCompanyId(4001);
        assertEquals(tramRequest.getVehicleCompanyId(), 4001);
        tramRequest.setCompany("Lee Trams");
        assertEquals(tramRequest.getCompany(), "Lee Trams");
        tramRequest.setType("ShortTram");
        assertEquals(tramRequest.getType(), "ShortTram");
        tramRequest.setLivery("LeeTramsAdvert");
        assertEquals(tramRequest.getLivery(), "LeeTramsAdvert");
    }

}
