package com.example.techmasterpi.repos;


import com.example.techmasterpi.domain.RentalOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOfferRepository extends JpaRepository<RentalOffer, Integer> {
}
