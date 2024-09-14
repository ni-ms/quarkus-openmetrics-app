package org.cactus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.cactus.entity.InformationSchemaTable;
import org.cactus.service.DatabaseService;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/database")
public class DatabaseResource {

    @Inject
    DatabaseService databaseService;

    @GET
    @Path("/information-schema/tables")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InformationSchemaTable> getPerformanceSchemaTables() {
        return databaseService.getInformationSchemaTables();
    }

    @GET
    @Path("/information-schema/status")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkInformationSchemaStatus() {
        boolean enabled = databaseService.isInformationSchemaEnabled();
        return enabled ? "Information Schema is enabled." : "Information Schema is not enabled.";
    }

    @GET
    @Path("/count/{table}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRowCount(@PathParam("table") String table) {
        int count = databaseService.getRowCount(table);
        return "Table " + table + " has " + count + " rows.";
    }

}
