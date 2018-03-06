package dao.interfaces;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public interface GenericDao<T> {

    /**
     * Method to create a new entity and persist it to the database
     *
     * @param t is the entity that needs to be stored in the database
     * @return
     */
    T create(T t);

    /**
     * Method to delete an entity
     *
     * @param id
     */
    void delete(long id);

    /**
     * Method to find an entity by it's id
     *
     * @param id
     * @return the found entity
     */
    T findById(long id);

    /**
     * Method to store an updated entity
     *
     * @param t the updated entity
     * @return the updated entity
     */
    T update(T t);

}
