package com.example.caching.controler;

import com.example.caching.domain.Pet;
import com.example.caching.services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{name}")
    public Pet get(@PathVariable final String name){
        return petService.getByName(name);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createOrUpdate(@RequestBody final Pet pet){
        return petService.createOrUpdate(pet);
    }
}
