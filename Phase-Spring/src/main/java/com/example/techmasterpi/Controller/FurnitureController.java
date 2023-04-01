package com.example.techmasterpi.Controller;

import com.example.techmasterpi.model.FurnitureDTO;
import com.example.techmasterpi.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/furnitures", produces = MediaType.APPLICATION_JSON_VALUE)
public class FurnitureController {
    @Autowired

    private  FurnitureService furnitureService;


    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public ResponseEntity<List<FurnitureDTO>> getAllFurnitures() {
        return ResponseEntity.ok(furnitureService.findAll());
    }

    @PostMapping("/{relocationid}")
    @ResponseBody
    public int createFurniture(@RequestBody @Valid  FurnitureDTO furnitureDTO,
                               @PathVariable(name ="relocationid")  Integer relocationid) {
        return furnitureService.create(furnitureDTO,relocationid);
    }
    @GetMapping("/{furnitureid}")
    public ResponseEntity<FurnitureDTO> getFurniture(
            @PathVariable(name = "furnitureid")  Integer furnitureid) {
        return ResponseEntity.ok(furnitureService.get(furnitureid));
    }
    @PutMapping("/{furnitureid}")
    public ResponseEntity<Void> updateFurniture(
            @PathVariable(name = "furnitureid")  Integer furnitureid,
            @RequestBody @Valid  FurnitureDTO furnitureDTO) {
        furnitureService.update(furnitureid, furnitureDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{furnitureid}")
    public ResponseEntity<Void> deleteFurniture(
            @PathVariable(name = "furnitureid")  Integer furnitureid) {
        furnitureService.delete(furnitureid);
        return ResponseEntity.noContent().build();
    }
}



