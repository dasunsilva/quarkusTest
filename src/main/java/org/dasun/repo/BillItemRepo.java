package org.dasun.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.model.Bill;
import org.dasun.model.BillItems;

@ApplicationScoped
public class BillItemRepo implements PanacheRepository<BillItems> {}