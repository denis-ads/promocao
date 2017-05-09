
package br.com.developer.dao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.developer.exception.DBException;

/**
 * Implementacao generica abstrata de persistencia de entidades utilizando JPA.
 */
public abstract class BaseDao<T, K> {

    private static final String SELECT_ALL = ".selectAll";
    //private static final String COUNT = ".count";

    @PersistenceContext(unitName = "campanha-persistence-unit")
    protected EntityManager em;

    public void flush() {
        this.em.flush();
    }

    public void delete(T objeto) throws DBException {
        try {
            em.remove(objeto);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public T create(T objeto) throws DBException {
        try {
            em.persist(objeto);
        } catch (Exception e) {
            throw new DBException(e);
        }

        return objeto;
    }

    public T update(T objeto) throws DBException {
        T bean = null;
        try {
            bean = em.merge(objeto);
        } catch (Exception e) {
            throw new DBException(e);
        }

        return bean;
    }

    public T findById(K id) throws DBException {
        T bean = null;

        try {
            bean = em.find(getEntityTypeClass(), id);
        } catch (NoResultException nre) {
            return null;
        } catch (Exception e) {
            throw new DBException(e);
        }

        return bean;
    }

    @SuppressWarnings("unchecked")
    public Class<T> getEntityTypeClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<T>) paramType.getActualTypeArguments()[0];
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<T> listarTodos(Class objectClass) throws DBException {
        try {
            return em.createNamedQuery(objectClass.getSimpleName().concat(SELECT_ALL)).getResultList();
            // return em.createQuery(" SELECT o FROM " + objectClass.getSimpleName() + " AS o ",
            // objectClass).getResultList();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public void detach(T entity) {
        em.detach(entity);
    }

    public void refresh(T entity) {
        em.refresh(entity);
    }

    public void clear() {
        em.clear();
    }

    public Class<?> getDAOInterfaceClass() {
        return this.getClass().getInterfaces()[0];
    }
    
    /**
     * TODO PENDENTE
     * 
     * @param objectClass
     * @return
     * @throws DBException
     */
   /* public int countPorFiltro(Class objectClass) throws DBException {
        try {
            int count = (Integer) em.createNamedQuery(objectClass.getSimpleName().concat(COUNT)).getSingleResult();
            return count;
        } catch (Exception e) {
            throw new DBException(e);
        }
    }*/

    // public T salvar(T objeto) throws DBException {
    // if (objeto.getId() == null) {
    // return inserir(objeto);
    // }
    // return atualizar(objeto);
    // }

}
