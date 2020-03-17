package com.example.caching.services;

import com.example.caching.domain.Pet;
import com.example.caching.repositories.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@CacheConfig(cacheNames = "pets")
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @CachePut(key = "#pet.name")
    public Pet createOrUpdate(final Pet pet){
        log.info("createOrUpdate");
        return  petRepository.save(pet);
    }

    @Cacheable
    public Pet getByName(final String name){
        log.info("getByName");
        return petRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Pet with name does not exist " + name));
    }

}
