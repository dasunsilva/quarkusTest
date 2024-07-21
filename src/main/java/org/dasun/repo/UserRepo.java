package org.dasun.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.dasun.model.User;

import java.util.List;

/**
 * User repo make user related database transactions.
 */
@ApplicationScoped
public class UserRepo implements PanacheRepository<User> {
}
