package br.com.srm.business.impl;

import br.com.srm.business.PersonBusiness;
import br.com.srm.dto.PersonRequestDTO;
import br.com.srm.dto.PersonResponseDTO;
import br.com.srm.entity.Person;
import br.com.srm.enums.DocumentTypeEnum;
import br.com.srm.enums.ExceptionEnum;
import br.com.srm.exception.BusinessException;
import br.com.srm.repository.PersonRepository;
import br.com.srm.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        Optional<Person> optPerson = repository.findByDocument(StringUtils.removeMask(requestDTO.getDocument()));
        if (optPerson.isPresent()) {
            throw new BusinessException(ExceptionEnum.CPF_ALREADY_EXIST.getMessage());
        }
        Person person = mapper.map(requestDTO, Person.class);
        person.setDocument(StringUtils.removeMask(requestDTO.getDocument()));
        person.setDocumentType(person.getDocument().length() > 11 ? "CNPJ" : "CPF");
        person.setCreateDate(LocalDateTime.now());
        return mapper.map(repository.save(person), PersonResponseDTO.class);
    }

    @Override
    public PersonResponseDTO updatePerson(PersonRequestDTO requestDTO, Long id) {
        if (repository.existsById(id)) {
            Person person = repository.findById(id).get();
            person.setDocument(StringUtils.removeMask(requestDTO.getDocument()));
            person.setDocumentType(person.getDocument().length() > 11 ? DocumentTypeEnum.CNPJ.getDescription() : DocumentTypeEnum.CPF.getDescription());
            person.setModifyDate(LocalDateTime.now());
            return mapper.map(repository.save(person), PersonResponseDTO.class);
        }
        return null;
    }

    @Override
    public void deletePerson(Long id) {
        repository.findById(id).ifPresent(person -> repository.deleteById(person.getId()));
    }
}
