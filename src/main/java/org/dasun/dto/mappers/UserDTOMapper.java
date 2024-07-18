package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.UserDTO;
import org.dasun.model.Bill;
import org.dasun.model.User;
import org.dasun.repo.BillRepo;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserDTOMapper {

    public UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhoneNumber());

        List<Bill> bills = new ArrayList<>();
        for (Bill bill : user.getBills()) {
            Bill tempBill = new Bill();
            tempBill.setId(bill.getId());
            tempBill.setDate(bill.getDate());
            tempBill.setAmount(bill.getAmount());
            bills.add(tempBill);
        }
        userDTO.setBills(bills);


        return userDTO;

    }

    public User mapDTOtoUser(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhone());
        user.setAccountNumber(userDTO.getAccountNumber());

        return user;

    }
}
