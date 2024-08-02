package org.dasun.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.dasun.model.Bill;

import java.util.List;

/**
 * Bill repo make bill related database transactions.
 */
@ApplicationScoped
public class BillRepo implements PanacheRepository<Bill> {}
