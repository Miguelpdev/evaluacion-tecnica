package com.microservice.service;

import com.microservice.model.ReporteEstadoCuenta;

import java.time.LocalDate;
import java.util.Date;

public interface ReporteService {
    ReporteEstadoCuenta generarReporteEstadoCuenta(Date fechaInicio, Date fechaFin, Long clienteId);

}
