package org.dasun.service;

import org.dasun.dto.ItemDTO;
import org.dasun.dto.UserDTO;

import java.util.List;
import java.util.Map;
/**
 * This interface defines the report generation related services
 */
public interface ReportService {

    /**
     * This is used to get the total sales of the store
     * @return return a total sale of the store as a string
     */
    public String getTotalSales();

    /**
     * This method is used to get the information about the maximum selling item in the store
     * @return a list of ItemDTO that has the information about the max selling items
     */
    public List<ItemDTO> getMaxItem();

    /**
     * This method is used to get the information about the minimum selling item in the store
     * @return a list of ItemDTO that has the information about the minimum selling items
     */
    public List<ItemDTO> getMinItem();

    /**
     * This method is used to get the average spending of a user in the store
     * @return a string that have information about the average spending of a user in the store
     */
    public String getAverageSpending();

    /**
     * This method is used to get the information about the active users.
     * An active user means a user that has at least one record of purchase
     * @return a list of UserDTO indicating information about active users
     */
    public List<UserDTO> getActiveUsers();
}
