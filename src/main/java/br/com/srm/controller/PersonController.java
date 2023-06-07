package br.com.srm.controller;

import br.com.srm.business.PersonBusiness;
import br.com.srm.dto.PersonRequestDTO;
import br.com.srm.dto.PersonResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired @Lazy
    PersonBusiness business;

    @GetMapping("/{document}")
    private ResponseEntity<PersonResponseDTO> findByDocument(@PathVariable String document) {
        return ResponseEntity.ok(business.findByDocument(document));
    }

    @GetMapping("/all")
    private ResponseEntity<List<PersonResponseDTO>> findAll() {
        List<PersonResponseDTO> persons = business.findAll();
        if (persons.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(persons);
    }

    @PostMapping("/create")
    private ResponseEntity<PersonResponseDTO> createPerson(@Valid @RequestBody PersonRequestDTO requestDTO) {
        return ResponseEntity.ok(business.createPerson(requestDTO));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<PersonResponseDTO> updatePerson(@Valid @RequestBody PersonRequestDTO requestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(business.updatePerson(requestDTO, id));
    }

    @PostMapping("/delete/{id}")
    private ResponseEntity<String> deletePerson(@PathVariable Long id) {
        business.deletePerson(id);
        return ResponseEntity.ok("Registro Deletado");
    }

}
