package br.com.developer.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.developer.exception.ServiceException;
import br.com.developer.model.Campanha;
import br.com.developer.model.Usuario;
import br.com.developer.services.UsuarioService;

/**
 *
 */
@Path("/usuarios")
public class UsuarioEndpoint {

    @Inject
    private UsuarioService usuarioService;

    private final Logger LOGGER = Logger.getLogger("UsuarioEndpoint");

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Usuario entity) {
        try {
            List<Campanha> campanhas = usuarioService.cadastrarUsuario(entity);
            return Response.ok(campanhas).build();
            //return Response.created(UriBuilder.fromResource(UsuarioEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
        } catch (final Exception e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            usuarioService.deleteById(id);
            return Response.noContent().build();

        } catch (final ServiceException e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.NOT_FOUND).build();
        } catch (final Exception e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Usuario entity;
        try {
            entity = usuarioService.findById(id);

            if (entity == null) {
                return Response.status(Status.NO_CONTENT).build();
            }
            return Response.ok(entity).build();
        } catch (final Exception e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
        try {
            System.out.println("entrou denis");
            return Response.ok(usuarioService.listAll(startPosition, maxResult)).build();
        } catch (final Exception e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Usuario entity) {
        if (entity == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (id == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (!id.equals(entity.getId())) {
            return Response.status(Status.CONFLICT).entity(entity).build();
        }

        try {

            entity = usuarioService.update(entity);
        } catch (final OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
        } catch (final ServiceException e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.noContent().build();
    }

}
