package com.microservice.service;

import com.microservice.enumeration.Genero;
import com.microservice.exception.ApiException;
import com.microservice.mapper.ClienteMapper;
import com.microservice.mapper.ResponseClienteMapper;
import com.microservice.model.Cliente;
import com.microservice.model.Persona;
import com.microservice.model.request.RequestApi;
import com.microservice.model.response.ResponseCliente;
import com.microservice.repository.ClienteRepository;
import com.microservice.repository.PersonaRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

    PersonaRepository personaRepository;

    ClienteRepository clienteRepository;

    @Transactional
    @Override
    public ResponseCliente create(RequestApi request) {
        Cliente cliente = clienteRepository.save(ClienteMapper.buildCliente(request));
        return ResponseClienteMapper.buildResponseCliente(cliente);
    }

    @Transactional
    @Override
    public ResponseCliente update(RequestApi request) {
        Cliente cliente = actualizaCliente(request);
        clienteRepository.save(cliente);
        return ResponseClienteMapper.buildResponseCliente(cliente);
    }

    @Override
    public ResponseCliente update(Long id, RequestApi request) {
        return null;
    }

    @Override
    public ResponseCliente delete(String identificacion) {
        Cliente cliente = eliminadoLogicoCliente(identificacion);
        clienteRepository.save(cliente);
        return ResponseClienteMapper.buildResponseCliente(cliente);
    }

    @Transactional
    @Override
    public ResponseCliente delete(Long id) {
        eliminadaCliente(id);
        return ResponseCliente.builder().build();
    }

    @Override
    public ResponseCliente get(String identificacion) {
        Cliente cliente = getCliente(identificacion);
        return ResponseClienteMapper.buildResponseCliente(cliente);
    }

    private Cliente actualizaCliente(RequestApi request) {
        Cliente cliente = getCliente(request.getIdentificacion());
        // DNI, EDAD por lo general son inmutables
        cliente.setGenero(Genero.valueOf(request.getGenero()));
        cliente.setNombre(request.getNombres());
        cliente.setDireccion(request.getDireccion());
        cliente.setTelefono(request.getTelefono());
        cliente.setContrasena(request.getConstrasena());
        cliente.setEstado(request.getEstado());
        return cliente;
    }

    private Cliente getCliente(String identificacion) {
        // Si el cliente no existe, lanza una excepcion
        return clienteRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> {
                    throw new ApiException(String.format("No existe un cliente registrado con documento %s", identificacion));
                });
    }

    private Cliente eliminadoLogicoCliente(String identificacion) {
        Cliente cliente = getCliente(identificacion);
        if(!cliente.getEstado())
            throw new ApiException(String.format("El cliente registrado con documento %s ya esta inactivo", identificacion));
        // Borrado logico
        cliente.setEstado(Boolean.FALSE);
        return cliente;
    }

    private void eliminadaCliente(Long id) {
        // Si el cliente no existe, lanza una excepcion
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ApiException(String.format("No existe un cliente registrado con identificador %s", id));
                });
        clienteRepository.delete(cliente);
    }

}
