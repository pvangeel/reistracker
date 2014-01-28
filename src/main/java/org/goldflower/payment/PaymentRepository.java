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
        TypedQuery<Double> query = entityManager.createQuery("select coalesce(SUM(p.amount), 0) from Payment p where p.approved = true", Double.class);
        return query.getSingleResult();
    }

    @Transactional
    public Payment persist(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

    public List<Payment> getAllPayments() {
        return entityManager.createQuery("select p from Payment p", Payment.class).getResultList();
    }

    @Transactional
    public Payment update(Long id, Payment payment, String approvedBy) {
        Payment paymentEntity = entityManager.find(Payment.class, id);
        paymentEntity.setApprovedBy(approvedBy);
        paymentEntity.setApproved(payment.isApproved());
        paymentEntity.setAmount(payment.getAmount());
        paymentEntity.setName(payment.getName());
        payment.setEmail(payment.getEmail());
        return paymentEntity;
    }
}
