package org.dasun.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * This is the Item class
 * This defines the fields that are in a Item object
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "items")
public class Item {

    @Id
    @SequenceGenerator(name = "item_seq", sequenceName = "item_sequence", allocationSize = 50 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "item_price",nullable = false)
    private int price;

    @Column(name = "item_stock", nullable = false)
    private int stock;

    /*
    This holds the information about the bill items.
    Bill item will have bill id, item id, quantitu, price at purchase
     */
    // TODO: Add lazy loading here
    @OneToMany(mappedBy = "items", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BillItems> billItems;

}
