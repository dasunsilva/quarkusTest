package org.dasun.repo;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
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
public class UserRepo implements PanacheRepository<User> {
}
