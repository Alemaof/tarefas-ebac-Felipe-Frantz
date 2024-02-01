package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.entity.Cliente;
import br.com.ffrantz.entity.Produto;
import br.com.ffrantz.entity.ProdutoVendido;
import br.com.ffrantz.entity.Venda;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;

public class VendaDAO extends GenericDAO<Venda, Long> implements IVendaDAO {

    public VendaDAO() {
        super(Venda.class);
    }

    @Override
    public Venda cadastrar(Venda venda) {
        try {
            openConnection();
            venda.getProdutoVendido().forEach(produtoVendido -> {
                Produto prod = entityManager.merge(produtoVendido.getProduto());
                produtoVendido.setProduto(prod);
            });
            Cliente cliente = entityManager.merge(venda.getCliente());
            venda.setCliente(cliente);
            entityManager.persist(venda);
            entityManager.getTransaction().commit();
            closeConnection();
            return venda;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Venda consultarComCollection(Long id) {
        openConnection();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Venda> query = builder.createQuery(Venda.class);
        Root<Venda> root = query.from(Venda.class);
        root.fetch("cliente");
        root.fetch("produtoVendido");
        query.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Venda> tpQuery =
                entityManager.createQuery(query);
        Venda venda = tpQuery.getSingleResult();
        closeConnection();
        return venda;
    }

    @Override
    public void recalcularValorTotalVenda(Venda venda) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ProdutoVendido prod : venda.getProdutoVendido()) {
            valorTotal = valorTotal.add(prod.getValorTotal());
        }
        venda.setValorTotal(valorTotal);
    }
}
