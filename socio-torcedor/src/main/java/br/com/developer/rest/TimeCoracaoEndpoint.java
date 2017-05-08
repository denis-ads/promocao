package br.com.developer.rest;

import java.util.logging.Logger;

import javax.ejb.Stateless;
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
import javax.ws.rs.core.UriBuilder;

import br.com.developer.exception.ServiceException;
import br.com.developer.model.TimeCoracao;
import br.com.developer.services.TimeCoracaoService;

/**
 * 
 */
@Path("/timecoracao")
public class TimeCoracaoEndpoint {

    @Inject
    private TimeCoracaoService timeCoracaoService;

    private Logger LOGGER = Logger.getLogger("TimeCoracaoEndpoint");

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TimeCoracao entity) {
        try {
            entity = timeCoracaoService.create(entity);
            return Response.created(UriBuilder.fromResource(TimeCoracaoEndpoint.class).path(String.valueOf(entity.getId())).build())
                            .build();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            timeCoracaoService.deleteById(id);
            return Response.noContent().build();

        } catch (ServiceException e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        TimeCoracao entity;
        try {
            entity = timeCoracaoService.findById(id);

            if (entity == null) {
                return Response.status(Status.NO_CONTENT).build();
            }
            return Response.ok(entity).build();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
        try {
            return Response.ok(timeCoracaoService.listAll(startPosition, maxResult)).build();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, TimeCoracao entity) {
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

            entity = timeCoracaoService.update(entity);
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
        } catch (ServiceException e) {
            LOGGER.severe(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.noContent().build();
    }
}
    
//    @PersistenceContext(unitName = "campaign-persistence-unit")
//	private EntityManager em;
//
//	@POST
//	@Consumes("application/json")
//	public Response create(TimeCoracao entity) {
//		em.persist(entity);
//		return Response.created(
//				UriBuilder.fromResource(TimeCoracaoEndpoint.class)
//						.path(String.valueOf(entity.getId())).build()).build();
//	}
//
//	@DELETE
//	@Path("/{id:[0-9][0-9]*}")
//	public Response deleteById(@PathParam("id") Long id) {
//		TimeCoracao entity = em.find(TimeCoracao.class, id);
//		if (entity == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		em.remove(entity);
//		return Response.noContent().build();
//	}
//
//	@GET
//	@Path("/{id:[0-9][0-9]*}")
//	@Produces("application/json")
//	public Response findById(@PathParam("id") Long id) {
//		TypedQuery<TimeCoracao> findByIdQuery = em
//				.createQuery(
//						"SELECT DISTINCT t FROM TimeCoracao t WHERE t.id = :entityId ORDER BY t.id",
//						TimeCoracao.class);
//		findByIdQuery.setParameter("entityId", id);
//		TimeCoracao entity;
//		try {
//			entity = findByIdQuery.getSingleResult();
//		} catch (NoResultException nre) {
//			entity = null;
//		}
//		if (entity == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		return Response.ok(entity).build();
//	}
//
//	@GET
//	@Produces("application/json")
//	public List<TimeCoracao> listAll(
//			@QueryParam("start") Integer startPosition,
//			@QueryParam("max") Integer maxResult) {
//		TypedQuery<TimeCoracao> findAllQuery = em.createQuery(
//				"SELECT DISTINCT t FROM TimeCoracao t ORDER BY t.id",
//				TimeCoracao.class);
//		if (startPosition != null) {
//			findAllQuery.setFirstResult(startPosition);
//		}
//		if (maxResult != null) {
//			findAllQuery.setMaxResults(maxResult);
//		}
//		final List<TimeCoracao> results = findAllQuery.getResultList();
//		return results;
//	}
//
//	@PUT
//	@Path("/{id:[0-9][0-9]*}")
//	@Consumes("application/json")
//	public Response update(@PathParam("id") Long id, TimeCoracao entity) {
//		if (entity == null) {
//			return Response.status(Status.BAD_REQUEST).build();
//		}
//		if (id == null) {
//			return Response.status(Status.BAD_REQUEST).build();
//		}
//		if (!id.equals(entity.getId())) {
//			return Response.status(Status.CONFLICT).entity(entity).build();
//		}
//		if (em.find(TimeCoracao.class, id) == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		try {
//			entity = em.merge(entity);
//		} catch (OptimisticLockException e) {
//			return Response.status(Response.Status.CONFLICT)
//					.entity(e.getEntity()).build();
//		}
//
//		return Response.noContent().build();
//	}
//}
