package br.com.ffrantz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ffrantz.entity.Cliente;

@Repository
public interface IClienteRepository extends CrudRepository<Cliente, Long> {

}
