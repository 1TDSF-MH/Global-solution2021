package br.com.fiap.resource;

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


import br.com.fiap.bo.OngBO;
import br.com.fiap.to.OngTO;



@Path("/associacao")
public class OngResource {
	
	private OngBO ong = new OngBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OngTO> buscar() {
		return ong.listar();
	}
	
	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public OngTO buscar(@PathParam("codigo") int codigo) {
		return ong.listar(codigo);
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response cadastrar(UsuarioTO produto, @Context UriInfo uriInfo) {
//		usr.inserir(produto);
//		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
//		builder.path(produto.getCnpj());
//		return Response.created(builder.build()).build();
//	}
//	
//    @PUT
//    @Path("/{cnpj}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response atualizar(UsuarioTO produto, @PathParam("cnpj") String cnpj) {
//    	produto.setCnpj(cnpj);
//    	usr.atualiza(produto);
//    	return Response.ok().build();
//    }
//	
//    @DELETE
//    @Path("/{codigo}")
//    public void excluir(@PathParam("codigo") int codigo) {
//    	usr.apagar(codigo);
//    }
}
