package com.microservice.service;

import com.microservice.model.ReporteEstadoCuenta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface ReporteService {
    ReporteEstadoCuenta generarReporteEstadoCuenta(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long clienteId);

}
