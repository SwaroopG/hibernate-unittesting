package com.arris.hibernate.dao;

import java.util.Collection;

/**
 * Base hibernate interface.
 * @author Swaroop
 * @param <T>
 */
interface HibernateDao<T> {

    /**
     * @return All the entities.
     */
    public Collection<T> getAll();

    /**
     * @param id Primary key
     * @return The entity with the given id.
     */
    public T getById(Long id);

    /**
     * Updates the entity.
     * @param t Entity. Cannot be null.
     */
    public void update(T t);

    /**
     * Deletes the entity with the given id.
     * @param id Cannot be null.
     */
    public void delete(Long id);
}
