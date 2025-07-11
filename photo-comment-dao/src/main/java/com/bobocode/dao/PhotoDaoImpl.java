package com.bobocode.dao;

import com.bobocode.model.Photo;
import com.bobocode.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    @Override
    public Photo findById(long id) {
        throw new UnsupportedOperationException("Just do it!"); // todo
    }

    @Override
    public List<Photo> findAll() {
        throw new UnsupportedOperationException("Just do it!"); // todo
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
