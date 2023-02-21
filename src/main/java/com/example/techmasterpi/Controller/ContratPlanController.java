package com.example.techmasterpi.Controller;

import com.example.techmasterpi.domain.ContractPlan;
import com.example.techmasterpi.service.IContratPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/contractPlans")
public class ContratPlanController {
     @Autowired
private IContratPlan contratPlan;

@PostMapping("/addContrat")
@ResponseBody
public void createContractPlan (@RequestBody ContractPlan c) {
     contratPlan.create(c);
}
}
