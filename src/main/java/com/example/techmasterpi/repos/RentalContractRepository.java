package com.example.techmasterpi.repos;


import com.example.techmasterpi.domain.RentalContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalContractRepository extends JpaRepository<RentalContract, Integer> {
}
