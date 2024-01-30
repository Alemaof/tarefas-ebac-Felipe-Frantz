package br.com.ffrantz.factory;

import br.com.ffrantz.domain.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteFactory {

    public static Cliente convert(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("ID_CLIENTE"));
        cliente.setNome(rs.getString(("NOME")));
        cliente.setCpf(rs.getLong(("CPF")));
        cliente.setIdade(rs.getInt("IDADE"));
        cliente.setTelefone(rs.getLong(("TELEFONE")));
        cliente.setEndereco(rs.getString(("ENDERECO")));
        cliente.setCidade(rs.getString(("CIDADE")));
        cliente.setEstado(rs.getString(("ESTADO")));
        return cliente;
    }
}
