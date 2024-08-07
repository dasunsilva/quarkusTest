package org.dasun.command.implementation;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.command.BillCommand;
import org.dasun.dto.BillDTO;
import org.dasun.dto.mappers.BillDTOMapper;
import org.dasun.model.Bill;

@ApplicationScoped
public class BillCommandImpl implements BillCommand {

    @Inject
    EventBus bus;

    @Inject
    BillDTOMapper billDTOMapper;

    @Override
    public Uni<String> addBill(BillDTO billDTO) {
        return billDTOMapper.mapDTOBill(billDTO)
                .chain(bill ->
                        bus.<String>request("add-bill", bill)
                                .onItem().transform(Message::body));
    }

    @Override
    public String updateBill(BillDTO billDTO, Long id) {
        return null;
    }

    @Override
    public String deleteBill(Long id) {
        return null;
    }

}
