
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


import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;

@Path("/usuario")
public class UsuarioResource {
	
	private UsuarioBO usr = new UsuarioBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioTO> buscar() {
		return usr.listar();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioTO buscar(@PathParam("id") int id) {
		return usr.listar(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(UsuarioTO user, @Context UriInfo uriInfo) {
		usr.inserir(user);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(user.getCodigo()));
		return Response.created(builder.build()).build();
	}
	
    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(UsuarioTO user, @PathParam("codigo") int codigo) {
    	user.setCodigo(codigo);
    	usr.atualiza(user);
    	return Response.ok().build();
    }
	
    @DELETE
    @Path("/{codigo}")
    public void excluir(@PathParam("codigo") int codigo) {
    	usr.apagar(codigo);
    }
}
