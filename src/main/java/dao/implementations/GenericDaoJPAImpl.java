package dao.implementations;

import dao.interfaces.GenericDao;
import domain.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
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

    public void delete(long id) {
        T objectToDelete = findById(id);
        this.em.remove(this.em.merge(objectToDelete));
    }

    public T findById(long id) {
        return this.em.find(this.entityClass, id);
    }

    public T update(T t) {
        return this.em.merge(t);
    }

    public List<T> getAll() {
        Query query = em.createQuery("SELECT entity FROM " + this.entityClass.getSimpleName() + " entity");
        return new ArrayList<T>(query.getResultList());
    }
}
