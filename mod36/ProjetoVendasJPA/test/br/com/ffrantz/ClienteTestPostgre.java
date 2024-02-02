package br.com.ffrantz;

import br.com.ffrantz.dao.ClienteDAOPostgre;
import br.com.ffrantz.dao.IClienteDAO;
import br.com.ffrantz.entity.Cliente;
import br.com.ffrantz.service.ClienteService;
import br.com.ffrantz.service.IClienteService;
import jakarta.persistence.EntityExistsException;
import org.junit.After;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class ClienteTestPostgre {

    private IClienteService clienteService;

    private IClienteDAO clienteDAO;

    private Cliente cliente;

    public ClienteTestPostgre() {
        this.clienteDAO = new ClienteDAOPostgre();
        this.clienteService = new ClienteService(clienteDAO);
        cliente = cadastrarCliente(123456789l);
    }
    @Test
    public void cadastrarTest() {

        cliente = clienteService.cadastrar(cliente);

        assertNotNull(cliente);
        assertNotNull(cliente.getId());
    }

    @Test
    public void buscarTest() {
        cliente = clienteService.cadastrar(cliente);

        Cliente clienteBuscado = clienteService.consultar(cliente.getId());

        assertNotNull(clienteBuscado);
        assertEquals(clienteBuscado.getNome(),cliente.getNome());
    }

    @Test
    public void atualizarTest() {

        cliente = clienteService.cadastrar(cliente);
        cliente.setNome("Gabriel");

        Cliente clienteAtualiado = clienteService.alterar(cliente);

        assertNotNull(clienteAtualiado);
        assertEquals(cliente.getNome(), clienteAtualiado.getNome());
    }

    @Test
    public void excluirTest() {
        Cliente clienteCadastrado = clienteService.cadastrar(cliente);
        clienteService.excluir(clienteCadastrado);

        clienteCadastrado = clienteService.consultar(clienteCadastrado.getId());

        assertNull(clienteCadastrado);
    }

    @Test
    public void buscarTodosTest() {
        clienteService.cadastrar(cliente);
        clienteService.cadastrar(cadastrarCliente(987654321l));
        clienteService.cadastrar(cadastrarCliente(9876541l));

        Collection<Cliente> clientes = clienteService.buscarTodos();

        assertEquals(clientes.size(),3);
    }

    @Test(expected = EntityExistsException.class)
    public void cadastrarMesmoCPF() {
        cliente = clienteService.cadastrar(cliente);
        Cliente cliente2 = clienteService.cadastrar(cliente);

        assertEquals(cliente,cliente2);
    }

    @After
    public void excluirTodos() {
        Collection<Cliente> clientes = clienteService.buscarTodos();
        clientes.forEach(cliente ->
                clienteService.excluir(cliente));
    }

    private Cliente cadastrarCliente(Long cpf) {
        Cliente cliente = new Cliente();
        cliente.setNome("Felipe");
        cliente.setCpf(cpf);
        cliente.setEndereco("Endereco");
        cliente.setTelefone(11999999999l);
        cliente.setCidade("Sao Paulo");
        cliente.setEstado("SP");
        cliente.setIdade(38);
        return cliente;
    }
}
