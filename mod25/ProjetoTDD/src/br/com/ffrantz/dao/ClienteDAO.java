package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.domain.Persistente;

public class ClienteDAO extends GenericDAO<Cliente, Long> {

    public ClienteDAO() {
        super();
    }

    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualizarDados(Cliente entity, Cliente entityCadastrado) {
        entityCadastrado.setCidade(entity.getCidade());
        entityCadastrado.setCpf(entity.getCpf());
        entityCadastrado.setEndereco(entity.getEndereco());
        entityCadastrado.setEstado(entity.getEstado());
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setTelefone(entity.getTelefone());
    }
}
