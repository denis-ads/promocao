package br.com.developer.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.developer.model.Usuario;

/**
 * DAO for Usuario
 */
@Stateless
public class UsuarioDao extends BaseDao<Usuario, Long>{
	
	public List<Usuario> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Usuario> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.timeCoracao ORDER BY u.id",
						Usuario.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
