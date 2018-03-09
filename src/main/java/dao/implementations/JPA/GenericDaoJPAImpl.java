package dao.implementations.JPA;

import dao.interfaces.GenericDao;
import domain.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
@Stateless
@JPA
public class GenericDaoJPAImpl<T> implements GenericDao<T> {

    @PersistenceContext(unitName = "KwetterPU")
    protected EntityManager em;
    private Class<T> entityClass;

    /**
     * Constructor for the GenericDaoJPAImpl class
     */
    public GenericDaoJPAImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public T create(T t) {
        this.em.persist(t);
        return t;
    }

    public List<T> getAll() {
        return em.createQuery("SELECT entity FROM " + this.entityClass.getSimpleName() + " entity").getResultList();
    }

    public Long countAll() {
        return (Long) em.createQuery("SELECT COUNT(*) FROM " + this.entityClass.getSimpleName()).getSingleResult();
    }

    public T findById(Long id) {
        return this.em.find(this.entityClass, id);
    }

    public T update(T t) {
        return this.em.merge(t);
    }

    public void deleteById(Long id) {
        this.em.remove(this.em.merge(findById(id)));
    }
}
