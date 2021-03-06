/*
    See lda-top/LICENCE (or http://elda.googlecode.com/hg/LICENCE)
    for the licence for this software.
    
    (c) Copyright 2011 Epimorphics Limited
    $Id$
*/
package com.epimorphics.lda.restlets;

import com.epimorphics.lda.specmanager.SpecEntry;
import com.epimorphics.lda.specmanager.SpecManagerFactory;
import com.epimorphics.lda.support.pageComposition.ComposeConfigDisplay;
import com.epimorphics.util.Util;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/api-config")
public class ConfigRestlet {

    @GET
    @Produces("text/html")
    public Response generateConfigPage
            (@PathParam("path") String pathstub
                    , @Context ServletContext sc
                    , @Context UriInfo ui
            ) {
        URI base = ui.getBaseUri();
        /* result ignored */
        RouterRestlet.getRouterFor(sc);
        //
        List<SpecEntry> specs = SpecManagerFactory.allSpecs();
        String page = new ComposeConfigDisplay().configPageMentioning(specs, base, pathstub);
        return RouterRestlet.returnAs(Util.withBody("API configuration", page), "text/html");
    }

}
