package martijn.kwetter.dao.implementations;

import martijn.kwetter.dao.interfaces.GenericDao;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public abstract class GenericDaoJPAImpl<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    /**
     * Constructor for the GenericDaoJPAImpl class
     */
    public GenericDaoJPAImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterPU");
        this.em = emf.createEntityManager();
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
        this.em.remove(objectToDelete);
    }

    public T findById(long id) {
        return this.em.find(type, id);
    }

    public T update(T t) {
        return this.em.merge(t);
    }
}
