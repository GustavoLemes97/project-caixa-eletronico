package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe é a responsável por criar novas pessoas clientes no banco
 * e também verificar e validar o login da pessoa cliente no caixa eletrônico.
 */
public class Banco {

  private final ArrayList<Conta> contas = new ArrayList<>();
  private final ArrayList<PessoaCliente> pessoasClientes = new ArrayList<>();

  /**
   * Método que gera o número da conta.
   */
  public String gerarNumeroNovaConta() {
    Random random = new Random();

    return String.valueOf(random.nextInt(89999) + 10000)
            .concat(String.valueOf(random.nextInt(89999) + 10000));
  }

  /**
   * Método que adiciona o cliente.
   */
  public PessoaCliente adicionarPessoaCliente(String nome, String cpf, String senha) {
    PessoaCliente pessoaCliente = new PessoaCliente(nome, cpf, senha);
    this.pessoasClientes.add(pessoaCliente);

    return pessoaCliente;
  }

  public void adicionarConta(Conta novaConta) {
    this.contas.add(novaConta);
  }

  /**
   * Método que verificar o login do cliente.
   */
  public PessoaCliente pessoaClienteLogin(String cpf, String senha) {
    return this.pessoasClientes
            .stream()
            .filter(cliente -> cliente.getCpf().equals(cpf) && cliente.validarSenha(senha))
            .findAny().orElse(null);
  }

  /**
   * Método que transfere fundos entre contas.
   */
  public void transferirFundos(PessoaCliente pessoaCliente,
                               int contaOrigem,
                               int contaDestino,
                               double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(contaOrigem,
            quantia,
            "Transferência realizada.");
    pessoaCliente.adicionarTransacaoContaEspecifica(contaDestino,
            quantia,
            "Transferência recebida.");
  }

  public void sacar(PessoaCliente pessoaCliente, int contaOrigem, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(contaOrigem, quantia * -1, "Saque Efetuado");
  }

  public void depositar(PessoaCliente pessoaCliente, int contaDestino, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(contaDestino, quantia, "Depósito recebido");
  }

  public void mostrarExtrato(PessoaCliente pessoaCliente, int conta) {
    pessoaCliente.retornarExtratoContaEspecifica(conta);
  }
}