package br.com.AutoPecasLoja.testes;

import br.com.AutoPecasLoja.dao.ClienteDao;
import br.com.AutoPecasLoja.model.Cliente;

import java.util.List;

public class ClienteTeste {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();
        Cliente cliente4 = new Cliente();
        Cliente cliente5 = new Cliente();
        Cliente cliente6 = new Cliente();
        Cliente cliente7 = new Cliente();

        ClienteDao clienteDao = new ClienteDao();

//		Criando novos clientes
        cliente1.setCpf("965.778.894-04");
        cliente1.setNome("Rosa");
        clienteDao.inserirCliente(cliente1);

        cliente2.setCpf("123.456.789-01");
        cliente2.setNome("João");
        clienteDao.inserirCliente(cliente2);

        cliente3.setCpf("987.654.321-09");
        cliente3.setNome("Maria");
        clienteDao.inserirCliente(cliente3);

        cliente4.setCpf("456.789.123-45");
        cliente4.setNome("Carlos");
        clienteDao.inserirCliente(cliente4);

        cliente5.setCpf("321.654.987-78");
        cliente5.setNome("Ana");
        clienteDao.inserirCliente(cliente5);

        cliente6.setCpf("789.123.456-02");
        cliente6.setNome("Paulo");
        clienteDao.inserirCliente(cliente6);

        cliente7.setCpf("654.321.987-87");
        cliente7.setNome("Laura");
        clienteDao.inserirCliente(cliente7);
//
        List<Cliente> clientes = clienteDao.listarTodosClientes();

//		Listar todos os clientes
        for (Cliente clienteImprimir : clientes) {
            System.out.println("ID: " + clienteImprimir.getId() + ", Nome: " + clienteImprimir.getNome() + ", CPF: " + clienteImprimir.getCpf());
        }
//
// 		Atualizar
        cliente2.setNome("Judite");
        cliente2.setCpf("632.822.546.33");
        clienteDao.atualizarCliente(2,cliente2);

        //Listar todos os clientes
        for (Cliente clienteImprimir : clientes) {
            System.out.println("ID: " + clienteImprimir.getId() + ", Nome: " + clienteImprimir.getNome() + ", CPF: " + clienteImprimir.getCpf());
        }

        // Deletar cliente com o ID 1
        clienteDao.deletarCliente(1);

        for (Cliente clienteImprimir : clientes) {
            System.out.println("ID: " + clienteImprimir.getId() + ", Nome: " + clienteImprimir.getNome() + ", CPF: " + clienteImprimir.getCpf());
        }
    }
}
