package de.davelee.trams.vehicles.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test the <code>CompanyData</code> class.
 * @author Dave Lee
 */
public class CompanyDataTest {

    @Test
    /**
     * Test case: it is possible to set and get all fields.
     * Expected result: all asserts are fulfilled.
     */
    public void testGettersAndSetters() {
        CompanyData companyData = new CompanyData();
        companyData.setCompanyName("DaveBuses");
        assertEquals("DaveBuses", companyData.getCompanyName());
        companyData.setBusResponseList(new ArrayList<>());
        assertNotNull(companyData.getBusResponseList());
    }
}
