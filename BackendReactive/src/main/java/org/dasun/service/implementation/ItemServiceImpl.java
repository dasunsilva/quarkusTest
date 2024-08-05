package org.dasun.service.implementation;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.ItemDTO;
import org.dasun.dto.mappers.ItemDTOMapper;
import org.dasun.repo.ItemRepo;
import org.dasun.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemServiceImpl implements ItemService {
    @Inject
    ItemRepo itemRepo;

    @Inject
    ItemDTOMapper itemDTOMapper;

    @Override
    @WithSession
    public Uni<List<ItemDTO>> getAllItems() {
        return itemRepo.listAll()
                .onItem().transform(items ->
                        items.stream().map(itemDTOMapper::mapItemDTO)
                                .collect(Collectors.toList()))
                .onFailure().transform(error ->
                        new Exception("Item fetch failed at database with message: " + error.getMessage()));
    }

    @Override
    @WithSession
    public Uni<ItemDTO> getItem(Long id) {
        return itemRepo.findById(id)
                .onItem().transform(itemDTOMapper::mapItemDTO)
                .onFailure().transform(error ->
                        new Exception("Item fetch failed at databse with message" + error.getMessage())
                );
    }
}
