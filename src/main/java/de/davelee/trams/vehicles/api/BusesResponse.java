package de.davelee.trams.vehicles.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Container class for a number of BusResponse objects.
 * @author Dave Lee
 */
public class BusesResponse {

    public List<BusResponse> busResponseList = new ArrayList<>();

    /**
     * Return a list of BusResponse objects contained in this object.
     * @return a <code>List</code> of <code>BusResponse</code> objects.
     */
    public List<BusResponse> getBusResponseList() {
        return busResponseList;
    }

    /**
     * Set a list of BusResponse objects contained in this object.
     * @param busResponseList a <code>List</code> of <code>BusResponse</code> objects.
     */
    public void setBusResponseList(final List<BusResponse> busResponseList) {
        this.busResponseList = busResponseList;
    }

    /**
     * Add a new BusResponse object to this container.
     * @param busResponse a <code>BusResponse</code> object to add.
     */
    public void addBusResponse ( final BusResponse busResponse) {
        busResponseList.add(busResponse);
    }

}
