package br.com.ffrantz.vendas.online.usecase;

import br.com.ffrantz.vendas.online.entity.Cliente;
import br.com.ffrantz.vendas.online.repository.IClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastraCliente {

    private IClienteRepository clienteRepository;

    @Autowired
    public CadastraCliente(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(@Valid Cliente cliente) {
        return this.clienteRepository.insert(cliente);
    }

    public Cliente atualizar(@Valid Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public void remover(String id) {
        this.clienteRepository.deleteById(id);
    }
}
