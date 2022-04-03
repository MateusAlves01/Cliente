package br.com.mateus.cliente.dto;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotNull(message = "Nome é Obrigatório !")
    @Length(min = 3, max = 255)
    private String nomeCompleto;

    @NotNull(message = "{cpf.notNull}")
    @CPF(message = "{cpf}")
    @Length(min = 11, max = 14)
    private String cpf;

    @NotNull(message = "{email.notNull}")
    @Email(message = "{email}")
    private String email;

    @Length(min = 2, max = 3)
    private String ddd;

    @Length(min = 5, max = 30)
    private String telefone;



}
