package br.com.mateus.cliente.http.controller;


import br.com.mateus.cliente.document.ClienteRedis;
import br.com.mateus.cliente.dto.ClienteRequestDTO;
import br.com.mateus.cliente.dto.ClienteResponseDTO;
import br.com.mateus.cliente.entity.Cliente;
import br.com.mateus.cliente.service.ClienteService;
import br.com.mateus.cliente.service.ClienteServiceRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteServiceRedis clienteServiceRedis;

    @PostMapping
    public ClienteResponseDTO criarCliente(@Validated @RequestBody ClienteRequestDTO clienteRequestDTO){

        ClienteResponseDTO clienteSalvo = clienteService.criar(clienteRequestDTO);
        return clienteSalvo;
    }
    @PostMapping ("async")
    public ClienteRedis criarClienteRedis (@Validated @RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteRedis clienteSalvo = clienteServiceRedis.salvar(clienteRequestDTO);
        return clienteSalvo;
    }
    @PutMapping("async")
    public void sicronizarClienteBancoDados(){
        clienteService.sicronizarClienteBancoDados();
    }


    @GetMapping
    public List<ClienteResponseDTO> listarClientes(@RequestParam(required = false) String nome){
        return clienteService.listarClientes(nome);
    }

    @GetMapping("/{email}/email")
    public ClienteResponseDTO consultarPorEmail(@PathVariable String email){
        return clienteService.consultarPorEmail(email);
    }

    @GetMapping("/{cpf}/cpf")
    public ClienteResponseDTO consultarPorCpf( @PathVariable String cpf){
        return clienteService.consultarPorCpf(cpf);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable String email) throws Exception {
        clienteService.deletarCliente(email);
    }

    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable String email, @Validated @RequestBody ClienteRequestDTO clienteRequestDTO) throws Exception {
        clienteService.atualizarCliente(clienteRequestDTO, email);
    }






}
