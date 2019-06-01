package de.davelee.trams.vehicles.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the <code>DeleteAssignRequest</code> class.
 * @author Dave Lee
 */
public class DeleteAssignRequestTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        DeleteAssignRequest deleteAssignRequest = new DeleteAssignRequest();
        deleteAssignRequest.setCompany("DaveBuses");
        assertEquals("DaveBuses", deleteAssignRequest.getCompany());
        deleteAssignRequest.setAssignedRouteSchedule("1/1");
        assertEquals("1/1", deleteAssignRequest.getAssignedRouteSchedule());
    }

}
