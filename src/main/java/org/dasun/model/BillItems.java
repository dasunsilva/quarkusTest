package org.dasun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "bill_item")
public class BillItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private int priceAtPurchase;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bills;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item items;


}
