package org.dasun.service.implementation;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.BillDTO;
import org.dasun.dto.mappers.BillDTOMapper;
import org.dasun.repo.BillRepo;
import org.dasun.repo.UserRepo;
import org.dasun.service.BillService;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BillServiceImpl implements BillService {

    @Inject
    UserRepo userRepo;

    @Inject
    BillRepo billRepo;

    @Inject
    BillDTOMapper billDTOMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @WithSession
    public Uni<List<BillDTO>> getAllBills() {
        // Get all bills as a list of DTOs;
        return billRepo.listAll()
                .map(bills -> bills
                                .stream()
                                .map(billDTOMapper::mapBillDTO)
                                .collect(Collectors.toList()))
                .onFailure()
                .transform(error ->
                        new Exception("Bill fetching failed at database with message: " + error.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @WithSession
    public Uni<BillDTO> getBill(Long id) {
        return billRepo.findById(id)
                .onItem().transform(billDTOMapper::mapBillDTO)
                .onFailure().transform(error ->
                        new Exception("Bill with id: "+ id + " fetch failed at database with message: " + error.getMessage()));
    }

}
