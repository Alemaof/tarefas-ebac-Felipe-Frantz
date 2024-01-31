import br.com.ffrantz.dao.*;
import br.com.ffrantz.entity.Acessorio;
import br.com.ffrantz.entity.Carro;
import br.com.ffrantz.entity.Marca;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CarroTest {

    private ICarroDAO carroDAO;

    private IMarcaDAO marcaDAO;

    private IAcessorioDAO acessorioDAO;

    public CarroTest(){
        this.carroDAO = new CarroDAO();
        this.marcaDAO = new MarcaDAO();
        this.acessorioDAO = new AcessorioDAO();
    }

    @Test
    public void cadastrar(){
        Marca marca = criarMarca();
        Acessorio acessorio = cirarAcessorio("Alarme");
        Carro carro = new Carro();
        carro.setNome("HB20");
        carro.setMarca(marca);
        carro.setAno(2023);
        carro.addAcessorio(acessorio);
        carro = carroDAO.cadastrar(carro);

        assertNotNull(carro);
        assertNotNull(carro.getId());
    }

    private Marca criarMarca() {
        Marca marca = new Marca();
        marca.setNome("Hyundai");
        return marcaDAO.cadastrar(marca);
    }

    private Acessorio cirarAcessorio(String nome) {
        Acessorio acessorio = new Acessorio();
        acessorio.setNome(nome);
        return acessorio;
    }
}
