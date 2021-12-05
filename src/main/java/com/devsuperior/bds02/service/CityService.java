package com.devsuperior.bds02.service;

import com.devsuperior.bds02.dto.CityDto;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repository.CityRepository;
import com.devsuperior.bds02.service.exception.DatabaseException;
import com.devsuperior.bds02.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public List<CityDto> findAllSorted(){
        List<City> list = cityRepository.findAll(Sort.by("name"));
        return list.stream().map(CityDto::new).collect(Collectors.toList());
    }

    @Transactional
    public CityDto save(CityDto cityDto){
        City entity = new City();
        entity.setName(cityDto.getName());
        entity = cityRepository.save(entity);
        return new CityDto(entity);
    }
    
    @Transactional
    public void delete(Long id){
        try {
            cityRepository.deleteById(id);
            cityRepository.flush();
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("ID " + id + " not found");
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation. Cannot delete this category");
        }

    }
}
