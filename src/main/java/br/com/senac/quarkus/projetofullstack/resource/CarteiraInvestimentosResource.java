package br.com.senac.quarkus.projetofullstack.resource;

import br.com.senac.quarkus.projetofullstack.entity.CarteiraInvestimentos;
import br.com.senac.quarkus.projetofullstack.service.CarteiraInvestimentosService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/carteiras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarteiraInvestimentosResource {

    @Inject
    CarteiraInvestimentosService carteiraInvestimentosService;

    @GET
    public List<CarteiraInvestimentos> getAllCarteirasInvestimentos() {
        return carteiraInvestimentosService.getAllCarteirasInvestimentos();
    }

    @GET
    @Path("/{id}")
    public CarteiraInvestimentos getCarteiraInvestimentosById(@PathParam("id") Integer id) {
        return carteiraInvestimentosService.getCarteiraInvestimentosById(id);
    }

    @POST
    public Response createCarteiraInvestimentos(CarteiraInvestimentos carteiraInvestimentos) {
        carteiraInvestimentosService.createCarteiraInvestimentos(carteiraInvestimentos);
        return Response.accepted().build();
    }

    @PUT
    public Response updateCarteiraInvestimentos(CarteiraInvestimentos carteiraInvestimentos) {
        carteiraInvestimentosService.updateCarteiraInvestimentos(carteiraInvestimentos);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCarteiraInvestimentos(@PathParam("id") Integer id) {
        carteiraInvestimentosService.deleteCarteiraInvestimentos(id);
        return Response.accepted().build();
    }
}
