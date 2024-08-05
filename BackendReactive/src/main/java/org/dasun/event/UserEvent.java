package org.dasun.event;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.model.User;
import org.hibernate.reactive.mutiny.Mutiny;

@ApplicationScoped
public class UserEvent {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    @ConsumeEvent(value = "add-user")
    public Uni<String> addUser(User user){
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.persist(user)
                                .onItem().transformToUni(u ->
                                        Uni.createFrom().item("User is added successflly!"))
                                .onFailure().transform(error ->
                                        new Exception("User add fails at the databse with message: " + error.getMessage())
                                )
                )
        );
    }

    @ConsumeEvent(value = "edit-user")
    public Uni<String> editUser(User user){
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.merge(user)
                                .onItem().transformToUni(item ->
                                        Uni.createFrom().item("User is updated successflly!"))
                                .onFailure().transform(error ->
                                        new Exception("User update fails at the databse with message: " + error.getMessage())
                                )
                )
        );
    }

    @ConsumeEvent(value = "remove-user")
    public Uni<String> removeUser(Long userId){
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.find(User.class, userId)
                                .onItem().ifNotNull().transformToUni(user ->
                                        session.remove(user)
                                                .onItem().transformToUni(item ->
                                                        Uni.createFrom().item("User is removed successflly!"))
                                                .onFailure().transform(error ->
                                                        new Exception("User delete fails at the databse with message: " + error.getMessage()))
                                        )
                                .onItem().ifNull().failWith(new Exception("User with id: " + userId + " does not exist!"))
                )
        );
    }
}
