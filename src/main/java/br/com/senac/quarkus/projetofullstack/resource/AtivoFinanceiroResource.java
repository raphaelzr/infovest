package br.com.senac.quarkus.projetofullstack.resource;

import br.com.senac.quarkus.projetofullstack.entity.AtivoFinanceiro;
import br.com.senac.quarkus.projetofullstack.service.AtivoFinanceiroService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/ativos-financeiros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AtivoFinanceiroResource {

    @Inject
    AtivoFinanceiroService ativoFinanceiroService;

    @GET
    public List<AtivoFinanceiro> getAllAtivosFinanceiros() {
        return ativoFinanceiroService.getAllAtivosFinanceiros();
    }

    @GET
    @Path("/{id}")
    public AtivoFinanceiro getAtivoFinanceiroById(@PathParam("id") Integer id) {
        return ativoFinanceiroService.getAtivoFinanceiroById(id);
    }

    @POST
    public Response createAtivoFinanceiro(AtivoFinanceiro ativo) {
        ativoFinanceiroService.createAtivoFinanceiro(ativo);
        return Response.accepted().build();
    }

    @PUT
    public Response updateAtivoFinanceiro(AtivoFinanceiro ativo) {
        ativoFinanceiroService.updateAtivoFinanceiro(ativo);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAtivoFinanceiro(@PathParam("id") Integer id) {
        ativoFinanceiroService.deleteAtivoFinanceiro(id);
        return Response.accepted().build();
    }
}