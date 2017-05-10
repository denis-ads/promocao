package br.com.developer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.developer.exception.DBException;
import br.com.developer.model.Campanha;
import br.com.developer.model.TimeCoracao;

/**
 * 
 */
@Stateless
public class CampanhaDao extends BaseDao<Campanha, Long>{

	public List<Campanha> listAll(Integer startPosition, Integer maxResult) {
		final TypedQuery<Campanha> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT c FROM Campanha c LEFT JOIN FETCH c.timeCoracao ORDER BY c.id",
						Campanha.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}

    public List<Campanha> consultarCampanhasAtivas(Date data, Integer startPosition, Integer maxResult) throws DBException {
        try {
            final Query namedQuery = em.createNamedQuery("Campanha.campanhasAtivas");
            namedQuery.setParameter("dataAtual", data);

            if (startPosition != null) {
                namedQuery.setFirstResult(startPosition);
            }
            if (maxResult != null) {
                namedQuery.setMaxResults(maxResult);
            }

            return namedQuery.getResultList();
        } catch (final Exception e) {
            throw new DBException(e);
        }
    }
    
    public List<Campanha> consultarCampanhasAtivasTimeId(Date data, Long timeCoracaoId, Integer startPosition, Integer maxResult) throws DBException {
        try {
            final Query namedQuery = em.createNamedQuery("Campanha.campanhasAtivasTimeCoracaoId");
            namedQuery.setParameter("dataAtual", data);
            namedQuery.setParameter("timeCoracaoId", timeCoracaoId);

            if (startPosition != null) {
                namedQuery.setFirstResult(startPosition);
            }
            if (maxResult != null) {
                namedQuery.setMaxResults(maxResult);
            }
            return namedQuery.getResultList();
        } catch (final Exception e) {
            throw new DBException(e);
        }
    }
    
    public List<Campanha> consultarCampanhasAtivasTimeCoracaoId(Date data, Long timeCoracaoId, Integer startPosition, Integer maxResult) throws DBException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Campanha> criteria = cb.createQuery(Campanha.class);
            Root<Campanha> root = criteria.from(Campanha.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (data != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("fimVigencia"), data));
            }

            if (timeCoracaoId!= null && timeCoracaoId != 0) {
                Join<Campanha, TimeCoracao> timeCoracaoJoin = root.join("timeCoracao");
                predicates.add(cb.equal(timeCoracaoJoin.get("id"), timeCoracaoId));
            }
            
            Predicate[] preds = new Predicate[predicates.size()];
            predicates.toArray(preds);
            criteria.select(root);
            criteria.where(preds);

             List<Campanha> campanhas = em.createQuery(criteria).getResultList();
             return campanhas;
        } catch (final Exception e) {
            throw new DBException(e);
        }
    }
    
}
