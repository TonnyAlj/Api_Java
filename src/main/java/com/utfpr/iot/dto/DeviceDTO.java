package com.utfpr.iot.dto;

public record DeviceDTO(
    Long idGateway,
    String nome,
    String descricao,
    String localizacao,
    String enderecoIP
) {


}
