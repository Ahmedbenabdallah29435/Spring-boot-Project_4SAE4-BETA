package com.example.techmasterpi.domain;


import com.example.techmasterpi.model.TypeROffer;
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
public class RentalOffer {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer offreid;

    @Column
    private String title;

    @Column(name = "\"description\"")
    private String description;

    @Column
    private String adress;

    @Column
    private LocalDate offredate;

    @Column
    private String picture;

    @Column
    private Double monthlyrent;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeROffer typerentalloffer;

    @OneToMany(mappedBy = "rentalIOfferContractRental")
    private Set<RentalContract> rentalIOfferContractRentalRentalContracts;

    @OneToMany(mappedBy = "rentalofferRentalContract")
    private Set<RentalContract> rentalofferRentalContractRentalContracts;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
