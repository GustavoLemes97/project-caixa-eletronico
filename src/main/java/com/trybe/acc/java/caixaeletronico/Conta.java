package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;

public class Conta {
  private String tipoConta;
  private String idConta;
  private PessoaCliente pessoaCliente;
  private ArrayList<Transacao> transacoes = new ArrayList<>();

  public Conta(String tipoConta, PessoaCliente pessoaCliente, Banco banco) {
    this.tipoConta = tipoConta;
    this.idConta = banco.gerarNumeroNovaConta();
    this.pessoaCliente = pessoaCliente;
  }

  public void adicionarTransacao(double quantia, String descricao) {
    Transacao novaTransacao = new Transacao(quantia, descricao);

    this.transacoes.add(novaTransacao);
  }

  public double retornarSaldo() {
    return this.transacoes
            .stream()
            .map( transacao -> new Double(transacao.getQuantia()))
            .reduce(0, Double::sum);
  }

  public String retornarResumoConta() {
    return "Número da conta: " + this.idConta
            + " Saldo: " + retornarSaldo()
            + " Tipo da conta: " + this.tipoConta;
  }

  public void retornarExtrato() {
    return this.transacoes
            .stream()
            .map(Transacao::retornarResumoTransacao);
  }

  public String getIdConta() {
    return idConta;
  }

  public PessoaCliente getPessoaCliente() {
    return pessoaCliente;
  }
}
