package com.microservice.model;

import java.util.List;
import java.util.Map;

public class ReporteEstadoCuenta {
    private List<Cuenta> cuentas;
    private Map<Cuenta, List<Movimiento>> detalleMovimientos;

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Map<Cuenta, List<Movimiento>> getDetalleMovimientos() {
        return detalleMovimientos;
    }

    public void setDetalleMovimientos(Map<Cuenta, List<Movimiento>> detalleMovimientos) {
        this.detalleMovimientos = detalleMovimientos;
    }
}
