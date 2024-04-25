package br.com.ffrantz.animalservice.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    @Column(name = "nome_provisorio", nullable = false)
    private String nomeProvisorio;

    @Column(name = "idade_estimada", nullable = false)
    private Integer idadeEstimada;

    @Column(name = "raca", nullable = false)
    private String raca;

    @Column(name = "especie", nullable = false)
    private String especie;

    @Column(name = "data_entrada", nullable = false)
    private Date dataEntrada;

    @Column(name = "data_adocao")
    private Date dataAdocao;

    @Column(name = "condicoes_chegada", nullable = false)
    private String condicoesChegada;

    @ManyToOne
    @JoinColumn(name = "recebedor_id", nullable = false)
    private Recebedor recebedor;

    @Column(name = "data_obito")
    private Date dataObito;

    @Column(name = "porte", nullable = false)
    private String porte;

    public String getNomeProvisorio() {
        return nomeProvisorio;
    }

    public void setNomeProvisorio(String nomeProvisorio) {
        this.nomeProvisorio = nomeProvisorio;
    }

    public Integer getIdadeEstimada() {
        return idadeEstimada;
    }

    public void setIdadeEstimada(Integer idadeEstimada) {
        this.idadeEstimada = idadeEstimada;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(Date dataAdocao) {
        this.dataAdocao = dataAdocao;
    }

    public String getCondicoesChegada() {
        return condicoesChegada;
    }

    public void setCondicoesChegada(String condicoesChegada) {
        this.condicoesChegada = condicoesChegada;
    }

    public Recebedor getNomeRecebedor() {
        return recebedor;
    }

    public void setNomeRecebedor(Recebedor nomeRecebedor) {
        this.recebedor = nomeRecebedor;
    }

    public Date getDataObito() {
        return dataObito;
    }

    public void setDataObito(Date dataObito) {
        this.dataObito = dataObito;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}
