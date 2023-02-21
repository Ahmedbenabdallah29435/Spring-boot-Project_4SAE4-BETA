package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.ContractPlan;
import com.example.techmasterpi.domain.Plan;
import com.example.techmasterpi.repos.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService  implements IPlan {
     @Autowired

     private PlanRepository planRepository;
    public List<Plan> findAll(){
        return planRepository.findAll();

    }
    public Plan get(int id){
        return planRepository.findById(id).get();
    }
    public int create(Plan c){
        return planRepository.save(c).getPlanid();
    }
    public void  update (int id , Plan c){
        if (planRepository.findById(c.getPlanid()).isPresent())
            planRepository.save(c);
        else
            System.out.println("doesnt exist");
    }

    public void  delete (int id ){
        planRepository.deleteById(id);
    }
}
