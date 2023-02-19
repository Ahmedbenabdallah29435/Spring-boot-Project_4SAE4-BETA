package com.example.techmasterpi.Controller;


import com.example.techmasterpi.model.DeliveryDTO;
import com.example.techmasterpi.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping(value = "/api/deliverys", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    public DeliveryController( DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDTO>> getAllDeliverys() {
        return ResponseEntity.ok(deliveryService.findAll());
    }

    @GetMapping("/{deliveryid}")
    public ResponseEntity<DeliveryDTO> getDelivery(
            @PathVariable(name = "deliveryid")  Integer deliveryid) {
        return ResponseEntity.ok(deliveryService.get(deliveryid));
    }

    @PostMapping
    public ResponseEntity<Integer> createDelivery(
            @RequestBody @Valid  DeliveryDTO deliveryDTO,
            @PathParam("iduser")Integer iduser ,
            @PathParam("idrelocation")Integer idrelocation) {
        return new ResponseEntity<>(deliveryService.create(deliveryDTO,iduser,idrelocation), HttpStatus.CREATED);
    }

    @PutMapping("/{deliveryid}")
    public ResponseEntity<Void> updateDelivery(
            @PathVariable(name = "deliveryid")  Integer deliveryid,
            @RequestBody @Valid  DeliveryDTO deliveryDTO) {
        deliveryService.update(deliveryid, deliveryDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{deliveryid}")
    public ResponseEntity<Void> deleteDelivery(
            @PathVariable(name = "deliveryid") Integer deliveryid) {
        deliveryService.delete(deliveryid);
        return ResponseEntity.noContent().build();
    }

}
