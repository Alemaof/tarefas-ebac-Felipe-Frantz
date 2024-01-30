package br.com.ffrantz.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public class Venda implements Persistente {

    public enum Status {
        INICIADA, CONCLUIDA, CANCELADA;

        public static Status getByName(String value) {
            for (Status status : Status.values()) {
                if(status.name().equals(value)) {
                    return status;
                }
            }
            return null;
        }

    }

    private Long id;

    private Long codigo;

    private Cliente cliente;

    private Set<ProdutoVendido> produtoVendido;

    private BigDecimal valorTotal;

    private Instant dataDaVenda;

    private Status status;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ProdutoVendido> getProdutoVendido() {
        return produtoVendido;
    }

    public void setProdutoVendido(Set<ProdutoVendido> produtoVendido) {
        this.produtoVendido = produtoVendido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Instant getDataDaVenda() {
        return dataDaVenda;
    }

    public void setDataDaVenda(Instant dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
