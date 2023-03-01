package com.example.techmasterpi.repos;


import com.example.techmasterpi.domain.SellContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellContractRepository extends JpaRepository<SellContract, Integer> {
}
