import br.com.ffrantz.dao.IMarcaDAO;
import br.com.ffrantz.dao.MarcaDAO;
import br.com.ffrantz.entity.Marca;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MarcaTest {

    private IMarcaDAO marcaDAO;

    public MarcaTest(){
        this.marcaDAO = new MarcaDAO();
    }

    @Test
    public void cadastrar(){
        Marca marca = new Marca();
        marca.setNome("Hyundai");
        marca = marcaDAO.cadastrar(marca);

        assertNotNull(marca);
        assertNotNull(marca.getId());

    }
}
