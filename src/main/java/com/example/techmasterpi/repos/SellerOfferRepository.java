package com.example.techmasterpi.repos;


import com.example.techmasterpi.domain.SellerOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerOfferRepository extends JpaRepository<SellerOffer, Integer> {
}
