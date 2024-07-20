package org.dasun.service;

import org.dasun.dto.ItemDTO;
import org.dasun.dto.UserDTO;

import java.util.List;

public interface ReportService {
    public String getTotalSales();
    public List<ItemDTO> getMaxItem();
    public List<ItemDTO> getMinItem();
    public String getAverageSpending();
    public List<UserDTO> getActiveUsers();
}
