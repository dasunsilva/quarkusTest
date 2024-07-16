package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.dto.BillDTO;
import org.dasun.model.Bill;

@ApplicationScoped
public class BillDTOMapper {

    public BillDTO mapBillDTO(Bill bill) {
        BillDTO billDTO = new BillDTO();

        billDTO.setId(bill.getId());
        billDTO.setDate(bill.getDate());
        billDTO.setAmount(bill.getAmount());

        return billDTO;

    }

    public Bill mapDTOBill(BillDTO billDTO) {
        Bill bill = new Bill();

        bill.setId(billDTO.getId());
        bill.setDate(billDTO.getDate());
        bill.setAmount(billDTO.getAmount());

        return bill;

    }
}
