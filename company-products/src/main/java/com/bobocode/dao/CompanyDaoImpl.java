package com.bobocode.dao;

import com.bobocode.exception.CompanyDaoException;
import com.bobocode.model.Company;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Function;

public class CompanyDaoImpl implements CompanyDao {

    private EntityManagerFactory entityManagerFactory;

    public CompanyDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public static final String FIND_COMPANY_WITH_PRODUCTS_JPQL_QUERY =
            "SELECT c FROM Company c JOIN FETCH c.products WHERE c.id = :id";
    /**
     * Retrieves a {@link Company} with all its products by company id
     *
     * @param id company id
     * @return company with all its products
     */
    @Override
    public Company findByIdFetchProducts(Long id) {
        return performWithinTransaction(em ->
                    em.createQuery(FIND_COMPANY_WITH_PRODUCTS_JPQL_QUERY, Company.class)
                            .setParameter("id", id).getSingleResult());
    }

    private <T> T performWithinTransaction(Function<EntityManager, T> performer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            T result = performer.apply(em);
            Session s = em.unwrap(Session.class);
            s.setReadOnly(result, true);
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new CompanyDaoException("Error performing transaction", e);
        } finally {
            em.close();
        }
    }

}