package br.com.AutoPecasLoja.testes;

import br.com.AutoPecasLoja.dao.ClienteDao;
import br.com.AutoPecasLoja.dao.VeiculoDao;
import br.com.AutoPecasLoja.model.Cliente;
import br.com.AutoPecasLoja.model.Veiculo;

import java.util.List;

public class VeiculoTeste {

    public static void main(String[] args) {
        ClienteDao clienteDao = new ClienteDao();
        Cliente clienteUso = clienteDao.buscarClientePorId(2);

        VeiculoDao veiculoDao = new VeiculoDao();

        Veiculo veiculo1 = new Veiculo();
        Veiculo veiculo2 = new Veiculo();
        Veiculo veiculo3 = new Veiculo();
        Veiculo veiculo4 = new Veiculo();
        Veiculo veiculo5 = new Veiculo();
        Veiculo veiculo6 = new Veiculo();

        clienteUso = clienteDao.buscarClientePorId(3);
        veiculo1.setModelo("Gol");
        veiculo1.setMarca("Volkswagen");
        veiculo1.setAno(2019);
        veiculo1.setCliente(clienteUso);
        veiculoDao.inserirVeiculo(veiculo1);

        veiculo2.setModelo("Palio");
        veiculo2.setMarca("Fiat");
        veiculo2.setAno(2018);
        veiculo2.setCliente(clienteUso);
        veiculoDao.inserirVeiculo(veiculo2);


        clienteUso = clienteDao.buscarClientePorId(4);

        veiculo3.setModelo("Onix");
        veiculo3.setMarca("Chevrolet");
        veiculo3.setAno(2020);
        veiculo3.setCliente(clienteUso);
        veiculoDao.inserirVeiculo(veiculo3);

        veiculo4.setModelo("HB20");
        veiculo4.setMarca("Hyundai");
        veiculo4.setAno(2017);
        veiculo4.setCliente(clienteUso);
        veiculoDao.inserirVeiculo(veiculo4);


        clienteUso = clienteDao.buscarClientePorId(5);
        veiculo5.setModelo("Corolla");
        veiculo5.setMarca("Toyota");
        veiculo5.setAno(2022);
        veiculo5.setCliente(clienteUso);
        veiculoDao.inserirVeiculo(veiculo5);

        clienteUso = clienteDao.buscarClientePorId(6);
        veiculo6.setModelo("Civic");
        veiculo6.setMarca("Honda");
        veiculo6.setAno(2016);
        veiculo6.setCliente(clienteUso);
        veiculoDao.inserirVeiculo(veiculo6);

        List<Veiculo> veiculos = veiculoDao.listarTodosVeiculos();

//		Listar todos os veiculos
        for (Veiculo veiculoImprimir : veiculos) {
            Cliente cliente = veiculoImprimir.getCliente();
            System.out.println("ID: " + veiculoImprimir.getId() + ", Marca: " +
                    veiculoImprimir.getMarca() + ", Modelo: " + veiculoImprimir.getModelo() + ", Ano: " +
                    veiculoImprimir.getAno() + ", Id_Cliente: " + cliente.getId());
        }


        clienteUso = clienteDao.buscarClientePorId(3);
        veiculo6.setModelo("Classic");
        veiculo6.setMarca("Ford");
        veiculo6.setAno(2023);
        veiculo6.setCliente(clienteUso);

        veiculoDao.atualizarVeiculo(veiculo6, 8);

//      Listar todos os veiculos
        for (Veiculo veiculoImprimir : veiculos) {
            Cliente cliente = veiculoImprimir.getCliente();
            System.out.println("ID: " + veiculoImprimir.getId() + ", Marca: " +
                    veiculoImprimir.getMarca() + ", Modelo: " + veiculoImprimir.getModelo() + ", Ano: " +
                    veiculoImprimir.getAno() + ", Id_Cliente: " + cliente.getId());
        }

        veiculoDao.deletarVeiculo(8);

//      Listar todos os veiculos
        for (Veiculo veiculoImprimir : veiculos) {
            Cliente cliente = veiculoImprimir.getCliente();
            System.out.println("ID: " + veiculoImprimir.getId() + ", Marca: " +
                    veiculoImprimir.getMarca() + ", Modelo: " + veiculoImprimir.getModelo() + ", Ano: " +
                    veiculoImprimir.getAno() + ", Id_Cliente: " + cliente.getId());
        }
    }
}
