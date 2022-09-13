package com.trybe.acc.java.caixaeletronico;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste da classe Conta")
class ContaTest {
  public static final double QUANTIA_SAQUE = 1000;
  public static final double QUANTIA_DEPOSITO = 5000;
  public static final String DESCRICAO_SAQUE = "Saque Efetuado";
  public static final String DESCRICAO_DEPOSITO = "Depósito recebido";
  public static final String TIPO_CONTA = "Corrente";
  Banco banco = new Banco();
  PessoaCliente pessoaCliente = new PessoaCliente();
  Conta conta;

  private final ByteArrayOutputStream saida = new ByteArrayOutputStream();

  @BeforeEach
  void initEach() {
    conta = new Conta(TIPO_CONTA, banco, pessoaCliente);
  }

  @Test
  @DisplayName("6 - Testa o construtor da classe conta.")
  void construtorTest() {

    PessoaCliente pessoaClienteCriada = conta.getPessoaCliente();
    String resumoConta = conta.retornarResumoConta();
    String idConta = conta.getIdConta();

    assertEquals(pessoaCliente, pessoaClienteCriada);
    assertTrue(resumoConta.contains(TIPO_CONTA));
    assertEquals(10, idConta.length());
  }

  @Test
  @DisplayName("7 - Testa o método adicionar transação e retornar saldo da conta.")
  void adicionarTransacaoTestRetornarSaldoTest() {
    conta.adicionarTransacao(QUANTIA_DEPOSITO, DESCRICAO_DEPOSITO);
    conta.adicionarTransacao(QUANTIA_DEPOSITO, DESCRICAO_DEPOSITO);
    double saldoTotal = conta.retornarSaldo();

    assertEquals(10000, saldoTotal);
  }

  @Test
  @DisplayName("8 - Testa o método retornar resumo está retornando uma string com os valores corretamente.")
  void retornarResumoContaTest() {
    conta.adicionarTransacao(QUANTIA_DEPOSITO, DESCRICAO_DEPOSITO);
    String idConta = conta.getIdConta();
    double saldoTotal = conta.retornarSaldo();
    String resumoConta = conta.retornarResumoConta();

    assertTrue(resumoConta.contains(idConta));
    assertTrue(resumoConta.contains(TIPO_CONTA));
    assertTrue(resumoConta.contains(String.valueOf(saldoTotal)));
  }

  @Test
  @DisplayName("9 - Testa o método retornar extrato está imprimindo os valores corretamente.")
  void retornarExtratoTest() {
    conta.adicionarTransacao(QUANTIA_DEPOSITO, DESCRICAO_DEPOSITO);
    conta.adicionarTransacao(QUANTIA_SAQUE, DESCRICAO_SAQUE);

    conta.retornarExtrato();
    String stringSaida = saida.toString();

    assertTrue(stringSaida.contains(DESCRICAO_DEPOSITO));
    assertTrue(stringSaida.contains(String.valueOf(QUANTIA_DEPOSITO)));
    assertTrue(stringSaida.contains(DESCRICAO_SAQUE));
    assertTrue(stringSaida.contains(String.valueOf(QUANTIA_SAQUE)));
  }

  @Test
  @DisplayName("10 - Testa o método Getter do atributo idConta está retornando.")
  void getIdContaTest() {
    String idConta = conta.getIdConta();

    assertEquals(10, idConta.length());
  }

  @Test
  @DisplayName("11 - Testa o método método Getter do atributo pessoaCliente está retornando.")
  void getPessoaClienteTest() {
    PessoaCliente pessoaClienteCriada = conta.getPessoaCliente();

    assertEquals(pessoaCliente, pessoaClienteCriada);
  }

}
