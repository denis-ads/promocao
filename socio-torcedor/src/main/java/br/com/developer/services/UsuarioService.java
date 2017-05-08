package br.com.developer.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

//import org.jboss.resteasy.client.jaxrs.ResteasyClient;
//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
//import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.com.developer.dao.UsuarioDao;
import br.com.developer.exception.DBException;
import br.com.developer.exception.ServiceException;
import br.com.developer.model.Campanha;
import br.com.developer.model.Usuario;

/**
 * DAO for Usuario
 */
@Stateless
public class UsuarioService {

    @Inject
    private UsuarioDao dao;

    public Usuario  create(Usuario entity) throws ServiceException {
        try {
            return dao.create(entity);
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteById(Long id) throws ServiceException {
        Usuario entity;
        try {
            entity = dao.findById(id);
            if (entity == null) {
                throw new IllegalArgumentException("Objeto não encontrado");
            }
                dao.delete(entity);
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    public Usuario findById(Long id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    public Usuario update(Usuario entity) throws ServiceException {
        try {
            return dao.update(entity);
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    public List<Usuario> listAll(Integer startPosition, Integer maxResult) {
       /* try{
            System.out.println("denis");
            final ResteasyClient client = new ResteasyClientBuilder().build();
            final List<Campanha> list = client.target("http://localhost:8082/campaign/rest/campanhas").request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Campanha>>(){});
            for (final Campanha campanha : list) {
                System.out.println(campanha);
            }
        }catch (final Exception e) {
            e.printStackTrace();
        }*/
        try{
            System.out.println("denis");
            final Client client = ClientBuilder.newBuilder().build();
            final List<Campanha> list = client.target("http://localhost:8082/campaign/rest/campanhas").request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Campanha>>(){});
            for (final Campanha campanha : list) {
                System.out.println(campanha);
            }
        }catch (final Exception e) {
            e.printStackTrace();
        }
        return dao.listAll(startPosition, maxResult);
    }

  }
