package br.com.senac.quarkus.projetofullstack.resource;

import br.com.senac.quarkus.projetofullstack.entity.PrecoDiarioAtivo;
import br.com.senac.quarkus.projetofullstack.service.PrecoDiarioAtivoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/precos-diarios-ativos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrecoDiarioAtivoResource {

    @Inject
    PrecoDiarioAtivoService precoDiarioAtivoService;

    @GET
    public List<PrecoDiarioAtivo> getAllPrecosDiariosAtivos() {
        return precoDiarioAtivoService.getAllPrecosDiariosAtivos();
    }

    @GET
    @Path("/{id}")
    public PrecoDiarioAtivo getPrecoDiarioAtivoById(@PathParam("id") Integer id) {
        return precoDiarioAtivoService.getPrecoDiarioAtivoById(id);
    }

    @POST
    public Response createPrecoDiarioAtivo(PrecoDiarioAtivo precoDiarioAtivo) {
        precoDiarioAtivoService.createPrecoDiarioAtivo(precoDiarioAtivo);
        return Response.accepted().build();
    }

    @PUT
    public Response updatePrecoDiarioAtivo(PrecoDiarioAtivo precoDiarioAtivo) {
        precoDiarioAtivoService.updatePrecoDiarioAtivo(precoDiarioAtivo);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePrecoDiarioAtivo(@PathParam("id") Integer id) {
        precoDiarioAtivoService.deletePrecoDiarioAtivo(id);
        return Response.accepted().build();
    }
}
