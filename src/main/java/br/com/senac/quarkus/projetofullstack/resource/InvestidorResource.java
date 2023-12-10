package br.com.senac.quarkus.projetofullstack.resource;

import br.com.senac.quarkus.projetofullstack.entity.Investidor;
import br.com.senac.quarkus.projetofullstack.service.InvestidorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/investidores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvestidorResource {

    @Inject
    InvestidorService investidorService;

    @GET
    public List<Investidor> getAllInvestidores() {
        return investidorService.getAllInvestidores();
    }

    @GET
    @Path("/{id}")
    public Investidor getInvestidorById(@PathParam("id") Integer id) {
        return investidorService.getInvestidorById(id);
    }

    @POST
    public Response createInvestidor(Investidor investidor) {
        investidorService.createInvestidor(investidor);
        return Response.accepted().build();
    }

    @PUT
    public Response updateInvestidor(Investidor investidor) {
        investidorService.updateInvestidor(investidor);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteInvestidor(@PathParam("id") Integer id) {
        investidorService.deleteInvestidor(id);
        return Response.accepted().build();
    }
}
