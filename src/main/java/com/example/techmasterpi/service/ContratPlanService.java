package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.ContractPlan;
import com.example.techmasterpi.repos.ContractPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratPlanService implements  IContratPlan {
    @Autowired
    private ContractPlanRepository contractPlanRepository;
    public List<ContractPlan> findAll(){
       return contractPlanRepository.findAll();

    }
    public ContractPlan get(int id){
        return contractPlanRepository.findById(id).get();
    }
    public void create(ContractPlan c){
         contractPlanRepository.save(c);
    }
    public void  update (int id , ContractPlan c){
        if (contractPlanRepository.findById(c.getContractId()).isPresent())
            contractPlanRepository.save(c);
        else
            System.out.println("doesnt exist");
    }

    public void  delete (int id ){
        contractPlanRepository.deleteById(id);
    }
}
