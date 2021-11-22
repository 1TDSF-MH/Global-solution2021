package br.com.fiap.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	
	@GET
	@Path("/login/{email}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean verificarSenha(@PathParam("email") String email, @PathParam("senha") String senha) {
		return ong.validarEntrada(email, senha);
	}

	@GET
	@Path("/codigo/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public int mostrarCodigoOng(@PathParam("email") String email) {
		return ong.retornaCodigoOng(email);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(OngTO o, @Context UriInfo uriInfo) {
		ong.inserir(o);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(o.getCodigo()));
		return Response.created(builder.build()).build();
	}

}
