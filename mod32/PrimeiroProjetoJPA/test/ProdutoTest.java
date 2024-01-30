import br.com.ffrantz.dao.IProdutoDAO;
import br.com.ffrantz.dao.ProdutoDAO;
import br.com.ffrantz.entity.Produto;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ProdutoTest {

    IProdutoDAO produtoDAO;

    public ProdutoTest() {
        produtoDAO = new ProdutoDAO();
    }
    @Test
    public void cadastrarTest() {
        Produto produto = new Produto();
        produto.setCodigo("P7");
        produto.setNome("TV SAMSUNG");
        produto.setDescricao("Descricao produto");
        produto = produtoDAO.cadastrar(produto);

        assertNotNull(produto);
        assertNotNull(produto.getId());

    }
}
