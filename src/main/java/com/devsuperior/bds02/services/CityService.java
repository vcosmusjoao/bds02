package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.excepetion.DatabaseException;
import com.devsuperior.bds02.services.excepetion.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;

    //LISTAR TODOS OS PRODUTOS
    @Transactional(readOnly = true)
    public List<CityDTO> findAll(){
        List<City> lista =  repository.findAll(Sort.by("name"));
        return  lista.stream().map(x-> new CityDTO(x)).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public CityDTO add(CityDTO cityDTO){
        City city = new City();
        city.setName(cityDTO.getName());
        city = repository.save(city);
        return new CityDTO(city);
    }

    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(("Id não encontrado ")+ id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Violação de Integridade");
        }
    }


}
