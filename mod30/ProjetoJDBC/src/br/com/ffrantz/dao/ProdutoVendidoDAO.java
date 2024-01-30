package br.com.ffrantz.dao;

import br.com.ffrantz.connection.ConnectionFactory;
import br.com.ffrantz.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProdutoVendidoDAO implements IProdutoVendidoDAO {

    Produto produto = new Produto();


    @Override
    public Map<Long, Integer> searchAll(Long id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM tb_produtosvendidos WHERE id_venda_fk = ?");
        stm.setLong(1,id);
        ResultSet rs = stm.executeQuery();
        Long prod = null;
        Integer qtt = null;
        Map<Long, Integer> prods = new HashMap<>();
        while (rs.next()) {
             prod = rs.getLong("id_produto_fk");
             qtt = rs.getInt("quantidade");
             prods.put(prod,qtt);
        }
        return prods;
    }
}
