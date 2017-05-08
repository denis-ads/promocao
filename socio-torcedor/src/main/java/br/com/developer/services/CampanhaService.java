package br.com.developer.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.developer.dao.CampanhaDao;
import br.com.developer.exception.DBException;
import br.com.developer.exception.ServiceException;
import br.com.developer.model.Campanha;

/**
 * DAO for Campanha
 */
@Stateless
public class CampanhaService {

    @Inject
	private CampanhaDao dao;

	public Campanha  create(Campanha entity) throws ServiceException {
		try {
		    return dao.create(entity);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
	}

	public void deleteById(Long id) throws ServiceException {
		Campanha entity;
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

	public Campanha findById(Long id) throws ServiceException {
		try {
            return dao.findById(id);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
	}

	public Campanha update(Campanha entity) throws ServiceException {
		try {
            return dao.update(entity);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
	}
	
	public List<Campanha> listAll(Integer startPosition, Integer maxResult) {
	    return dao.listAll(startPosition, maxResult);
	}
}
