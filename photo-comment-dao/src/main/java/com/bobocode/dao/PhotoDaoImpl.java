package com.bobocode.dao;

import com.bobocode.model.Photo;
import com.bobocode.util.EntityManagerUtil;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.QueryHint;
import java.util.List;

/**
 * Please note that you should not use auto-commit mode for your implementation.
 */
public class PhotoDaoImpl implements PhotoDao {

    private EntityManagerUtil entityManagerUtil;

    public PhotoDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerUtil = new EntityManagerUtil(entityManagerFactory);
    }

    /**
     * Saves photo into db and sets an id
     *
     * @param photo new photo
     */
    @Override
    public void save(Photo photo) {
        entityManagerUtil.performWithinTx(em -> em.persist(photo));
    }

    /**
     * Retrieves a photo from the database by its id
     *
     * @param id photo id
     * @return photo instance
     */
    @Override
    public Photo findById(long id) {
        return entityManagerUtil.performReturningWithinTx(em -> em.find(Photo.class, id));
    }

    public static final String FIND_ALL_PHOTOS_JPQL_QUERY = "SELECT p FROM Photo p";
    /**
     * Returns a list of all stored photos
     *
     * @return list of stored photos
     */
    @Override
    public List<Photo> findAll() {
        return entityManagerUtil.performReturningWithinTx(em -> em.createQuery(FIND_ALL_PHOTOS_JPQL_QUERY, Photo.class)
                                                                  .setHint(QueryHints.HINT_READONLY, true)
                                                                  .getResultList());
    }

    @Override
    public void remove(Photo photo) {
        throw new UnsupportedOperationException("Just do it!"); // todo
    }

    @Override
    public void addComment(long photoId, String comment) {
        throw new UnsupportedOperationException("Just do it!"); // todo
    }
}
