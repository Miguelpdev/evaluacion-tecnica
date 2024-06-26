package com.microservice.controller;

import com.microservice.constants.Constantes;
import com.microservice.model.Cuenta;
import com.microservice.model.Movimiento;
import com.microservice.model.request.RequestApi;
import com.microservice.model.response.ResponseApi;
import com.microservice.model.response.ResponseCuenta;
import com.microservice.service.CuentaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/cuentas")
public class CuentaRestController {

    private final CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaService.obtenerTodasLasCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
        if (cuenta != null) {
            return ResponseEntity.ok(cuenta);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        Cuenta cuentaActualizada = cuentaService.actualizarCuenta(id, cuenta);
        if (cuentaActualizada != null) {
            return ResponseEntity.ok(cuentaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        if (cuentaService.eliminarCuenta(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "create", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> crearCuentaA(@Valid @RequestBody RequestApi request){
        ResponseCuenta resultado = cuentaService.create(request);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "update",produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> actualizarCuentaA(@Valid @RequestBody RequestApi request){
        ResponseCuenta resultado = cuentaService.update(request);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseApi> actualizarParcialCuenta(@PathVariable(name = "id") Long id, @RequestBody RequestApi requestApi){
        ResponseCuenta resultado = cuentaService.update(id, requestApi);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "delete/{id}", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> eliminarCuentaA(@Valid @PathVariable(name = "id") Long id){
        ResponseCuenta resultado = cuentaService.delete(id);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "get/{numeroCuenta}", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> getCuenta(@Valid @PathVariable(name = "numeroCuenta") String numeroCuenta){
        ResponseCuenta resultado = cuentaService.get(numeroCuenta);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/{numeroCuenta}/movimientos")
    public Movimiento registrarMovimiento(@PathVariable String numeroCuenta, @RequestBody Movimiento movimiento) {
        return cuentaService.registrarMovimiento(numeroCuenta, movimiento);
    }




}
