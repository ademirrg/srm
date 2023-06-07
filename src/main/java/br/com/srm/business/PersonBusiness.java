package br.com.srm.business;

import br.com.srm.dto.PersonRequestDTO;
import br.com.srm.dto.PersonResponseDTO;
import br.com.srm.entity.Person;

import java.util.List;

public interface PersonBusiness {

    PersonResponseDTO findByDocument(String document);
    List<PersonResponseDTO> findAll();
    PersonResponseDTO createPerson(PersonRequestDTO requestDTO);
    PersonResponseDTO updatePerson(PersonRequestDTO requestDTO);
    void deletePerson(String document);
}
