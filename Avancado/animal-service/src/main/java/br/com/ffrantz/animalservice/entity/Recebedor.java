package br.com.ffrantz.animalservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Recebedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "recebedor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Animal> animaisRecebidos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Animal> getAnimaisRecebidos() {
        return animaisRecebidos;
    }

    public void setAnimaisRecebidos(List<Animal> animaisRecebidos) {
        this.animaisRecebidos = animaisRecebidos;
    }

    public Integer getId() {
        return id;
    }
}
