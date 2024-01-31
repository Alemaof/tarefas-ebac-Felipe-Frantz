package br.com.ffrantz.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_MARCA")
public class Marca implements Persistente {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="marca_seq")
    @SequenceGenerator(name="marca_seq", sequenceName="sq_marca", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NOME",length = 20, nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "marca")
    private List<Carro> carros;

    public Marca(){
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

    public void addCarro(Carro carro){
        this.carros.add(carro);
    }
}
