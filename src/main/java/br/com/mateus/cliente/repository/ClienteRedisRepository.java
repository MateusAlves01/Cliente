package br.com.mateus.cliente.repository;

import br.com.mateus.cliente.document.ClienteRedis;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRedisRepository extends CrudRepository<ClienteRedis, String> {
}