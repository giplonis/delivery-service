package lt.vu.web.controller;

import io.swagger.v3.oas.annotations.Hidden;

import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RequestScoped
@Path("/api-doc")
@Hidden
public class ApiDocController {

    @Context
    private ServletContext servletContext;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public InputStream getIndexAction() throws IOException {
        return new FileInputStream(
            this.servletContext.getRealPath("/WEB-INF/classes/api-doc/index.html")
        );
    }

    @GET
    @Path("/{file}")
    public InputStream getFileAction(@PathParam("file") String file) throws IOException {
        return new FileInputStream(
            this.servletContext.getRealPath("/WEB-INF/classes/api-doc/" + file)
        );
    }
}
