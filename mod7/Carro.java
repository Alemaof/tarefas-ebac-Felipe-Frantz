/**
 * @author Felipe Frantz
 */
public class Carro {

    // Atributos da classe
    private String cor;
    private String marca;
    private String modelo;
    private int ano;
    private float velocidade = 0;

    //Construtor: método para instanciar objetos da classe. Define os atributos necessários para criar um novo objeto
    public Carro(String cor, String marca, String modelo, int ano) {
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    // Getter and Setters
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    /*
     Métodos para movimentar o carro
    */
    public void acelerar (float velocidade) {
        if (velocidade >= 0) {
            this.velocidade += velocidade;
        }
        else {
            System.out.println("Velocidade inválida");
        }
    }

    public void parar() {
        this.velocidade = 0;
    }

    public void reduzir (float velocidade) {
        if (this. velocidade > velocidade) {
            this.velocidade -= velocidade;
        }
        else {
            this.parar();
        }


    }

}