package com.example.networksiihw;

import com.example.Accounts;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class AccountsDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");

    public void addAccounts(Accounts accounts) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(accounts);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Accounts getAccountsById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Accounts accounts = entityManager.find(Accounts.class, id);
        entityManager.close();
        return accounts;
    }

    public List<Accounts> getAllAccounts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Accounts> accountsList = entityManager.createQuery("SELECT a FROM Accounts a", Accounts.class).getResultList();
        entityManager.close();
        return accountsList;
    }

    // Add additional methods for update and delete if needed
}