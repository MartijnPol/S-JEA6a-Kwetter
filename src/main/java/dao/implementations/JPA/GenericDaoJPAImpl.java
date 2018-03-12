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
    protected EntityManager entityManager;
    private Class<T> entityClass;

    /**
     * Constructor for the GenericDaoJPAImpl class
     */
    public GenericDaoJPAImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public T save(T t) {
        this.entityManager.persist(t);
        return t;
    }

    public List<T> getAll() {
        return entityManager.createQuery("SELECT entity FROM " + this.entityClass.getSimpleName() + " entity").getResultList();
    }

    public Long countAll() {
        return (Long) entityManager.createQuery("SELECT COUNT(entity) FROM " + this.entityClass.getSimpleName() + " entity").getSingleResult();
    }

    public T findById(Long id) {
        return this.entityManager.find(this.entityClass, id);
    }

    public T update(T t) {
        return this.entityManager.merge(t);
    }

    public void deleteById(Long id) {
        this.entityManager.remove(this.entityManager.merge(findById(id)));
    }
}
