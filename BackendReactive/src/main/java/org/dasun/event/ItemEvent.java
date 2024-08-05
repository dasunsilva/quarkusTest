package org.dasun.event;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.dasun.model.Item;
import org.hibernate.reactive.mutiny.Mutiny;

public class ItemEvent {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    @ConsumeEvent(value = "add-item")
    public Uni<String> addItem(Item item){
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.persist(item)
                                .onItem().transformToUni( res ->
                                        Uni.createFrom().item("Item added successfully!"))
                                .onFailure().transform(error ->
                                        new Exception("Item adding failed at database with message: " + error.getMessage()))
                )
        );
    }

    @ConsumeEvent(value = "update-item")
    public Uni<String> updateItem(Item item){
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.merge(item)
                                .onItem().transformToUni(res ->
                                        Uni.createFrom().item("Item is updated successfully!"))
                                .onFailure().transform(error ->
                                        new Exception("Item update failed at databse with message: " + error.getMessage()))
                )
        );
    }

    @ConsumeEvent(value = "delete-item")
    public Uni<String> deleteItem(Long id){
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.find(Item.class, id)
                                .onItem().ifNotNull()
                                .transformToUni(item ->
                                        session.remove(item)
                                                .onItem().transformToUni( res ->
                                                        Uni.createFrom().item("Item is removed successfully!")
                                                )
                                                .onFailure().transform(error ->
                                                        new Exception("User remove failed at database with message: " + error.getMessage()))
                                )
                                .onItem().ifNull().failWith(new Exception("Can't find item with id: " + id))
                )
        );
    }
}
