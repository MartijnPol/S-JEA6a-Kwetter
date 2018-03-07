package dao.implementations;

import dao.interfaces.GenericDao;
import domain.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
    private Class<T> type;

    /**
     * Constructor for the GenericDaoJPAImpl class
     */
    public GenericDaoJPAImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
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
        return this.em.find(type, id);
    }

    public T update(T t) {
        return this.em.merge(t);
    }

    public List<T> getAll() {
        Query query = em.createQuery("SELECT a FROM " + type.getSimpleName() + " a");
        return new ArrayList<T>(query.getResultList());
    }
}
