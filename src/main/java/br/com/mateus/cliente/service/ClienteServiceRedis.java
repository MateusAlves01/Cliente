package br.com.mateus.cliente.service;

import br.com.mateus.cliente.document.ClienteRedis;
import br.com.mateus.cliente.dto.ClienteRequestDTO;
import br.com.mateus.cliente.entity.Cliente;
import br.com.mateus.cliente.repository.ClienteRedisRepository;
import ch.qos.logback.core.net.server.Client;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log4j
@Service
public class ClienteServiceRedis {

    @Autowired
    private ClienteRedisRepository clienteRedisRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ClienteRedis salvar (ClienteRequestDTO clienteRequestDTO){
        ClienteRedis clienteRedis = convertClienteRedis(clienteRequestDTO);
        return clienteRedisRepository.save(clienteRedis);
    }

    public ClienteRedis convertClienteRedis(ClienteRequestDTO clienteRequestDTO) {

        clienteRequestDTO.setCpf(TextoUltils.removeEspecialCaracter(clienteRequestDTO.getCpf()));
        ClienteRedis clienteRedis = modelMapper.map(clienteRequestDTO, ClienteRedis.class);
        setNomeSobreNome(clienteRequestDTO, clienteRedis);
        return clienteRedis;
    }


    public void setNomeSobreNome(ClienteRequestDTO clienteRequestDTO, ClienteRedis clienteRedis) {
        int delimitadorIndex = clienteRequestDTO.getNomeCompleto().indexOf(" ");
        String nome = clienteRequestDTO.getNomeCompleto().substring(0, delimitadorIndex);
        String sobrenome = clienteRequestDTO.getNomeCompleto().substring(delimitadorIndex + 1, clienteRequestDTO.getNomeCompleto().length());

        clienteRedis.setNome(nome);
        clienteRedis.setSobrenome(sobrenome);


    }
}
