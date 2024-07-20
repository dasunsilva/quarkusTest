package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.ItemDTO;
import org.dasun.dto.UserDTO;
import org.dasun.model.Bill;
import org.dasun.model.Item;
import org.dasun.model.User;
import org.dasun.repo.BillRepo;
import org.dasun.repo.ItemRepo;
import org.dasun.repo.UserRepo;
import org.dasun.service.GetStreamService;
import org.dasun.service.ItemService;
import org.dasun.service.UserService;

import java.util.stream.Stream;

@ApplicationScoped
public class GetStreamServiceImpl implements GetStreamService {
    @Inject
    BillRepo billRepo;

    @Inject
    ItemService itemService;

    @Inject
    UserService userService;

    @Override
    public Stream<Bill> getBillStream() {
        return billRepo.streamAll();
    }

    @Override
    public Stream<ItemDTO> getItemStream() {
        return itemService.getAllItems().stream();
    }

    @Override
    public Stream<UserDTO> getUserStream() {
        return userService.getAllUsers().stream();
    }
}
