package br.com.developer.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.developer.exception.DBException;
import br.com.developer.model.Usuario;

/**
 * DAO for Usuario
 */
@Stateless
public class UsuarioDao extends BaseDao<Usuario, Long> {

    public List<Usuario> listAll(Integer startPosition, Integer maxResult) {
        TypedQuery<Usuario> findAllQuery = em.createQuery("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.timeCoracao ORDER BY u.id",
                        Usuario.class);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        return findAllQuery.getResultList();
    }

    public Optional<Usuario> findByEmail(String email) throws DBException {
        try {
            final Query namedQuery = em.createNamedQuery("Usuario.findbyEmail");
            namedQuery.setParameter("email", email);
            Optional<Usuario> first = namedQuery.getResultList().stream().findFirst();
            return first;
            
        } catch (final Exception e) {
            throw new DBException(e);
        }
    }
}
