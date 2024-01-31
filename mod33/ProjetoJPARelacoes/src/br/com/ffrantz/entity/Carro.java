package br.com.ffrantz.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CARRO")
public class Carro implements Persistente {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="carro_seq")
    @SequenceGenerator(name="carro_seq", sequenceName="sq_carro", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "ANO")
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "marca_id",
        foreignKey = @ForeignKey(name = "fk_marca_carro"),
        referencedColumnName = "id", nullable = false)
    private Marca marca;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "TB_CARRO_ACESSORIO",
            joinColumns = {@JoinColumn(name = "ID_CARRO_FK")},
            inverseJoinColumns = {@JoinColumn(name = "ID_ACESSORIO_FK")}
    )
    private List<Acessorio> acessorios;

    public Carro(){
        this.acessorios = new ArrayList<Acessorio>();
    }

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

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }

    public void addAcessorio (Acessorio acessorio) {
        this.acessorios.add(acessorio);
    }
}
