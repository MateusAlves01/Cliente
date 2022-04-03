package br.com.mateus.cliente.service;


import br.com.mateus.cliente.dto.ClienteRequestDTO;
import br.com.mateus.cliente.dto.ClienteResponseDTO;
import br.com.mateus.cliente.entity.Cliente;
import br.com.mateus.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MokitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteServiceTest {
    @MockBean
    private ClienteRepository clienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteRequestDTO clienteRequestDTO;
    private Cliente cliente;
    private Cliente clienteSalvo;
    private ClienteResponseDTO clienteResponseDTO;


    @BeforeEach
    void init() {

        clienteRequestDTO = ClienteRequestDTO.builder()
                .nomeCompleto("Mateus Alves")
                .cpf("888.888.888.88")
                .email("mateus@gmail.com")
                .build();

        clienteResponseDTO = ClienteResponseDTO.builder()
                .nomeCompleto("Mateus Alves")
                .cpf("888.888.888.88")
                .enderecoEletronico("alves@gmail.com")
                .build();


        cliente = Cliente.builder()
                .nome("Mateus")
                .sobrenome("Alves")
                .cpf("888.888.888.88")
                .build();

        clienteSalvo = Cliente.builder()
                .id(1L)
                .nome("Mateus")
                .sobrenome("Alves")
                .cpf("888.888.888.88")
                .build();

        when(clienteRepository.save(any())).thenReturn(cliente);

    }


    @Test
    public void criarTest() {


        when(modelMapper.map(clienteRequestDTO, Cliente.class)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(clienteSalvo);
        when(modelMapper.map(clienteSalvo, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO clienteResponseDTOSalvo = clienteService.criar(clienteRequestDTO);
        Assertions.assertNotNull(clienteResponseDTOSalvo);
        Assertions.assertEquals("888.888.888.88", clienteResponseDTOSalvo.getCpf());
        Mockito.verify(clienteRepository, Mockito.times(1)).save(cliente);
    }

    @Test
    public void listarClientesTest() {

        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Ariel Tintel")
                .cpf("888.888.888.88")
                .build();
        Cliente cliente2 = Cliente.builder()
                .id(2L)
                .nome("Ariel Tintel")
                .cpf("888.888.888.88")
                .build();
        Cliente cliente3 = Cliente.builder()
                .id(3L)
                .nome("Ariel Tintel")
                .cpf("888.888.888.88")
                .build();
        List<Cliente> clienteList = Arrays.asList(cliente, cliente2, cliente3);

        when(clienteRepository.findAll()).thenReturn(clienteList);


        when(modelMapper.map(cliente, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);
        when(modelMapper.map(cliente2, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);
        when(modelMapper.map(cliente3, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        List<ClienteResponseDTO> listaClientes = clienteService.listarClientes(null);
        Assertions.assertNotNull(listaClientes);
        Assertions.assertEquals(3, listaClientes.size());
        Mockito.verify(clienteRepository, Mockito.times(1)).findAll();
    }
}
