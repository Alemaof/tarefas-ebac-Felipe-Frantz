package br.com.ffrantz.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_VENDA")
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

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="venda_seq")
    @SequenceGenerator(name="venda_seq", sequenceName="sq_venda", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODIGO", nullable = false, unique = true)
    private Long codigo;

    @OneToOne
    @JoinColumn(name = "id_cliente_fk",
            foreignKey = @ForeignKey(name = "fk_venda_cliente"),
            referencedColumnName = "id", nullable = false
        )
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private Set<ProdutoVendido> produtoVendido;

    @Column(name = "VALORTOTAL", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "DATADAVENDA", nullable = false)
    private Instant dataDaVenda;

    @Column(name = "STATUS", nullable = false)
    private Status status;
    
    public Venda() {
    	produtoVendido = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

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
