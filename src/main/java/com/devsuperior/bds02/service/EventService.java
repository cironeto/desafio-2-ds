package com.devsuperior.bds02.service;

import com.devsuperior.bds02.dto.EventDto;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repository.EventRepository;
import com.devsuperior.bds02.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public EventDto replace(Long id, EventDto eventDto){
        try {
            Event entity = eventRepository.getOne(id);
            entity.setName(eventDto.getName());
            entity.setDate(eventDto.getDate());
            entity.setUrl(eventDto.getUrl());
            entity.setCity(new City(eventDto.getCityId(),null));

            entity = eventRepository.save(entity);
            return new EventDto(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("ID " + id + " not found");
        }

    }
}
