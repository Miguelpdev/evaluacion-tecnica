package com.microservice.mapper;

import com.microservice.model.Cuenta;
import com.microservice.model.request.RequestApi;

public class CuentaMapper {
    public static Cuenta buildCuenta(RequestApi requestApi) {
        return Cuenta.builder()
                .numeroCuenta(requestApi.getNumeroCuenta())
                .tipoCuenta(requestApi.getTipoCuenta())
                .saldoInicial(requestApi.getSaldoInicial())
                .estado(requestApi.getEstado())
                .clienteId(requestApi.getClienteId())
                .build();

    }

}
