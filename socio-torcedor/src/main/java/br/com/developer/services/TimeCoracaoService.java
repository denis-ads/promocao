package br.com.developer.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.developer.dao.TimeCoracaoDao;
import br.com.developer.exception.DBException;
import br.com.developer.exception.ServiceException;
import br.com.developer.model.TimeCoracao;

/**
 * DAO for TimeCoracao
 */
@Stateless
public class TimeCoracaoService {
    
    @Inject
    private TimeCoracaoDao dao;

    public TimeCoracao  create(TimeCoracao entity) throws ServiceException {
        try {
            return dao.create(entity);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteById(Long id) throws ServiceException {
        TimeCoracao entity;
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

    public TimeCoracao findById(Long id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    public TimeCoracao update(TimeCoracao entity) throws ServiceException {
        try {
            return dao.update(entity);
        } catch (DBException e) {
            throw new ServiceException(e);
        }
    }

    public List<TimeCoracao> listAll(Integer startPosition, Integer maxResult) {
        return dao.listAll(startPosition, maxResult);
    }
}
