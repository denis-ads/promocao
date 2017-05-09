package br.com.developer.services;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import br.com.developer.CampanhaCache;
import br.com.developer.dao.UsuarioDao;
import br.com.developer.exception.DBException;
import br.com.developer.exception.ServiceException;
import br.com.developer.model.Campanha;
import br.com.developer.model.TimeCoracao;
import br.com.developer.model.Usuario;

/**
 * 
 */
@Stateless
public class UsuarioService {

    @Inject
    private UsuarioDao dao;

    @Inject
    private TimeCoracaoService timeCoracaoService;

    public Usuario create(Usuario entity) throws ServiceException {
        try {

            return dao.create(entity);
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    public List<Campanha> cadastrarUsuario(Usuario entity) throws ServiceException {
        try { 
            TimeCoracao timeCoracao = timeCoracaoService.findById(entity.getTimeCoracao().getId());
            entity.setTimeCoracao(timeCoracao);
            Optional<Usuario> usuario = dao.findByEmail(entity.getEmail());
            List<Campanha> list = null;
            if(usuario.isPresent()){
                list = verificarCampanhasAssociadas(usuario);
            }else{
                create(entity);
                list = buscarCampanhasDisponiveis();
            }
            
            return list;
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Essa regra esta estranha, pois falta requisitos para associar usuario a uma campanha
     * 
     * @param usuario
     * @return List<Campanha>
     */
    private List<Campanha> buscarCampanhasDisponiveis() {
        
        final Client client = ClientBuilder.newBuilder().build();
        String string = "http://localhost:8082/campanha/rest/campanhas";
        final List<Campanha> list = client.target(string).request().accept(MediaType.APPLICATION_JSON).get(
                        new GenericType<List<Campanha>>() {});
        return list;
    }

    /**
     * Essa regra esta estranha, pois falta requisitos para associar usuario a uma campanha
     * 
     * @param usuario
     * @return List<Campanha>
     */
    private List<Campanha> verificarCampanhasAssociadas(Optional<Usuario> usuario) {
        
        List<Campanha> campanhas = CampanhaCache.getCampanhas(usuario.get().getTimeCoracao().getId());

        if(campanhas == null ){
            final Client client = ClientBuilder.newBuilder().build();
            String string = "http://localhost:8082/campanha/rest/campanhas?timeCoracaoId=" + usuario.get().getTimeCoracao().getId();
            campanhas = client.target(string).request().accept(MediaType.APPLICATION_JSON).get(
                            new GenericType<List<Campanha>>() {});
            
            CampanhaCache.adicionarCache(usuario.get().getTimeCoracao().getId(), campanhas);
        }
        
        return campanhas;
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
        return dao.listAll(startPosition, maxResult);
    }

}
