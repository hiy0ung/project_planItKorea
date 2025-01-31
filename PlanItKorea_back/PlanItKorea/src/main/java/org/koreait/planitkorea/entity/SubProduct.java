package org.koreait.planitkorea.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Sub_Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "main_product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String subName;

    @Column(nullable = false)
    private String subDescription;

    @Column(nullable = false)
    private String subPrice;

    @Column(nullable = false)
    private int subPerson;

    @OneToMany(mappedBy = "subProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubProductImage> subProductImages;
}
