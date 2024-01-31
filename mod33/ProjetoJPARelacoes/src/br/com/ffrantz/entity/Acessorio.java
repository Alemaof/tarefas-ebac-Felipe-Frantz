package br.com.ffrantz.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_ACESSORIO")
public class Acessorio implements Persistente {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="acessorio_seq")
    @SequenceGenerator(name="acessorio_seq", sequenceName="sq_acessorio", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToMany(mappedBy = "acessorios")
    private List<Carro> carros;

    public Acessorio(){
        this.carros = new ArrayList<>();
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

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public void addCarro (Carro carro) {
        this.carros.add(carro);
    }
}
