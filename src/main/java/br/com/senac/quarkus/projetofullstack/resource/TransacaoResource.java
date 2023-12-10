package br.com.senac.quarkus.projetofullstack.resource;

import br.com.senac.quarkus.projetofullstack.entity.Transacao;
import br.com.senac.quarkus.projetofullstack.entity.TransacaoQuantidade;
import br.com.senac.quarkus.projetofullstack.service.TransacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/transacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransacaoResource {

    @Inject
    TransacaoService transacaoService;

    @GET
    public List<Transacao> getAllTransacoes() {
        return transacaoService.getAllTransacoes();
    }

    @GET
    @Path("/{id}")
    public Transacao getTransacaoById(@PathParam("id") Integer id) {
        return transacaoService.getTransacaoById(id);
    }

    @POST
    public Response createTransacao(Transacao transacao) {
        transacaoService.createTransacao(transacao);
        return Response.accepted().build();
    }

    @PUT
    public Response updateTransacao(Transacao transacao) {
        transacaoService.updateTransacao(transacao);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTransacao(@PathParam("id") Integer id) {
        transacaoService.deleteTransacao(id);
        return Response.accepted().build();
    }

    @GET
    @Path("/byCarteiraAndAtivo/{carteira}/{ativo}")
    public List<Transacao> getTransacoesByCarteiraAndAtivo (@PathParam("carteira") Integer carteira, @PathParam("ativo") Integer ativo) {
        List<Transacao> response = transacaoService.getTransacoesByCarteiraAndAtivo(carteira, ativo);
        return response;
    }

    @GET
    @Path("/byCarteira/{carteira}")
    public List<Transacao> getTransacoesByCarteira (@PathParam("carteira") Integer carteira) {
        List<Transacao> response = transacaoService.getTransacoesByCarteira(carteira);
        return response;
    }

    @GET
    @Path("/quantidadeAtivoByCarteira/{carteira}")
    public List<TransacaoQuantidade> getTransacoesQuantidadeByCarteira (@PathParam("carteira") Integer carteira) {
        List<TransacaoQuantidade> response = transacaoService.getTransacoesQuantidadeByCarteira(carteira);
        return response;
    }
}
