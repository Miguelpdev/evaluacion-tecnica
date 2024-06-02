# Evaluacion Técnica/Práctica
Evaluacion tecnica para Java Backend Developer
## Arquitectura Microservicio
Indicaciones generales
* Aplique todas las buenas prácticas de programación, patrones de diseño (ejemplo: Repository), etc. que considere necesario (se tomará en cuenta este punto para la calificación).
* El manejo de entidades se debe manejar JPA .
* Se debe aplicar un manejo de excepciones.
* Se debe realizar como mínimo una prueba unitaria.
* La solución se debe desplegar y funcionar en Docker.
* Posterior a la entrega de este ejercicio, dependiendo de los resultados, se podrá agendar una entrevista técnica donde deberás defender la solución planteada.
Herramientas y tecnologías utilizadas
* Si aplicaste para una posición con Java, utilizar Java spring boot.
Requerimiento de Microservicios y Funcionalidades
Separar en 2 microservicios, agrupando (Cliente, Persona) y (Cuenta,
Movimientos donde se contemple una comunicación asincrónica entre los 2 microservicios. Cumplir las funcionalidades: F1, F2, F3, F4, F5 deseable la
funcionalidad F6.
* Consideraciones Adicionales (para obtener la máxima puntuación):
Si quieres sobresalir, además de cumplir con los requisitos mencionados anteriormente, se espera la implementación de las funcionalidades F6 y F7.
La solución debe contemplar (no necesariamente implementado) factores como:
rendimiento, escalabilidad, resiliencia.
Generación de Api Rest "Application Programming Interface"
Manejar los verbos: Get, Post, Put, Patch, Delete

#### Persona
* Implementar la clase persona con los siguientes datos: nombre, género, edad, identificación, dirección, teléfono
* Debe manera su clave primaria (PK)
#### Cliente
* Cliente debe manejar una entidad, que herede de la clase persona.
* Un cliente tiene: clienteid, contraseña, estado.
* El cliente debe tener una clave única. (PK)
#### Cuenta
* Cuenta debe manejar una entidad.
* Una cuenta tiene: número cuenta, tipo cuenta, saldo Inicial, estado.
* Debe manejar su clave única.
#### Movimientos
* Movimientos debe manejar una entidad.
* Un movimiento tiene: Fecha, tipo movimiento, valor, saldo.
* Debe manejar su clave única.

## Funcionalidades del API
Los API's debe tener las siguientes operaciones:
### F1: Generación de CRUDS (Crear, editar, actualizar y eliminar registros - Entidades: Cliente, Cuenta y Movimiento).
Los nombres de los endpoints a generar son:
* /cuentas
* /clientes
* /movimientos
### F2: Registro de movimentos: al registrar un movimiento en la cuenta se debe tener en cuent	a lo siguiente:
* Para un movimiento se pueden tener valores positivos o negativos.
* Al realizar un movimiento se debe actualizar el saldo disponible.
* Se debe levar el registro de las transaciones realizadas.
### F3: Registro de movimientos: Al realizar un movimiento el cual no cuente con saldo, debe alertar mediante el siguiente mensaje "Saldo no disponible"
* Defina, según su expertise, la mejor manera de capturar y mostrar el error.
### F4: Reportes: Generar un reporte de "Estado de cuenta" especificando un rango de fechas y cliente.
* Este reporte debe contener:
* Cuentas asociadas con sus respectivos saldos
* Detalle de movimientos de las cuentas
* El endpoint que se debe utilizar para esto debe ser el siguiente:
```
 /reportes?fecha=rango fechas & cliente)
```
* El servicio del reporte debe retornar la información en formato JSON
* Defina, según su expertise, la mejor manera de solicitar y retornar esta información.
### F5: Pruebas unitarias: Implementar 1 prueba unitaria para la entidad de dominio Cliente.
### F6: Pruebas de Integración: Implementar 1 prueba de integración.
### F7: Despliegue de la solución en contenedores.
## Casos de uso (ejemplos)

1. Creacion de Usuarios

| Nombres  | Direccion | Telefono  | Contraseña | Estado |
|----------|-----------|-----------|------------|--------|
| Jose Lema| Otavio sn | 098254785 |1234|True|
| Marianela Montalvo| Amazonas y NNuU  | 097548065  |5676|True|
| Juan Osorio| 13 junio   | 098874587   |1245|True|

2. Creacion de Cuentas de Usuario

| Numero cuenta | Tipo | saldo inicial | estado | cliente |
|----------|-----------|----------|-------|--------|
| 478758   | Ahorros   | 2000  | True | Jose Lema |
| 225487   | Corriente | 100   | True | Marianela Montalvo |
| 495878   | Ahorros   | 0   | True | Juan Osorio |
| 496825   | Ahorros   | 540 | True | Marianela Montalvo |

3. Crear una nueva Cuenta Corriente para Jose Lema

| Numero cuenta | Tipo | saldo inicial | estado | cliente |
|----------|-----------|----------|-------|--------|
| 585545   | Corriente   | 1000  | True | Jose Lema |

4. Realizar los siguientes movimientos

| Numero cuenta | Tipo | saldo inicial | estado | movimiento |
|----------|-----------|----------|-------|--------|
| 478758   | Ahorros   | 2000  | True | Retiro de 575 |
| 225487   | Corriente | 100   | True | Deposito de 600 |
| 495878   | Ahorros   | 0     | True | Deposito de 150 |
| 496825   | Ahorros   | 540   | True | Retiro de 540 |

5. Listado de Movimiento por fecha x usuario

| Fecha   | Cliente   | Numero cuenta | Tipo   | Saldo inicial | Estado | Movimiento | Saldo Disponible |
|---------|-----------|---------------|--------|---------------|--------|------------|------------------|
|16/02/2022|Marianela Montalvo|225487|Corriente|100|True|600|700|
|08/02/2022|Marianela Montalvo|496825|Ahorros|540|True|-540|0|

- Ejemplo Json:
```
{
    "Fecha":"10/02/2022",
    "Cliente":"Marianela Montalvo",
    "Numero Cuenta":"225487",
    "Tipo":"Corriente",
    "Saldo Inicial":100,
    "Estado":true,
    "Movimiento":600,
    "Saldo Disponible":700
}
```
## Instrucciones de despliegue / Entregables
- Generar el script de base de datos, entidades y esquemas datos. con el nombre BaseDatos.sql.
- Incluir archivo Json, de aplicacion Postman, pra validacion de los endpoints. (http://{servidor}:{puerto}/api/{metodo}...{parametros})
- En el caso de usar Karate, incluir el set de pruebas en el proyecto Java, garantizando que se ejecuten junto a todos los tests.