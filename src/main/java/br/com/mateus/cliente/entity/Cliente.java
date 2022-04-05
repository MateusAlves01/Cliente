package br.com.mateus.cliente.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter // Criar Gets e Sets e ToString
@AllArgsConstructor //Criar construtor com as propriedades
@NoArgsConstructor // Cria um constructor vazio sem argumentos
@Builder //Ajuda na constrção de objetos clientes
@Entity // Informa que é um entidade do BD
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "Sobrenome", nullable = false)
    private String sobrenome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column (length = 3)
    private String ddd;

    @Column(length = 50)
    private  String telefone;


}
