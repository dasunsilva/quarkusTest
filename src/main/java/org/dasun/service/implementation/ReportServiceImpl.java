package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.ItemDTO;
import org.dasun.dto.UserDTO;
import org.dasun.model.Bill;
import org.dasun.repo.BillRepo;
import org.dasun.service.ItemService;
import org.dasun.service.ReportService;
import org.dasun.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReportServiceImpl implements ReportService {
    @Inject
    BillRepo billRepo;

    @Inject
    ItemService itemService;

    @Inject
    UserService userService;

    @Override
    public String getTotalSales() {
        Double totoalSales = billRepo.findAll().stream()
                .mapToDouble(Bill::getAmount)
                .reduce(0.0,Double::sum);

        return String.format("Total sales: Rs. %.2f", totoalSales);
    }

    @Override
    public List<ItemDTO> getMaxItem() {

        Map<Integer, List<ItemDTO>> maxSellingItemsMap = itemService.getAllItems().stream()
                .collect(Collectors.groupingBy(itemDTO -> itemDTO.getBillIds().size()));

        Optional<Integer> maxKey = maxSellingItemsMap.keySet().stream()
                .max(Integer::compareTo);

        if(maxKey.isPresent()){
            int maxItems = maxKey.get();
            return maxSellingItemsMap.get(maxItems);
        }else{
            return null;
        }
    }

    @Override
    public List<ItemDTO> getMinItem() {

        Map<Integer, List<ItemDTO>> minSellingItemsMap = itemService.getAllItems().stream()
                .collect(Collectors.groupingBy(itemDTO -> itemDTO.getBillIds().size()));

        Optional<Integer> maxKey = minSellingItemsMap.keySet().stream()
                .min(Integer::compareTo);

        if(maxKey.isPresent()){
            int maxItems = maxKey.get();
            return minSellingItemsMap.get(maxItems);
        }else{
            return null;
        }
    }

    @Override
    public String getAverageSpending() {
        Double averageSales = billRepo.findAll().stream()
                .mapToDouble(Bill::getAmount)
                .average()
                .orElse(0.0);

        return String.format("The average sales is: Rs. %.2f", averageSales);
    }

    @Override
    public List<UserDTO> getActiveUsers() {
        List<UserDTO> userList = userService.getAllUsers().stream()
                .filter(user -> !user.getBillIDs().isEmpty())
                .collect(Collectors.toList());

        return userList;
    }
}
