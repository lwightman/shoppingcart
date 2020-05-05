package com.lisa.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    public CartItem(Item item, int qty) {
        this.item = item;
        this.quantity = qty;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "itemId", nullable = false, foreignKey = @ForeignKey(name = "fk_Cart_Item"))
    private Item item;

    @Column(nullable = false)
    private int quantity;
}
