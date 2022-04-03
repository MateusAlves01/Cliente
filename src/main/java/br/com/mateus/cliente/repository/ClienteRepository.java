package br.com.mateus.cliente.repository;

import br.com.mateus.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findByNomeContainingIgnoreCase(String nome);
    public Cliente findByEmail(String email);
    public Cliente findByCpf(String cpf);
}
