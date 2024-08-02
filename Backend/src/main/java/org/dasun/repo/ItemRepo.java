package org.dasun.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.dasun.model.Item;

import java.util.List;

/**
 * Item repo make item related database transactions.
 */
@ApplicationScoped
public class ItemRepo implements PanacheRepository<Item> {
}
