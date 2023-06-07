package br.com.srm.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PersonRequestDTO {
    @NonNull
    private String name;
    @NonNull
    private String document;
}
