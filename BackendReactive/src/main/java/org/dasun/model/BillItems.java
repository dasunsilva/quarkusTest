package org.dasun.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the billItem class
 * This defines the fields that are in a billItem object
 */
@Getter
@Setter
@Entity
@Table(name = "bill_item")
public class BillItems {

    @Id
    @SequenceGenerator(name = "bill_items_seq", sequenceName = "bill_items_sequence", allocationSize = 50 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_items_seq")
    private Long id;
    private int quantity;
    private int priceAtPurchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private Bill bills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item items;


}
