package org.dasun.service;

import org.dasun.dto.ItemDTO;
import org.dasun.dto.UserDTO;
import org.dasun.model.Bill;
import org.dasun.model.Item;
import org.dasun.model.User;

import java.util.stream.Stream;

public interface GetStreamService {

    public Stream<Bill> getBillStream();
    public Stream<ItemDTO> getItemStream();
    public Stream<UserDTO> getUserStream();
}
