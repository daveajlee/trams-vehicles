package de.davelee.trams.vehicles.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>TrainRequest</code> class.
 * @author Dave Lee
 */
public class TrainRequestTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        TrainRequest trainRequest = new TrainRequest();
        trainRequest.setOperatingMode("Diesel");
        assertEquals(trainRequest.getOperatingMode(), "Diesel");
        trainRequest.setDeliveryDate("07-09-2016");
        assertEquals(trainRequest.getDeliveryDate(), "07-09-2016");
        trainRequest.setSeatingCapacity(100);
        assertEquals(trainRequest.getSeatingCapacity(), 100);
        trainRequest.setStandingCapacity(50);
        assertEquals(trainRequest.getStandingCapacity(), 50);
        trainRequest.setVehicleCompanyId(1542);
        assertEquals(trainRequest.getVehicleCompanyId(), 1542);
        trainRequest.setCompany("Lee Trains");
        assertEquals(trainRequest.getCompany(), "Lee Trains");
        trainRequest.setType("LR3466");
        assertEquals(trainRequest.getType(), "LR3466");
        trainRequest.setLivery("LeeTrainsAdvert");
        assertEquals(trainRequest.getLivery(), "LeeTrainsAdvert");
    }
}
