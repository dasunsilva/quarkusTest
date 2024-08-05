package org.dasun.repo;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.model.Bill;

/**
 * Bill repo make bill related database transactions.
 */
@ApplicationScoped
public class BillRepo implements PanacheRepository<Bill> {}
