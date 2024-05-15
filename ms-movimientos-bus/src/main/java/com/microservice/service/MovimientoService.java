package com.microservice.service;

import com.microservice.model.Movimiento;

import java.util.List;

public interface MovimientoService {

    List<Movimiento> obtenerTodosLosMovimientos();
    Movimiento obtenerMovimientoPorId(Long id);
    Movimiento crearMovimiento(Movimiento movimiento);
    Movimiento actualizarMovimiento(Long id, Movimiento movimiento);
    boolean eliminarMovimiento(Long id);
    Movimiento realizarMovimiento(Movimiento movimiento);
}
