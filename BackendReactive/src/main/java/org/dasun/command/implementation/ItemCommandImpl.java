package org.dasun.command.implementation;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.command.ItemCommand;
import org.dasun.dto.ItemDTO;
import org.dasun.dto.mappers.ItemDTOMapper;
import org.dasun.repo.ItemRepo;

@ApplicationScoped
public class ItemCommandImpl implements ItemCommand {

    @Inject
    ItemDTOMapper itemDTOMapper;

    @Inject
    EventBus bus;

    @Inject
    ItemRepo itemRepo;

    @Override
    public Uni<String> addItem(ItemDTO itemDTO) {
        return bus.<String>request("add-item", itemDTOMapper.mapDTOItem(itemDTO))
                .onItem().transformToUni(res ->
                        Uni.createFrom().item(res.body()));
    }

    @Override
    @WithSession
    public Uni<String> updateItem(ItemDTO itemDTO, Long id) {
        return itemRepo.findById(id)
                .onItem().transformToUni(item -> {
                    item.setName(itemDTO.getName());
                    item.setStock(itemDTO.getStock());
                    item.setPrice(itemDTO.getPrice());
                    // TODO: Add bill items

                    return bus.<String>request("update-item", item)
                            .onItem().transformToUni(res ->
                                    Uni.createFrom().item(res.body()));
                });
    }

    @Override
    public Uni<String> deleteItem(Long id) {
        return bus.<String>request("delete-item", id)
                .onItem().transform(Message::body);
    }
}
