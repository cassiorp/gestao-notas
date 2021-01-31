package br.com.gomining.gestaonotas.controller.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class StandardError {

    private Integer status;
    private String message;
    private OffsetDateTime dataHora;

}