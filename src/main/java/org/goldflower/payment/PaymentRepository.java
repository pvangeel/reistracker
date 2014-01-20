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
    public Payment persist(Payment payment, String approvedBy) {
        if(payment.getId() == null) {
            entityManager.persist(payment);
            return payment;
        } else {
            Payment paymentEntity = entityManager.find(Payment.class, payment.getId());
            paymentEntity.setAmount(payment.getAmount());
            paymentEntity.setApproved(payment.isApproved());
            paymentEntity.setEmail(payment.getEmail());
            paymentEntity.setName(payment.getName());
            paymentEntity.setApprovedBy(approvedBy);
            return paymentEntity;
        }
    }

    public List<Payment> getAllPayments() {
        return entityManager.createQuery("select p from Payment p", Payment.class).getResultList();
    }
}
