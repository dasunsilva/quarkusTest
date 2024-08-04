package org.dasun.repo;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.model.User;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;

/**
 * User repo make user related database transactions.
 */
@ApplicationScoped
public class UserRepo{

    @Inject
    Mutiny.SessionFactory sessionFactory;

    public Uni<List<User>> getAllUsers(){
        return sessionFactory.withSession(session ->
                session.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.bills", User.class)
                        .getResultList()
                        .onFailure().transform(error ->
                                new Exception("Error when  fetching data from database " + error.getMessage())
                        )
        );
    }

    public Uni<User> getUserById(Long id){
        return sessionFactory.withSession(session ->
                session.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.bills WHERE u.id = :userID", User.class)
                        .setParameter("userID", id)
                        .getSingleResult()
                        .onFailure().transform(error -> new Exception("Error when  fetching data from database " + error.getMessage()))
        );
    }


    public Uni<String> addUser(User user){
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.persist(user)
                                .onItem().transformToUni(item ->
                                        Uni.createFrom().item("User is added successfully"))
                                .onFailure().transform(error ->
                                        new Exception("User added failed at database " + error.getMessage()))
                )
        );
    }

    public Uni<String> updateUser(User user){
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.merge(user)
                                .chain(session::flush)
                                .onItem().transformToUni(item ->
                                        Uni.createFrom().item("User is updated successfully!"))
                                .onFailure().transform(error ->
                                        new Exception("User update failed at the database"))
                )
        );
    }

    public Uni<String> removeUser(User user){
        return sessionFactory.withSession(session ->
                session.withTransaction(transaction ->
                        session.merge(user).onItem()
                                .transformToUni(session.remove())
                        session.remove(user)
                                .onItem().transformToUni(item ->
                                        Uni.createFrom().item("User is removed successfully!"))
                                .onFailure().transform(error ->
                                        new Exception("User remove failed at databse" + error.getMessage()))
                )
        );
    }

}
