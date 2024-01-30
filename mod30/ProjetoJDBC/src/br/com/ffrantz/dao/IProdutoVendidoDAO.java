package br.com.ffrantz.dao;

import br.com.ffrantz.domain.ProdutoVendido;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

public interface IProdutoVendidoDAO {

    Map<Long, Integer> searchAll(Long id) throws SQLException;
}
