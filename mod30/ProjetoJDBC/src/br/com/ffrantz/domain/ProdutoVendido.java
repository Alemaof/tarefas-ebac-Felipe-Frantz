package br.com.ffrantz.domain;

import java.math.BigDecimal;

public class ProdutoVendido implements Persistente{

    private Long id;

    private Produto produto;

    private Integer quantidade;

    private BigDecimal valorTotal;

    public ProdutoVendido() {
        this.quantidade = 0;
        this.valorTotal = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
