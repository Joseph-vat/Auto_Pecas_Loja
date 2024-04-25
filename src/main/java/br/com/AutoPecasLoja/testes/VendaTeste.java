package br.com.AutoPecasLoja.testes;

import br.com.AutoPecasLoja.dao.*;
import br.com.AutoPecasLoja.model.Cliente;
import br.com.AutoPecasLoja.model.Funcionario;
import br.com.AutoPecasLoja.model.Veiculo;
import br.com.AutoPecasLoja.model.Venda;

import java.util.Date;
import java.util.List;

public class VendaTeste {
    public static void main(String[] args) {

        ClienteDao clienteDao = new ClienteDao();
        VeiculoDao veiculoDao = new VeiculoDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        VendaDao vendaDao = new VendaDao();

        Cliente clienteUso = clienteDao.buscarClientePorId(2);
        Funcionario funcionarioUso = funcionarioDao.buscarFuncionarioPorId(8);
        Venda venda1 = new Venda();

        DaoGenerico <VendaDao> daoGenerico = new DaoGenerico<>();

        // inserir venda
        venda1.setNome("lala");
        venda1.setValor_Total(2.23);
        venda1.setFuncionario(funcionarioUso);
        venda1.setCliente(clienteUso);
        venda1.setData(new Date());
        vendaDao.inserirVenda(venda1);

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

        clienteUso = clienteDao.buscarClientePorId(3);
        funcionarioUso = funcionarioDao.buscarFuncionarioPorId(2);
//
//        // atualizar venda
        venda1.setNome("lulu");
        venda1.setValor_Total(3.32);
        venda1.setFuncionario(funcionarioUso);
        venda1.setCliente(clienteUso);
        venda1.setData(new Date());
        vendaDao.atualizarVenda(1, venda1);

        vendaDao.deletarVenda(1);



    }
}
