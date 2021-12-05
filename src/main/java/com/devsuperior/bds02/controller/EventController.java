package com.devsuperior.bds02.controller;

import com.devsuperior.bds02.dto.EventDto;
import com.devsuperior.bds02.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDto> replace(@PathVariable Long id, @RequestBody EventDto eventDto){
        eventDto = eventService.replace(id, eventDto);
        return ResponseEntity.ok(eventDto);
    }


}
