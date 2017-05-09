package br.com.developer.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.developer.model.TimeCoracao;

/**
 * 
 */
@Stateless
public class TimeCoracaoDao extends BaseDao<TimeCoracao, Long>{
	
    public List<TimeCoracao> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<TimeCoracao> findAllQuery = em.createQuery(
				"SELECT DISTINCT t FROM TimeCoracao t ORDER BY t.id",
				TimeCoracao.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
