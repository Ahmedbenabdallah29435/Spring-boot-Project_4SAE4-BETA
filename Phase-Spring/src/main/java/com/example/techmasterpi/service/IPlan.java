package com.example.techmasterpi.service;


import com.example.techmasterpi.domain.Plan;

import java.util.List;

public interface IPlan  {
    public List<Plan> findAll();
    public Plan get(int id);
    public int create(Plan c);
    public void  update (int id , Plan c);
    public void  delete (int id );
}
