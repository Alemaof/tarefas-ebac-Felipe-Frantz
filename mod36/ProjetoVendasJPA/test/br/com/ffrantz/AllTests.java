package br.com.ffrantz;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClienteTestPostgre.class, ProdutoTestPostgre.class, VendaTestPostgre.class, ClienteTestMysql.class, ProdutoTestMysql.class, VendaTestMysql.class})
public class AllTests {
}
