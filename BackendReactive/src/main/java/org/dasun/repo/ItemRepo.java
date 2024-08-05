package org.dasun.repo;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.model.Item;

/**
 * Item repo make item related database transactions.
 */
@ApplicationScoped
public class ItemRepo implements PanacheRepository<Item> {
}
