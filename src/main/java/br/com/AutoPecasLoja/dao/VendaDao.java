package br.com.AutoPecasLoja.dao;

import br.com.AutoPecasLoja.model.Venda;

import javax.persistence.TypedQuery;
import java.util.List;

public class VendaDao {

    DaoGenerico<Venda> daoGenerico = new DaoGenerico<Venda>();
    public void inserirVenda(Venda venda) {
        daoGenerico.transacaoCompleta(venda);
        daoGenerico.fechar();
    }

    // Método para listar todas as vendas do banco
    public List<Venda> listarVendas() {
        List<Venda> vendas = null;
        try {
            TypedQuery<Venda> query = daoGenerico.getConexao()
                    .createQuery("SELECT v FROM Venda v ORDER BY v.id", Venda.class);
            vendas = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }
        return vendas;
    }


    // Método para atualizar uma venda no banco
    public void atualizarVenda(Integer id, Venda novaVenda) {
        try {
            Venda vendaExistente = buscarVendaPorId(id);

            if (vendaExistente != null) {
                vendaExistente.setNome(novaVenda.getNome());
                vendaExistente.setCliente(novaVenda.getCliente());
                vendaExistente.setData(new java.sql.Date(novaVenda.getData().getTime()));
                vendaExistente.setFuncionario(novaVenda.getFuncionario());
                vendaExistente.setValor_Total(novaVenda.getValor_Total());

                daoGenerico.abrirTransacao();
                daoGenerico.getConexao().merge(vendaExistente);
                daoGenerico.commitTransacao();
            } else {
                System.out.println("Venda com o ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            daoGenerico.rollbackTransacao();
            System.out.println("Erro ao atualizar venda: " + e.getMessage());
        }
    }

    // Método para deletar uma venda do banco
    public void deletarVenda(Integer id) {
        try {
            Venda vendaExistente = buscarVendaPorId(id);

            if (vendaExistente != null) {
                daoGenerico.abrirTransacao();
                daoGenerico.getConexao().remove(vendaExistente);
                daoGenerico.commitTransacao();
                System.out.println("Venda com o ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Venda com o ID " + id + " não encontrado. Não foi possível deletar.");
            }
        } catch (Exception e) {
            daoGenerico.rollbackTransacao();
            System.out.println("Erro ao deletar venda: " + e.getMessage());
        }
    }

    // Método para buscar uma venda por ID
    public Venda buscarVendaPorId(int id) {
        try {
            return daoGenerico.getConexao().find(Venda.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar venda por ID: " + e.getMessage());
            return null;
        }
    }

    public void somarValorTotalVendaServico (Venda venda, Double precoServico) {
        Double valorTotal = venda.getValor_Total() + precoServico;

        Venda vendaExistente = buscarVendaPorId(venda.getId());

        try {
            vendaExistente.setValor_Total(valorTotal);

            daoGenerico.abrirTransacao();
            daoGenerico.getConexao().merge(vendaExistente);
            daoGenerico.fechar();

            System.out.println("Valor total atualizado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar valor: " + e.getMessage());
        }
    }

    public void somarValorTotalVendaPeca (Venda venda, Double precoPeca) {
        Double valorTotal = venda.getValor_Total() + precoPeca;

        Venda vendaExistente = buscarVendaPorId(venda.getId());

        try {
            vendaExistente.setValor_Total(valorTotal);

            daoGenerico.abrirTransacao();
            daoGenerico.getConexao().merge(vendaExistente);
            daoGenerico.fechar();

            System.out.println("Valor total atualizado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar valor: " + e.getMessage());
        }
    }

    public void subtrairValorTotalVendaServico (Venda venda, Double precoServico) {
        Double valorTotal = venda.getValor_Total() - precoServico;

        Venda vendaExistente = buscarVendaPorId(venda.getId());

        try {
            vendaExistente.setValor_Total(valorTotal);

            daoGenerico.abrirTransacao();
            daoGenerico.getConexao().merge(vendaExistente);
            daoGenerico.fechar();

            System.out.println("Valor total atualizado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar valor: " + e.getMessage());
        }
    }

    public void subtrairValorTotalVendaPeca (Venda venda, Double precoPeca) {
        Double valorTotal = venda.getValor_Total() - precoPeca;

        Venda vendaExistente = buscarVendaPorId(venda.getId());

        try {
            vendaExistente.setValor_Total(valorTotal);

            daoGenerico.abrirTransacao();
            daoGenerico.getConexao().merge(vendaExistente);
            daoGenerico.fechar();

            System.out.println("Valor total atualizado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar valor: " + e.getMessage());
        }
    }

}
