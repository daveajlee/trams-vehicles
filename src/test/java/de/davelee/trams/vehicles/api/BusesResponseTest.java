package de.davelee.trams.vehicles.api;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

/**
 * Test the <code>BusResponses</code> class.
 * @author Dave Lee
 */
public class BusesResponseTest {

    @Test
    /**
     * Test case: it is possible to set and get the list of buses.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        BusesResponse busesResponse = new BusesResponse();
        busesResponse.setBusResponseList(new ArrayList<>());
        assertNotNull(busesResponse.getBusResponseList());
    }

}
