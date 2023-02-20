package com.example.techmasterpi.domain;


import com.example.techmasterpi.model.TypeOffer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class SellerOffer {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellid;

    @Column
    private String descrption;

    @Column
    private String title;

    @Column
    private Double price;

    @Column
    private String adress;

    @Column
    private LocalDate datesell;

    @Column
    private String picture;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOffer typeoffer;



    @OneToMany(mappedBy = "contractSell")
    private Set<SellContract> contractSellSellContracts;



}
