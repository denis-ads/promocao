package br.com.developer.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.developer.exception.DBException;
import br.com.developer.model.Campanha;

/**
 * DAO for Campanha
 */
@Stateless
public class CampanhaDao extends BaseDao<Campanha, Long>{
	
	public List<Campanha> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Campanha> findAllQuery = em
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
	
    public List<Campanha> consultarCampanhasAtivas() throws DBException {
        try {
            Query namedQuery = em.createNamedQuery("Campanha.campanhasAtivas");
            namedQuery.setParameter(":dataAtual", new Date());
            return namedQuery.getResultList();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
	
    /*public int countPorFiltro(MatriculaFilterDto filtro) throws DBException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
            Root<MatriculaE> root = criteria.from(MatriculaE.class);
            Predicate[] predicates = montarParametros(filtro, cb, root);

            criteria.select(cb.count(root));
            criteria.where(predicates);

            Long i = (Long) em.createQuery(criteria).getSingleResult();
            return i.intValue();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }*/

/*    private Predicate[] montarParametros(MatriculaFilterDto matriculaFilter, CriteriaBuilder cb, Root<MatriculaE> matriculaRoot) {
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if (matriculaFilter != null) {
            if (matriculaFilter.getId() != null && matriculaFilter.getId() != 0) {
                predicateList.add(cb.equal(matriculaRoot.get("pk"), matriculaFilter.getId()));
            }

            if (matriculaFilter.getIdCurso() != null && matriculaFilter.getIdCurso() != 0) {
                Join<MatriculaE, CursoE> cursoJoin = matriculaRoot.join("curso");
                predicateList.add(cb.equal(cursoJoin.get("pk"), matriculaFilter.getIdCurso()));
            }

            if (matriculaFilter.getIdEstudante() != null && matriculaFilter.getIdEstudante() != 0) {
                Join<MatriculaE, EstudanteE> estudanteJoin = matriculaRoot.join("estudante");
                predicateList.add(cb.equal(estudanteJoin.get("pk"), matriculaFilter.getIdEstudante()));
            }
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        return predicates;
    }*/
}
