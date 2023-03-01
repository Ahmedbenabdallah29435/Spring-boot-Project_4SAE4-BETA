package com.example.techmasterpi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class SellContract {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractsellid;

    @Column
    private Double price;

    @Column
    private LocalDate selldate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_sell_id")
    private SellerOffer contractSell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_contractsale_id")
    private User userContractsale;



}
