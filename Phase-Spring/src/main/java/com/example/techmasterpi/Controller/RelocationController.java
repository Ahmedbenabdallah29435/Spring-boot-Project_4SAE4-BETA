package com.example.techmasterpi.Controller;



import com.example.techmasterpi.model.RelocationDTO;
import com.example.techmasterpi.service.RelocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/relocations", produces = MediaType.APPLICATION_JSON_VALUE)
public class RelocationController {

    @Autowired
    private RelocationService relocationService;

    public RelocationController(RelocationService relocationService) {
        this.relocationService = relocationService;
    }

    @GetMapping
    public ResponseEntity<List<RelocationDTO>> getAllRelocations() {
        return ResponseEntity.ok(relocationService.findAll());
    }

    @GetMapping("/{relocationid}")
    public ResponseEntity<RelocationDTO> getRelocation(
            @PathVariable(name = "relocationid")  Integer relocationid) {
        return ResponseEntity.ok(relocationService.get(relocationid));
    }

    @PostMapping("/{userid}")
    @ResponseBody
    public int createRelocation(
            @RequestBody @Valid  RelocationDTO relocationDTO,
            @PathVariable(name = "userid")  Integer userid) {
        return relocationService.create(relocationDTO,userid);
    }

    @PutMapping("/{relocationid}")
    public ResponseEntity<Void> updateRelocation(
            @PathVariable(name = "relocationid")  Integer relocationid,
            @RequestBody @Valid final RelocationDTO relocationDTO) {
        relocationService.update(relocationid, relocationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{relocationid}")
    public ResponseEntity<Void> deleteRelocation(
            @PathVariable(name = "relocationid")  Integer relocationid) {
        relocationService.delete(relocationid);
        return ResponseEntity.noContent().build();
    }

}
