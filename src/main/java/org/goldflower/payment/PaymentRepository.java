package org.goldflower.payment;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PaymentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public double getTotalAmountPayed() {
        TypedQuery<Double> query = entityManager.createQuery("select SUM(p.amount) from Payment p where p.approved = true", Double.class);
        return query.getSingleResult();
    }

    @Transactional
    public void persist(Payment payment) {
        entityManager.persist(payment);
    }

    public List<Payment> getAllPayments() {
        return entityManager.createQuery("select p from Payment p", Payment.class).getResultList();
    }
}
