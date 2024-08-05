package org.dasun.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * This is the bill class
 * This defines the fields that are in a bill object
 */
@Getter
@Setter
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @SequenceGenerator(name = "bill_id_seq", sequenceName = "bills_sequence", allocationSize = 50 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_id_seq")
    @Column(name = "bill_id")
    private Long id;

    @Column(name = "bill_date", nullable = false)
    private LocalDate date;

    @Column(name = "bill_amount",nullable = false)
    private int amount;

    // TODO: Add lazy loading here
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    /*
       This holds the information about the bill items.
       Bill item will have bill id, item id, quantitu, price at purchase
    */
    // TODO: Add lazy loading here
    @OneToMany(mappedBy = "bills", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<BillItems> billItems;
}
