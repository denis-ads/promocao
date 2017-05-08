package br.com.developer.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.developer.dao.UsuarioDao;
import br.com.developer.exception.DBException;
import br.com.developer.exception.ServiceException;
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
        } catch (DBException e) {
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
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    public Usuario findById(Long id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    public Usuario update(Usuario entity) throws ServiceException {
        try {
            return dao.update(entity);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    public List<Usuario> listAll(Integer startPosition, Integer maxResult) {
        return dao.listAll(startPosition, maxResult);
    }
}
