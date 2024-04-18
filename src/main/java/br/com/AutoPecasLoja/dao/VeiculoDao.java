package br.com.AutoPecasLoja.dao;

import br.com.AutoPecasLoja.model.Cliente;
import br.com.AutoPecasLoja.model.Veiculo;

import javax.persistence.TypedQuery;
import java.util.List;

public class VeiculoDao {
    DaoGenerico<Veiculo> daoGenerico = new DaoGenerico<Veiculo>();

    public void inserirVeiculo(Veiculo veiculo) {
        daoGenerico.transacaoCompleta(veiculo);
    }

    public List<Veiculo> listarTodosVeiculos() {
        List<Veiculo> veiculos = null;
        try {
            TypedQuery<Veiculo> query = daoGenerico.getConexao()
                    .createQuery("SELECT v FROM Veiculo v ORDER BY v.id", Veiculo.class);
            veiculos = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar veiculos: " + e.getMessage());
        }
        return veiculos;
    }

    public void atualizarVeiculo(Veiculo novoVeiculo, int idVeiculo) {
        try {
            Veiculo veiculoExistente = daoGenerico.getConexao().find(Veiculo.class, idVeiculo);

            if (veiculoExistente != null) {
                veiculoExistente.setModelo(novoVeiculo.getModelo());
                veiculoExistente.setMarca(novoVeiculo.getMarca());
                veiculoExistente.setAno(novoVeiculo.getAno());
                veiculoExistente.setCliente(novoVeiculo.getCliente());

                daoGenerico.abrirTransacao();
                daoGenerico.getConexao().merge(veiculoExistente);
                daoGenerico.commitTransacao();
            } else {
                System.out.println("Veículo com o ID " + idVeiculo + " não encontrado. Não foi possível atualizar.");
            }
        } catch (Exception e) {
            daoGenerico.rollbackTransacao();
            System.out.println("Erro ao atualizar veículo: " + e.getMessage());
        }
    }

    public void deletarVeiculo(int id) {
        Veiculo veiculo = daoGenerico.getConexao().find(Veiculo.class, id);
        if (veiculo != null) {
            daoGenerico.abrirTransacao();
            daoGenerico.getConexao().remove(veiculo);
            daoGenerico.commitTransacao();
        } else {
            System.out.println("Veículo com o ID " + id + " não encontrado. Não foi possível deletar.");
        }
    }

    public Veiculo buscarVeiculoPorId(int id) {
        return daoGenerico.getConexao().find(Veiculo.class, id);
    }

    public List<Veiculo> listarVeiculosPorCliente(Cliente cliente) {
        TypedQuery<Veiculo> query = daoGenerico.getConexao()
                .createQuery("SELECT v FROM Veiculo v WHERE v.cliente = :cliente", Veiculo.class);
        query.setParameter("cliente", cliente);
        return query.getResultList();
    }
}
