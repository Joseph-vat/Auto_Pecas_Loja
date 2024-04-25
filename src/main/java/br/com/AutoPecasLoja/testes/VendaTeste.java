package br.com.AutoPecasLoja.testes;

import br.com.AutoPecasLoja.dao.*;
import br.com.AutoPecasLoja.model.Cliente;
import br.com.AutoPecasLoja.model.Funcionario;
import br.com.AutoPecasLoja.model.Venda;

import java.util.Date;
import java.util.List;

public class VendaTeste {
    public static void main(String[] args) {

        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        VendaDao vendaDao = new VendaDao();

        // inserir venda
        Cliente clienteUso = clienteDao.buscarClientePorId(3);
        Funcionario funcionarioUso = funcionarioDao.buscarFuncionarioPorId(8);
        Venda venda1 = new Venda();
        venda1.setNome("Venda 1");
        venda1.setFuncionario(funcionarioUso);
        venda1.setCliente(clienteUso);
        venda1.setData(new Date());
        vendaDao.inserirVenda(venda1);

        clienteUso = clienteDao.buscarClientePorId(2);
        funcionarioUso = funcionarioDao.buscarFuncionarioPorId(8);
        Venda venda2 = new Venda();
        venda2.setNome("Venda 2");
        venda2.setFuncionario(funcionarioUso);
        venda2.setCliente(clienteUso);
        venda2.setData(new Date());
        vendaDao.inserirVenda(venda2);

        clienteUso = clienteDao.buscarClientePorId(3);
        funcionarioUso = funcionarioDao.buscarFuncionarioPorId(2);
        Venda venda3 = new Venda();
        venda3.setNome("Venda 3");
        venda3.setData(new Date());
        venda3.setCliente(clienteUso); // Supondo que cliente3 já foi criado em um teste anterior
        venda3.setFuncionario(funcionarioUso); // Supondo que funcionario1 já foi criado em um teste anterior
        vendaDao.inserirVenda(venda3);

        clienteUso = clienteDao.buscarClientePorId(4);
        funcionarioUso = funcionarioDao.buscarFuncionarioPorId(8);
        Venda venda4 = new Venda();
        venda4.setNome("Venda 4");
        venda4.setData(new Date());
        venda4.setCliente(clienteUso); // Supondo que cliente4 já foi criado em um teste anterior
        venda4.setFuncionario(funcionarioUso); // Supondo que funcionario2 já foi criado em um teste anterior
        vendaDao.inserirVenda(venda4);

        clienteUso = clienteDao.buscarClientePorId(5);
        funcionarioUso = funcionarioDao.buscarFuncionarioPorId(2);
        Venda venda5 = new Venda();
        venda5.setNome("Venda 5");
        venda5.setData(new Date());
        venda5.setCliente(clienteUso); // Supondo que cliente5 já foi criado em um teste anterior
        venda5.setFuncionario(funcionarioUso); // Supondo que funcionario3 já foi criado em um teste anterior
        vendaDao.inserirVenda(venda5);

        clienteUso = clienteDao.buscarClientePorId(6);
        funcionarioUso = funcionarioDao.buscarFuncionarioPorId(2);
        Venda venda6 = new Venda();
        venda6.setNome("Venda 6");
        venda6.setData(new Date());
        venda6.setCliente(clienteUso); // Supondo que cliente1 já foi criado em um teste anterior
        venda6.setFuncionario(funcionarioUso); // Supondo que funcionario4 já foi criado em um teste anterior
        vendaDao.inserirVenda(venda6);

        List<Venda> vendas = vendaDao.listarVendas();

//		Listar todos os veiculos
        for (Venda vendaImprimir : vendas) {
            Cliente cliente = vendaImprimir.getCliente();
            Funcionario funcionario = vendaImprimir.getFuncionario();
            System.out.println("ID: " + vendaImprimir.getId() + "Nome: " + vendaImprimir.getNome() +
                    ", Data: " +
                    vendaImprimir.getData() + ", Valor-Total: " + vendaImprimir.getValor_Total() + ", cliente: " +
                    cliente.getId() + ", Funcionario: " + funcionario.getId());
        }

        clienteUso = clienteDao.buscarClientePorId(2);
        funcionarioUso = funcionarioDao.buscarFuncionarioPorId(2);

        // atualizar venda
        venda1.setNome("Venda Nova");
        venda1.setValor_Total(3.32);
        venda1.setFuncionario(funcionarioUso);
        venda1.setCliente(clienteUso);
        venda1.setData(new Date());
        vendaDao.atualizarVenda(1, venda1);

        vendaDao.deletarVenda(1);

    }
}
