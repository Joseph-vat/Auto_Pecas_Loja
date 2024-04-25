package br.com.AutoPecasLoja.dao;

import br.com.AutoPecasLoja.model.Funcionario;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncionarioDao {

    DaoGenerico<Funcionario> daoGenerico = new DaoGenerico<Funcionario>();


    public void inserirFuncionario(Funcionario funcionario) {
        daoGenerico.transacaoCompleta(funcionario);
    }

    // Método para listar todos os funcionários do banco
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = null;
        try {
            String jpql = "SELECT f FROM Funcionario f ORDER BY f.id";
            TypedQuery<Funcionario> query = daoGenerico.getConexao().createQuery(jpql, Funcionario.class); //typed obriga o resultado da consulta ser do tipo informado; // Criar a consulta 	query.setMaxResults(5); //seta a quant de resultados

            funcionarios = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar funcionarios: " + e.getMessage());
        }
        return funcionarios;
    }

    // Método para atualizar um funcionário no banco
    public void atualizarFuncionario(Integer id, Funcionario novoFuncionario) {
        try {
            Funcionario funcionario = buscarFuncionarioPorId(id);

            if (funcionario != null) {
                funcionario.setCpf(novoFuncionario.getCpf());
                funcionario.setNome(novoFuncionario.getNome());
                funcionario.setCargo(novoFuncionario.getCargo());
                funcionario.setSenha(novoFuncionario.getSenha());

                daoGenerico.abrirTransacao();
                daoGenerico.getConexao().merge(funcionario);
                daoGenerico.commitTransacao();

            } else {
                System.out.println("Funcionário com o ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            daoGenerico.rollbackTransacao();
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }

    }

    // Método para deletar um funcionário do banco
    public void deletarFuncionario(Integer id) {
        try {
            Funcionario funcionarioExistente = buscarFuncionarioPorId(id);

            if (funcionarioExistente != null) {
                daoGenerico.abrirTransacao();
                daoGenerico.getConexao().remove(funcionarioExistente);
                daoGenerico.commitTransacao();
                System.out.println("Funcionário com o ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Funcionário com o ID " + id + " não encontrado. Não foi possível deletar.");
            }
        } catch (Exception e) {
            daoGenerico.rollbackTransacao();
            System.out.println("Erro ao deletar funcionário: " + e.getMessage());
        }
    }

    // Metodo que validará os dados de login do funcionario
    public int validarCpfSenha(String cpf, String senha) {
        String jpql = "SELECT f.id, f.senha FROM Funcionario f WHERE f.cpf = :cpf";
        try {
            TypedQuery<Funcionario> query = daoGenerico.getConexao().createQuery(jpql, Funcionario.class);
            query.setParameter("cpf", cpf);
            Funcionario funcionarioExiste = query.getSingleResult();

            if (funcionarioExiste != null) {
                String senhaBanco = funcionarioExiste.getSenha();
                if (senhaBanco.equals(senha)) {
                    return funcionarioExiste.getId(); // Retorna o ID se o CPF e a senha coincidirem
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return -1; // Retorna -1 se o CPF e a senha não forem validados -> será que essa é a melhor forma?
    }

    // Metodo para buscar um funcionario por ID
    public Funcionario buscarFuncionarioPorId(int id) {
        try {
            Funcionario funcionario = daoGenerico.getConexao().find(Funcionario.class, id);

            if(funcionario!=null){
                return funcionario;
            }
            else {
                System.out.println("Funcionário não encontrado no banco!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar funcionário por ID: " + e.getMessage());
        }
        return null;
    }
}
