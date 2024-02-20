package br.com.ffrantz.entity;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO")
@NamedQuery(name = "Produto.findByNome", query = "SELECT c FROM Produto c WHERE c.nome LIKE :nome") 
public class Produto implements Persistente {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="produto_seq")
    @SequenceGenerator(name="produto_seq", sequenceName="sq_produto", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NOME", length = 50, nullable = false)
    private String nome;

    @Column(name = "CODIGO", nullable = false, unique = true)
    private Long codigo;

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Column(name = "VALOR", nullable = false)
    private BigDecimal valor;
    
    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
