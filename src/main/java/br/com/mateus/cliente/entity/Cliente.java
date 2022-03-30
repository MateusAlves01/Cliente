package br.com.mateus.cliente.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data // Criar Gets e Sets e ToString
@AllArgsConstructor //Criar construtor com as propriedades
@NoArgsConstructor // Cria um constructor vazio sem argumentos
@Builder //Ajuda na constrção de objetos clientes
@Entity // Informa que é um entidade do BD

public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;
}
