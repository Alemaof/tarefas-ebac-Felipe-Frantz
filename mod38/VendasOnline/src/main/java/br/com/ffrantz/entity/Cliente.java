package br.com.ffrantz.entity;

import javax.persistence.*;

@Entity
@Table(name = "TB_CLIENTE")
@NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome") 
public class Cliente implements Persistente {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="cliente_seq")
    @SequenceGenerator(name="cliente_seq", sequenceName="sq_cliente", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NOME", length = 50, nullable = false)
    private String nome;

    @Column(name = "CPF", nullable = false, unique = true)
    private Long cpf;

    @Column(name = "TELEFONE", length = 50, nullable = false)
    private Long telefone;

    @Column(name = "ENDERECO", length = 100, nullable = false)
    private String endereco;

    @Column(name = "CIDADE", length = 50, nullable = false)
    private String cidade;

    @Column(name = "ESTADO", length = 20, nullable = false)
    private String estado;

    @Column(name = "IDADE", nullable = false)
    private Integer idade;

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

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
