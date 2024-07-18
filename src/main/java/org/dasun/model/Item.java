package org.dasun.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "item_price",nullable = false)
    private int price;

    @Column(name = "item_stock", nullable = false)
    private int stock;

//    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Bill> bills;

    @OneToMany(mappedBy = "items")
    private List<BillItems> billItems;

}
