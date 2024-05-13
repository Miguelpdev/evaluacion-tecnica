package com.microservice.service;

import com.microservice.exception.ApiException;
import com.microservice.mapper.CuentaMapper;
import com.microservice.mapper.ResponseCuentaMapper;
import com.microservice.model.Cuenta;
import com.microservice.model.request.RequestApi;
import com.microservice.model.response.ResponseCuenta;
import com.microservice.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CuentaServiceImpl implements CuentaService {

    CuentaRepository cuentaRepository;

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta actualizarCuenta(Long id, Cuenta cuenta) {
        if (cuentaRepository.existsById(id)) {
            cuenta.setId(id);
            return cuentaRepository.save(cuenta);
        }
        return null;
    }

    public boolean eliminarCuenta(Long id) {
        if (cuentaRepository.existsById(id)) {
            cuentaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public ResponseCuenta create(RequestApi request) {
        Cuenta cuenta = cuentaRepository.save(CuentaMapper.buildCuenta(request));
        return ResponseCuentaMapper.buildResponseCuenta(cuenta);
    }

    @Override
    public ResponseCuenta update(RequestApi request) {
        Cuenta cuenta = actualizaCuenta(request);
        cuentaRepository.save(cuenta);
        return ResponseCuentaMapper.buildResponseCuenta(cuenta);
    }

    @Override
    public ResponseCuenta update(Long id, RequestApi requestApi) {
        return null;
    }

    @Override
    public ResponseCuenta delete(String numeroCuenta) {
        return null;
    }

    @Override
    public ResponseCuenta delete(Long id) {
        eliminadaCuenta(id);
        return ResponseCuenta.builder().build();
    }

    @Override
    public ResponseCuenta get(String numeroCuenta) {
        Cuenta cuenta =getCuenta(numeroCuenta);
        return ResponseCuentaMapper.buildResponseCuenta(cuenta);
    }

    private Cuenta getCuenta(String numeroCuenta){
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> {
            throw new ApiException(String.format("No existe la cuenta %s", numeroCuenta));
        });
    }

    private void eliminadaCuenta(Long id){
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> {
                throw new ApiException(String.format("No existe una cuenta registrado con %s", id));
                });
        cuentaRepository.delete(cuenta);
    }

    private Cuenta actualizaCuenta(RequestApi request){
        Cuenta cuenta = getCuenta(request.getNumeroCuenta());
        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setSaldoInicial(request.getSaldoInicial());
        cuenta.setEstado(request.getEstado());
        return cuenta;
    }
}
