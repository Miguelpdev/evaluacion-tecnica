package com.microservice.controller;

import com.microservice.model.ReporteEstadoCuenta;
import com.microservice.service.ReporteService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping
    public ResponseEntity<?> generarReporte(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            @RequestParam("clienteId") Long clienteId) {

        // Generar el reporte utilizando el servicio de reportes
        ReporteEstadoCuenta reporte = reporteService.generarReporteEstadoCuenta(fechaInicio, fechaFin, clienteId);

        return ResponseEntity.ok(reporte); // Retornar el reporte en formato JSON
    }
}
