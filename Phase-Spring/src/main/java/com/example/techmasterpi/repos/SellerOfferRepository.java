package com.example.techmasterpi.repos;


import com.example.techmasterpi.domain.SellerOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerOfferRepository extends JpaRepository<SellerOffer, Integer> {
    List<SellerOffer> findByFavoriteTrue();
    @Query("Select dc From SellerOffer dc where dc.datesell<current_date ")
    List<SellerOffer> findByHistorique();
}
