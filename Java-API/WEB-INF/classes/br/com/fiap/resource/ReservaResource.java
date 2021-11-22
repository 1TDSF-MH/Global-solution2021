package br.com.fiap.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.bo.ReservaBO;
import br.com.fiap.to.ProdutoTO;

@Path("/reserva")
public class ReservaResource {
	ReservaBO res = new ReservaBO();

	@GET
	@Path("/produtos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProdutoTO> buscar(@PathParam("id") int id) {
		return res.listarProdutos(id);
	}

	@GET
	@Path("/cdprodutos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<int[]> buscarCdProd(@PathParam("id") int id) {
		return res.listarDadosReserva(id);
	}

	@GET
	@Path("/resumo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String[]> mostrarResumo(@PathParam("id") int id) {
		return res.mostrarDadosResumo(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(ArrayList<int[]> reserva, @Context UriInfo uriInfo) {
		res.registrarReserva(reserva);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).build();
	}

	@PUT
	@Path("/update/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("codigo") int cdAssoc) {
		res.atualizarEstoque(cdAssoc);
		return Response.ok().build();
	}

	@DELETE
	@Path("/delete/{codigo}")
	public void excluir(@PathParam("codigo") int codigo) {
		res.cancelarReserva(codigo);
	}

}
