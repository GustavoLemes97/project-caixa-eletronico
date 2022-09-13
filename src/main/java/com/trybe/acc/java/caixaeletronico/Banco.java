package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Random;

public class Banco {

  private ArrayList<Conta> contas = new ArrayList<>();
  private ArrayList<PessoaCliente> pessoasClientes = new ArrayList<>();

  public String gerarNumeroNovaConta() {
    Random random = new Random();

    return String.valueOf(random.nextInt((99999 - 10000) + 1))
            + random.nextInt((99999 - 10000) + 1);
  }

  public PessoaCliente adicionarPessoaCliente(String nome, String cpf, String senha) {
    PessoaCliente pessoaCliente = new PessoaCliente(nome, cpf, senha);
    this.pessoasClientes.add(pessoaCliente);

    return pessoaCliente;
  }

  public void adicionarConta(Conta novaConta) {
    this.contas.add(novaConta);
  }

  public PessoaCliente pessoaClienteLogin(String cpf, String senha) {
    return this.pessoasClientes
            .stream()
            .filter(cliente -> cliente.getCpf().equals(cpf) && cliente.validarSenha(senha))
            .findAny().orElse(null);
  }

  public void transferirFundos(PessoaCliente pessoaCliente, int contaOrigem, int contaDestino, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(contaOrigem, quantia, "Transferência realizada.");
    pessoaCliente.adicionarTransacaoContaEspecifica(contaDestino, quantia, "Transferência recebida.");
  }

  public void sacar(PessoaCliente pessoaCliente, int contaOrigem, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(contaOrigem, quantia, "Saque Efetuado");
  }

  public void depositar(PessoaCliente pessoaCliente, int contaDestino, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(contaDestino, quantia, "Depósito recebido");
  }

  public void mostrarExtrato(PessoaCliente pessoaCliente, int conta) {
    pessoaCliente.retornarExtratoContaEspecifica(conta);
  }
}