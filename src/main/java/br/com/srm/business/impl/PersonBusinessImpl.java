package br.com.srm.business.impl;

import br.com.srm.business.PersonBusiness;
import br.com.srm.dto.PersonRequestDTO;
import br.com.srm.dto.PersonResponseDTO;
import br.com.srm.entity.Person;
import br.com.srm.enums.DocumentTypeEnum;
import br.com.srm.repository.PersonRepository;
import br.com.srm.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonBusinessImpl implements PersonBusiness {
    
    @Autowired @Lazy
    PersonRepository repository;
    
    @Autowired
    ModelMapper mapper;
    
    @Override
    public PersonResponseDTO findByDocument(String document) {
        Optional<Person> person = repository.findByDocument(StringUtils.removeMask(document));
        return person.map(value -> mapper.map(value, PersonResponseDTO.class)).orElse(null);
    }

    @Override
    public List<PersonResponseDTO> findAll() {
        List<Person> persons = repository.findAll();
        return persons.stream()
                .map(person -> mapper.map(person, PersonResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonResponseDTO createPerson(PersonRequestDTO requestDTO) {
        Person person = mapper.map(requestDTO, Person.class);
        person.setDocument(StringUtils.removeMask(requestDTO.getDocument()));
        person.setDocumentType(person.getDocument().length() > 11 ? "CNPJ" : "CPF");
        person.setCreateDate(LocalDateTime.now());
        return mapper.map(repository.save(person), PersonResponseDTO.class);
    }

    @Override
    public PersonResponseDTO updatePerson(PersonRequestDTO requestDTO) {
        if (repository.findByDocument(StringUtils.removeMask(requestDTO.getDocument())).isPresent()) {
            Person person = mapper.map(requestDTO, Person.class);
            person.setDocument(StringUtils.removeMask(requestDTO.getDocument()));
            person.setDocumentType(person.getDocument().length() > 11 ? DocumentTypeEnum.CNPJ.getDescription() : DocumentTypeEnum.CPF.getDescription());
            person.setModifyDate(LocalDateTime.now());
            return mapper.map(repository.save(person), PersonResponseDTO.class);
        }
        return null;
    }

    @Override
    public void deletePerson(String document) {
        repository.findByDocument(StringUtils.removeMask(document)).ifPresent(person -> repository.deleteById(person.getId()));
    }
}
