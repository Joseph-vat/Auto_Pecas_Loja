package br.com.AutoPecasLoja.testes;

import br.com.AutoPecasLoja.dao.FuncionarioDao;
import br.com.AutoPecasLoja.model.Funcionario;

import java.util.List;

public class TesteFuncionario {
    public static void main(String[] args) {
        Funcionario funcionario1 = new Funcionario();
        Funcionario funcionario2 = new Funcionario();

        FuncionarioDao funcionarioDao = new FuncionarioDao();

        // Criando novos funcionários
        funcionario1.setCpf("123.456.789-01");
        funcionario1.setNome("Fulano");
        funcionario1.setCargo("Gerente");
        funcionario1.setSenha("senha123");
        funcionarioDao.inserirFuncionario(funcionario1);

        funcionario2.setCpf("987.654.321-09");
        funcionario2.setNome("Ciclano");
        funcionario2.setCargo("Analista");
        funcionario2.setSenha("senha456");
        funcionarioDao.inserirFuncionario(funcionario2);

        // Listar todos os funcionários
        List<Funcionario> funcionarios = funcionarioDao.listarFuncionarios();
        for (Funcionario funcionario : funcionarios) {
            System.out.println("ID: " + funcionario.getId() + ", Nome: " + funcionario.getNome() +
                    ", CPF: " + funcionario.getCpf() + ", Cargo: " + funcionario.getCargo());
        }

        // Atualizar funcionário
        funcionario2.setNome("Beltrano");
        funcionario2.setCpf("111.222.333-44");
        funcionario2.setCargo("Desenvolvedor");
        funcionario2.setSenha("novaSenha");
        funcionarioDao.atualizarFuncionario(funcionario2.getId(), funcionario2);

        // Listar todos os funcionários novamente após atualização
        funcionarios = funcionarioDao.listarFuncionarios();
        for (Funcionario funcionario : funcionarios) {
            System.out.println("ID: " + funcionario.getId() + ", Nome: " + funcionario.getNome() +
                    ", CPF: " + funcionario.getCpf() + ", Cargo: " + funcionario.getCargo());
        }

        // Deletar funcionário com o ID 1
        funcionarioDao.deletarFuncionario(1);

        // Listar todos os funcionários após exclusão
        funcionarios = funcionarioDao.listarFuncionarios();
        for (Funcionario funcionario : funcionarios) {
            System.out.println("ID: " + funcionario.getId() + ", Nome: " + funcionario.getNome() +
                    ", CPF: " + funcionario.getCpf() + ", Cargo: " + funcionario.getCargo());
        }
    }
}
