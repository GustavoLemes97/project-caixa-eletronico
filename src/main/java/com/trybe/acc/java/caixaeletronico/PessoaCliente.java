package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Objects;

public class PessoaCliente {
  private final String nomeCompleto;
  private final String cpf;
  private final String senha;
  private ArrayList<Conta> contas = new ArrayList<>();

  public PessoaCliente(String nomeCompleto, String cpf, String senha) {
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.senha = senha;

    System.out.println("Nova pessoa cliente " + nomeCompleto + " com CPF: " + cpf + " criada!");
  }

  public void adicionarConta(Conta novaConta) {
    this.contas.add(novaConta);
  }

  public int retornaNumeroDeContas() {
    return this.contas.size();
  }

  public double retornarSaldoContaEspecifica(int conta) {
    return this.contas.get(conta).retornarSaldo();
  }

  public String retornarIdContaEspecifica(int conta) {
    return this.contas.get(conta).getIdConta();
  }

  public void retornarExtratoContaEspecifica(int conta) {
    this.contas.get(conta).retornarExtrato();
  }

  public void adicionarTransacaoContaEspecifica(int conta, double quantia, String descricao) {
    this.contas.get(conta).adicionarTransacao(quantia, descricao);
  }

  public void retornarResumoContas() {
    this.contas
            .stream()
            .map(Conta::retornarResumoConta);
  }
  public String getCpf() {
    return this.cpf;
  }

  public boolean validarSenha(String senha) {
    return this.senha.equals(senha);
  }
}
