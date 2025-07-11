package com.bobocode.dao;

import com.bobocode.exception.AccountDaoException;
import com.bobocode.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private EntityManagerFactory emf;

    public AccountDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Receives a new instance of {@link Account} and stores it into database. Sets a generated id to account.
     *
     * @param account new instance of account
     */
    @Override
    public void save(Account account) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(account);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AccountDaoException("Error save account: " + account, e);
        } finally {
            em.close();
        }
    }

    /**
     * Returns an {@link Account} instance by its id
     *
     * @param id account id in the database
     * @return account instance
     */
    @Override
    public Account findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Account.class, id);
        } catch (Exception e) {
            throw new AccountDaoException("Error find by id: " + id, e);
        } finally {
            em.close();
        }
    }

    public static final String FIND_BY_EMAIL_JPQL_QUERY = "SELECT a FROM Account a WHERE a.email = :email";
    /**
     * Returns {@link Account} instance by its email
     *
     * @param email account emails
     * @return account instance
     */
    @Override
    public Account findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(FIND_BY_EMAIL_JPQL_QUERY, Account.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            throw new AccountDaoException("Error find by email: " + email, e);
        } finally {
            em.close();
        }
    }

    public static final String FIND_ALL_JPQL_QUERY = "SELECT a FROM Account a";
    /**
     * Returns all accounts stored in the database.
     *
     * @return account list
     */
    @Override
    public List<Account> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(FIND_ALL_JPQL_QUERY, Account.class)
                    .getResultList();
        } catch (Exception e) {
            throw new AccountDaoException("Error find all", e);
        } finally {
            em.close();
        }
    }

    /**
     * Receives stored {@link Account} instance and updates it in the database
     *
     * @param account stored account with updated fields
     */
    @Override
    public void update(Account account) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.merge(account);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AccountDaoException("Error update account: " + account, e);
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(Account account) {
        throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }
}

