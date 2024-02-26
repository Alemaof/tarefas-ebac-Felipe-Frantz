package br.com.ffrantz.vendas.online.repository;

import br.com.ffrantz.vendas.online.entity.Venda;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IVendaRepository extends MongoRepository<Venda, String> {

    Optional<Venda> findByCodigo(String codigo);
}
