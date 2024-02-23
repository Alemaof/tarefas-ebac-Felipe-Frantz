package br.com.ffrantz.vendas.online.resource;

import br.com.ffrantz.vendas.online.entity.Cliente;
import br.com.ffrantz.vendas.online.usecase.BuscaCliente;
import br.com.ffrantz.vendas.online.usecase.CadastraCliente;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    private BuscaCliente buscaCliente;
    private CadastraCliente cadastraCliente;

    @Autowired
    public ClienteResource(BuscaCliente buscaCliente,
                           CadastraCliente cadastraCliente) {
        this.buscaCliente = buscaCliente;
        this.cadastraCliente = cadastraCliente;
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> buscar(Pageable pageable) {
        return ResponseEntity.ok(buscaCliente.buscar(pageable));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um cliente pelo id")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(buscaCliente.buscarPorId(id));
    }

    @GetMapping(value = "isCadastrado/{id}")
    public ResponseEntity<Boolean> isCadastrado(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(buscaCliente.isCadastrado(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(cadastraCliente.cadastrar(cliente));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Busca um cliente pelo cpf")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable(value = "cpf", required = true) Long cpf) {
        return ResponseEntity.ok(buscaCliente.buscarPorCpf(cpf));
    }

    @PutMapping
    @Operation(summary = "Atualiza um cliente")
    public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(cadastraCliente.atualizar(cliente));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um cliente pelo seu identificador único")
    public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
        cadastraCliente.remover(id);
        return ResponseEntity.ok("Removido com sucesso");
    }
}
