package org.dasun.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.model.Bill;
import org.dasun.model.BillItems;

import java.util.List;

/**
 * BillItem repo make bill item related database transactions.
 */
@ApplicationScoped
public class BillItemRepo implements PanacheRepository<BillItems> {
}
