# ApiRest
## Method POST
- Realizar movimiento

`http://localhost:8082/api/v1/cuentas/{numeroCuenta}/movimientos`
```
{
    "tipoMovimiento": "Depósito",
    "valor": 200.00
}
```
- Crear cuenta

`http://localhost:8082/api/v1/cuentas`
```
{
    "numeroCuenta": "123222222",
    "tipoCuenta": "Ahorros",
    "saldoInicial": 200.00,
    "estado": true,
    "clienteId": 1
}
```
## Method PATCH
- Actualizar tipo cuenta

`http://localhost:8082/api/v1/cuentas/update/8`
```
{
    "tipoCuenta": "Nomina"
}
```
## Method GET
- Obtener reporte por fechas y cliente

`http://localhost:8082/api/v1/reportes?fechaInicio=2024-05-13T14:07:22&fechaFin=2024-05-14T14:07:22&clienteId=2`
