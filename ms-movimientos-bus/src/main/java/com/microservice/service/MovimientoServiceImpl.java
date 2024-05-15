package com.microservice.service;

import com.microservice.exception.HandleException;
import com.microservice.model.Cuenta;
import com.microservice.model.Movimiento;
import com.microservice.repository.MovimientoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MovimientoServiceImpl implements MovimientoService {

    MovimientoRepository movimientoRepository;
    CuentaService cuentaService;

    public List<Movimiento> obtenerTodosLosMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    public Movimiento crearMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public Movimiento actualizarMovimiento(Long id, Movimiento movimiento) {
        if (movimientoRepository.existsById(id)) {
            movimiento.setId(id);
            return movimientoRepository.save(movimiento);
        }
        return null;
    }

    public boolean eliminarMovimiento(Long id) {
        if (movimientoRepository.existsById(id)) {
            movimientoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Movimiento realizarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(movimiento.getCuentaId().getId());
        if (cuenta.getSaldoInicial().compareTo(movimiento.getValor()) < 0) {
            //throw new HandleException.SaldoNoDisponibleException("Saldo no disponible");
            throw new RuntimeException();
        }
        // Realizar el movimiento y actualizar el saldo
        // movimientoRepository.save(movimiento);
        // cuenta.setSaldo(cuenta.getSaldo().subtract(movimiento.getValor()));
        // cuentaService.actualizarCuenta(cuenta);

        // Actualizar el saldo de la cuenta
        cuenta.setSaldoInicial(cuenta.getSaldoInicial().subtract(movimiento.getValor()));
        cuentaService.actualizarCuenta(cuenta.getId(), cuenta);

        // Guardar el movimiento en la base de datos
        Movimiento nuevoMovimiento = movimientoRepository.save(movimiento);
        return movimiento;
    }
//    public Movimiento realizarMovimiento(Movimiento movimiento) {
//        // Obtener la cuenta asociada al movimiento
//        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuentaId())
//                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada"));
//
//        // Verificar si el saldo disponible es suficiente para el movimiento
//        if (cuenta.getSaldo().compareTo(movimiento.getValor()) < 0) {
//            throw new SaldoNoDisponibleException("Saldo no disponible");
//        }
//
//        // Actualizar el saldo de la cuenta
//        cuenta.setSaldo(cuenta.getSaldo().subtract(movimiento.getValor()));
//        cuentaRepository.save(cuenta);
//
//        // Guardar el movimiento en la base de datos
//        Movimiento nuevoMovimiento = movimientoRepository.save(movimiento);
//
//        return nuevoMovimiento;
//    }
}
