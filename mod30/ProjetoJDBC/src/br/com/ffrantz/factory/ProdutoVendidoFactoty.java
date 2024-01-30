package br.com.ffrantz.factory;

import br.com.ffrantz.domain.Produto;
import br.com.ffrantz.domain.ProdutoVendido;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoVendidoFactoty {

    public static ProdutoVendido convert(ResultSet rs) throws SQLException {
        Produto prod = ProdutoFactory.convert(rs);
        ProdutoVendido prodV = new ProdutoVendido();
        prodV.setProduto(prod);
        prodV.setId(rs.getLong("ID"));
        prodV.setQuantidade(rs.getInt("QUANTIDADE"));
        prodV.setValorTotal(rs.getBigDecimal("VALOR_TOTAL"));
        return prodV;
    }
}
