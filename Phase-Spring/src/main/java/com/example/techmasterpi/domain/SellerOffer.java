package com.example.techmasterpi.domain;


import com.example.techmasterpi.model.TypeOffer;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor

public class SellerOffer {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellid;

    @Column
    private String description;

    @Column
    private String title;
    @NonNull
    @Column
    private Double price;

    @Column
    private String address;

    @Column
    private LocalDate datesell;

    @Column
    private String picture;
    @Column
    private Boolean sold=false;
    @Column
    private boolean favorite =false;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOffer typeoffer;



    @OneToMany(mappedBy = "contractSell")
    private Set<SellContract> SellContracts;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "sellerOffer")
    private List<OffreFavorit> offreFavorits;


    public SellerOffer(String description) {
        this.description=description;
    }

    public SellerOffer(String descriptipon, String title) {
        this.description=descriptipon;
        this.title=title;
    }
//    public boolean getFavorite(){
//        return favorite;
//    }


}
