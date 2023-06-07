package br.com.srm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentTypeEnum {
    CPF("CPF"),
    CNPJ("CNPJ");

    private final String description;
}
