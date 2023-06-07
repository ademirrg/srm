package br.com.srm.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "person")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "document")
    private String document;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "dt_create")
    private LocalDateTime createDate;

    @Column(name = "dt_modify")
    private LocalDateTime modifyDate;
}
