package br.com.ffrantz;

import br.com.ffrantz.domain.Venda;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClienteTest.class, ProdutoTest.class, VendaTest.class})
public class AllTests {
}
