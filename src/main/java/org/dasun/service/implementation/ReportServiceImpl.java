package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.ItemDTO;
import org.dasun.dto.UserDTO;
import org.dasun.model.Bill;
import org.dasun.model.Item;
import org.dasun.model.User;
import org.dasun.service.GetStreamService;
import org.dasun.service.ReportService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReportServiceImpl implements ReportService {
    @Inject
    GetStreamService getStreamService;

    @Override
    public String getTotalSales() {
        Double totoalSales = getStreamService.getBillStream()
                .mapToDouble(Bill::getAmount)
                .reduce(0.0,Double::sum);

        return String.format("Total sales: Rs. %.2f", totoalSales);
    }

    @Override
    public List<ItemDTO> getMaxItem() {
        Comparator<ItemDTO> itemDTOComparator = new Comparator<ItemDTO>() {
            @Override
            public int compare(ItemDTO itemDTO1, ItemDTO itemDTO2) {
                return Integer.compare(itemDTO2.getBillIds().size(), itemDTO1.getBillIds().size());
            }
        };

        List<ItemDTO> sortedList = getStreamService.getItemStream()
                .sorted(itemDTOComparator)
                .collect(Collectors.toList());

        int maxItemCount = sortedList.get(0).getBillIds().size();

        List<ItemDTO> maxSellingItems = sortedList.stream()
                .filter(itemDTO -> itemDTO.getBillIds().size()==maxItemCount)
                .collect(Collectors.toList());

        return maxSellingItems;
    }

    @Override
    public List<ItemDTO> getMinItem() {
        Comparator<ItemDTO> itemDTOComparator = new Comparator<ItemDTO>() {
            @Override
            public int compare(ItemDTO itemDTO1, ItemDTO itemDTO2) {
                return Integer.compare(itemDTO1.getBillIds().size(), itemDTO2.getBillIds().size());
            }
        };

        List<ItemDTO> sortedList = getStreamService.getItemStream()
                .sorted(itemDTOComparator)
                .collect(Collectors.toList());

        int minItemCount = sortedList.get(0).getBillIds().size();

        List<ItemDTO> minSellingItems = sortedList.stream()
                .filter(itemDTO -> itemDTO.getBillIds().size()==minItemCount)
                .collect(Collectors.toList());

        return minSellingItems;
    }

    @Override
    public String getAverageSpending() {
        Double averageSales = getStreamService.getBillStream()
                .mapToDouble(Bill::getAmount)
                .average()
                .orElse(0.0);

        return String.format("The average sales is: Rs. %.2f", averageSales);
    }

    @Override
    public List<UserDTO> getActiveUsers() {
        List<UserDTO> userList = getStreamService.getUserStream()
                .filter(user -> !user.getBillIDs().isEmpty())
                .collect(Collectors.toList());

        return userList;
    }
}
