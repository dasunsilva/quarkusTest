package org.dasun.event;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.dasun.model.Bill;
import org.hibernate.reactive.mutiny.Mutiny;

public class BillEvent {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    @ConsumeEvent(value = "add-bill")
    public Uni<String> addBill(Bill bill) {
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.persist(bill)
                                .onItem().transformToUni(item ->
                                        Uni.createFrom().item("Bill is added successfully"))
                                .onFailure().transform( error ->
                                        new Exception("Bill adding failed at database with message: " + error.getMessage()))
                )
        );
    }

    @ConsumeEvent(value = "update-bill")
    public Uni<String> updateBill(Bill bill) {
        return null;
    }

    @ConsumeEvent(value = "delete-bill")
    public Uni<String> deleteBill(Long id) {
        return null;
    }

}
