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

    @Override
    public Account findById(Long id) {
        throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public Account findByEmail(String email) {
        throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public List<Account> findAll() {
        throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public void update(Account account) {
        throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }

    @Override
    public void remove(Account account) {
        throw new UnsupportedOperationException("I don't wanna work without implementation!"); // todo
    }
}

