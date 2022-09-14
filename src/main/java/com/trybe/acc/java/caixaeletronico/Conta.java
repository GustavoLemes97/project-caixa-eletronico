package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;

/**
 * Classe que representa as contas do banco, onde temos os
 * métodos relacionados às transações, e mostra as informações de saldo.
 */
public class Conta {
  private final String tipoConta;
  private final String idConta;
  private final PessoaCliente pessoaCliente;
  private final ArrayList<Transacao> transacoes = new ArrayList<>();

  /**
   * Método construtor que cria contas.
   */
  public Conta(String tipoConta, PessoaCliente pessoaCliente, Banco banco) {
    this.tipoConta = tipoConta;
    this.idConta = banco.gerarNumeroNovaConta();
    this.pessoaCliente = pessoaCliente;
  }

  /**
   * Método que cria as transações.
   */
  public void adicionarTransacao(double quantia, String descricao) {
    Transacao novaTransacao = new Transacao(quantia, descricao);

    this.transacoes.add(novaTransacao);
  }

  /**
   * Método que retorna o saldo da conta.
   */
  public double retornarSaldo() {
    return this.transacoes
            .stream()
            .map(Transacao::getQuantia)
            .reduce(0.0, Double::sum);
  }

  /**
   * Método que gera resumo da conta.
   */
  public String retornarResumoConta() {
    return "Número da conta: " + this.idConta
            + " Saldo: " + retornarSaldo()
            + " Tipo da conta: " + this.tipoConta;
  }

  /**
   * Método que gera o extrato da conta.
   */
  public void retornarExtrato() {
    this.transacoes
            .stream()
            .map(Transacao::retornarResumoTransacao)
            .forEach(System.out::println);
  }

  public String getIdConta() {
    return idConta;
  }

  public PessoaCliente getPessoaCliente() {
    return pessoaCliente;
  }
}
