package com.example.techmasterpi.Controller;

import com.example.techmasterpi.domain.Plan;
import com.example.techmasterpi.service.IContratPlan;
import com.example.techmasterpi.service.IPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/plans")
public class PlanController {
    @Autowired

    private IPlan plan;
    @PostMapping("/addPlan")
    @ResponseBody
    public void addPlan(@RequestBody Plan p){
         plan.create(p);
    }

}
