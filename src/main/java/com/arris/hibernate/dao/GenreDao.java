package com.arris.hibernate.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.arris.hibernate.entity.Genre;

/**
 * Hibernate Dao of Genre.
 * @author Swaroop
 */
@Repository
@Transactional
public final class GenreDao implements HibernateDao<Genre> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Genre> getAll() {
        Query query = em.createQuery("from Genre");
        return query.getResultList();
    }

    @Override
    public Genre getById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public void update(Genre genre) {
        em.persist(genre);
    }

    @Override
    public void delete(Long id) {
        em.remove(em.find(Genre.class, id));
    }

}
