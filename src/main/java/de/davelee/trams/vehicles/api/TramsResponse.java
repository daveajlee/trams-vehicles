package de.davelee.trams.vehicles.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Container class for a number of TramResponse objects.
 * @author Dave Lee
 */
public class TramsResponse {

    public List<TramResponse> tramResponseList = new ArrayList<>();

    /**
     * Return a list of TramResponse objects contained in this object.
     * @return a <code>List</code> of <code>TramResponse</code> objects.
     */
    public List<TramResponse> getTramResponseList() {
        return tramResponseList;
    }

    /**
     * Set a list of TramResponse objects contained in this object.
     * @param tramResponseList a <code>List</code> of <code>TramResponse</code> objects.
     */
    public void setTramResponseList(final List<TramResponse> tramResponseList) {
        this.tramResponseList = tramResponseList;
    }

    /**
     * Add a new TramResponse object to this container.
     * @param tramResponse a <code>TramResponse</code> object to add.
     */
    public void addTramResponse ( final TramResponse tramResponse) {
        tramResponseList.add(tramResponse);
    }

}
