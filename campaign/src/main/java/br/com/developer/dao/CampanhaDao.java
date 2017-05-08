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
            // return em.createQuery(" SELECT o FROM " + objectClass.getSimpleName() + " AS o ",
            // objectClass).getResultList();
            return namedQuery.getResultList();
        } catch (final Exception e) {
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

	// public void create(Campanha entity) {
//  em.persist(entity);
//}

//public void deleteById(Long id) {
//  Campanha entity = em.find(Campanha.class, id);
//  if (entity != null) {
//      em.remove(entity);
//  }
//}

//public Campanha findById(Long id) {
//  return em.find(Campanha.class, id);
//}

//public Campanha update(Campanha entity) {
//  return em.merge(entity);
//}

//  TypedQuery<Campanha> findByIdQuery = em
//          .createQuery(
//                  "SELECT DISTINCT c FROM Campanha c LEFT JOIN FETCH c.timeCoracao WHERE c.id = :entityId ORDER BY c.id",
//                  Campanha.class);
//  findByIdQuery.setParameter("entityId", id);

//  public List<Campanha> listAll(@QueryParam("start") Integer startPosition,
//  @QueryParam("max") Integer maxResult) {
//
//TypedQuery<Campanha> findAllQuery = em
//      .createQuery(
//              "SELECT DISTINCT c FROM Campanha c LEFT JOIN FETCH c.timeCoracao ORDER BY c.id",
//              Campanha.class);
//if (startPosition != null) {
//  findAllQuery.setFirstResult(startPosition);
//}
//if (maxResult != null) {
//  findAllQuery.setMaxResults(maxResult);
//}
//final List<Campanha> results = findAllQuery.getResultList();
//return results;

}
