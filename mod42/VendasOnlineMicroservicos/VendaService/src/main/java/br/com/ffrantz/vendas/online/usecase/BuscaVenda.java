package br.com.ffrantz.vendas.online.usecase;

import br.com.ffrantz.vendas.online.entity.Venda;
import br.com.ffrantz.vendas.online.exception.EntityNotFoundException;
import br.com.ffrantz.vendas.online.repository.IVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscaVenda {

    private IVendaRepository vendaRepository;

    @Autowired
    public BuscaVenda(IVendaRepository produtoRepository) {
        this.vendaRepository = produtoRepository;
    }

    public Page<Venda> buscar(Pageable pageable) {
        return vendaRepository.findAll(pageable);
    }

    public Venda buscarPorCodigo(String codigo) {
        return vendaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException(Venda.class, "codigo", codigo));
    }
}
