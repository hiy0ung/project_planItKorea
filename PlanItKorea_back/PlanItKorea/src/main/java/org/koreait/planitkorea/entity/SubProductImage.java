package org.koreait.planitkorea.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Sub_Product_Images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sub_product_id", nullable = false)
    private SubProduct subProduct;

    @Column
    private String subProductImage;
}
