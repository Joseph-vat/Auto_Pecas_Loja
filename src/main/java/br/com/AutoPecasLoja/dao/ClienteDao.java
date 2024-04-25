package br.com.AutoPecasLoja.dao;

import br.com.AutoPecasLoja.model.Cliente;

import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDao {
    DaoGenerico<Cliente> daoGenerico = new DaoGenerico<Cliente>();

    public void inserirCliente(Cliente cliente) {
        daoGenerico.transacaoCompleta(cliente);
    }

    public List<Cliente> listarTodosClientes() {
        List<Cliente> clientes = null;
        try {
            TypedQuery<Cliente> query = daoGenerico.getConexao()
                    .createQuery("SELECT c FROM Cliente c ORDER BY c.id", Cliente.class);
            clientes = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public void atualizarCliente(int id, Cliente novoCliente) {
        try {
            // Verifica se o cliente com o ID fornecido existe no banco de dados
            Cliente clienteExistente = buscarClientePorId(id);

            // Se o cliente existir, atualiza os seus dados com os do novo cliente
            if (clienteExistente != null) {
                clienteExistente.setNome(novoCliente.getNome());
                clienteExistente.setCpf(novoCliente.getCpf());

                daoGenerico.abrirTransacao();
                daoGenerico.getConexao().merge(clienteExistente);
                daoGenerico.commitTransacao();
            } else {
                System.out.println("Cliente com o ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            daoGenerico.rollbackTransacao();
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void deletarCliente(int id) {
        try {
            // Verifica se o cliente com o ID fornecido existe no banco de dados
            Cliente clienteExistente = buscarClientePorId(id);

            // Se o cliente existir, deleta-o do banco de dados
            if (clienteExistente != null) {
                daoGenerico.abrirTransacao();
                daoGenerico.getConexao().remove(clienteExistente);
                daoGenerico.commitTransacao();
                System.out.println("Cliente com o ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Cliente com o ID " + id + " não encontrado. Não foi possível deletar.");
            }
        } catch (Exception e) {
            daoGenerico.rollbackTransacao();
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }


    public Cliente buscarClientePorId(int id) {
        try {
            // Verifica se o cliente com o ID fornecido existe no banco de dados
            Cliente cliente = daoGenerico.getConexao().find(Cliente.class, id);

            if(cliente!=null){
                return cliente;
            }
            else {
                System.out.println("Cliente não encontrado no banco!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente por ID: " + e.getMessage());
        }
        return null;
    }

}
