package com.microservice.controller;

import com.microservice.constants.Constantes;
import com.microservice.model.request.RequestApi;
import com.microservice.model.response.ResponseApi;
import com.microservice.model.response.ResponseCliente;
import com.microservice.service.ClienteService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/clientes")
public class ClienteRestController {

    private final ClienteService clienteService;

    @PostMapping(path = "create", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> crearCliente(@Valid @RequestBody RequestApi request) {
        ResponseCliente resultado = clienteService.create(request);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "update", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> actualizarCliente(@Valid @RequestBody RequestApi request) {
        ResponseCliente resultado = clienteService.update(request);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }

    @PatchMapping("/update/{clienteId}/informacion")
    public ResponseEntity<ResponseApi> actualizarEstadoCliente(
            @PathVariable(name = "clienteId") Long id,
            @RequestBody RequestApi requestApi) {
        ResponseCliente resultado = clienteService.update(id, requestApi);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "update-parcial", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> actualizacionParcialCliente(@Valid @RequestBody RequestApi request) {
        ResponseCliente resultado = clienteService.update(request);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "delete/identificacion/{identificacion}", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> desactivarCliente(@Valid @PathVariable(name = "identificacion") String identificacion) {
        ResponseCliente resultado = clienteService.delete(identificacion);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }


    @DeleteMapping(path = "delete/id/{clienteId}", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> eliminarCliente(@Valid @PathVariable(name = "clienteId") Long id) {
        ResponseCliente resultado = clienteService.delete(id);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "get/{identificacion}", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<ResponseApi> getCliente(@Valid @PathVariable(name = "identificacion") String identificacion) {
        ResponseCliente resultado = clienteService.get(identificacion);
        return new ResponseEntity<>(
                ResponseApi.builder()
                        .mensaje(Constantes.SUCCESS_OPERATION)
                        .resultado(resultado)
                        .build(),
                HttpStatus.OK
        );
    }

}
