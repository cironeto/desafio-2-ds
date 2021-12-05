package com.devsuperior.bds02.controller;

import com.devsuperior.bds02.dto.CityDto;
import com.devsuperior.bds02.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDto>> findAllSorted(){
        return ResponseEntity.ok(cityService.findAllSorted());
    }

    @PostMapping
    public ResponseEntity<CityDto> save(@RequestBody CityDto cityDto){
        cityDto = cityService.save(cityDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cityDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cityDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
