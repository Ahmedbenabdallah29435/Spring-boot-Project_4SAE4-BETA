package com.example.techmasterpi.service;


import com.example.techmasterpi.domain.ContractPlan;

import java.util.List;

public interface IContratPlan {
    public List<ContractPlan> findAll();
    public ContractPlan get(int id);
    public void create(ContractPlan c);
    public void  update (int id , ContractPlan c);
    public void  delete (int id );
}
