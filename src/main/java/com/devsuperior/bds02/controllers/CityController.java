package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import com.devsuperior.bds02.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value= "/cities")
public class CityController {
    @Autowired
    private CityService service;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll() {
        List<CityDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CityDTO> add (@RequestBody CityDTO cityDTO){
        cityDTO =  service.add(cityDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(cityDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(cityDTO);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
