package br.com.AutoPecasLoja.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoGenerico <E> {
    private static EntityManagerFactory emf;
    private EntityManager conexao;
    private Class<E> classe;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("auto-pecas");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public DaoGenerico() {
        this(null);
    }

    public DaoGenerico(Class<E> classeUsada) {
        this.classe = classeUsada;
        conexao = emf.createEntityManager();
    }


    public EntityManager getConexao() {
        return conexao;
    }

    public DaoGenerico<E> abrirTransacao(){
        conexao.getTransaction().begin();
        return this;
    }

    public DaoGenerico<E> commitTransacao(){
        conexao.getTransaction().commit();
        return this;
    }

    public DaoGenerico<E> persistirTransacao(E entidade){
        conexao.persist(entidade);
        return this;
    }

    public DaoGenerico<E> transacaoCompleta(E entidade){
        abrirTransacao();
        try {
            persistirTransacao(entidade);
            commitTransacao();
        } catch (Exception e) {
            rollbackTransacao();
            System.out.println("Erro ao executar transação: " + e.getMessage());
        }
        return this;
    }


    public void fechar() {
        conexao.close();
    }

    public DaoGenerico<E> rollbackTransacao() {
        try {
            conexao.getTransaction().rollback();
        } catch (Exception e) {
            System.out.println("Erro ao fazer rollback da transação: " + e.getMessage());
        }
        return this;
    }
}
