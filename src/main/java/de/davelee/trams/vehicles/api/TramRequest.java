package de.davelee.trams.vehicles.api;

import io.swagger.annotations.ApiModelProperty;

/**
 * This class represents a tram request.
 * @author Dave Lee
 */
public class TramRequest extends VehicleRequest {

    private boolean bidirectional;

    @ApiModelProperty(position=1, required=true, value="whether or not the tram is bidirectional")
    /**
     * Return a boolean value whether or not the tram is bidirectional.
     * @return a <code>boolean</code> which is true iff the tram is bidirectional.
     */
    public boolean isBidirectional() {
        return bidirectional;
    }

    /**
     * Set a boolean value whether or not the tram is bidirectional.
     * @param bidirectional a <code>boolean</code> which is true iff the tram is bidirectional.
     */
    public void setBidirectional(final boolean bidirectional) {
        this.bidirectional = bidirectional;
    }
}
