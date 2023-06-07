package br.com.srm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    CPF_ALREADY_EXIST(400, "CPF já cadastrado em nossa base.");

    private Integer code;
    private String message;
}
