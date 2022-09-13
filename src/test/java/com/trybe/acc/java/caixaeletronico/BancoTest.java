package com.trybe.acc.java.caixaeletronico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe Banco")
class BancoTest {
  public static final String NOME = "Alexiania Silva";
  public static final String CPF_VALIDO = "8514540006";
  public static final String CPF_INVALIDO = "8514548806";
  public static final String SENHA = "asdas40ad006";

  public static final int PARA_CONTA = 0;
  public static final int DA_CONTA = 1;
  public static final double VALOR_DEPOSITADO = 3000;
  public static final double VALOR_TRANSFERIDO = 1000;

  private final ByteArrayOutputStream saida = new ByteArrayOutputStream();
  Banco banco = new Banco();

  @Test
  @DisplayName("1 - Testa o gerador de número único para nova conta.")
  void gerarNumeroNovaContaTest() {
    String numeroGerado = banco.gerarNumeroNovaConta();

    assertEquals(10, numeroGerado.length());
  }

  @Test
  @DisplayName("2 - Testa o método adicionar pessoa cliente retorna o objeto pessoa cliente.")
  void adicionarPessoaClienteTest() {
    PessoaCliente pessoaCliente = banco.adicionarPessoaCliente(NOME, CPF_VALIDO, SENHA);

    assertEquals(PessoaCliente.class, pessoaCliente.getClass());
  }

  @Test
  @DisplayName("3 - Testa o método login da pessoa cliente retorna o objeto pessoa cliente corretamente.")
  void pessoaClienteLoginTest() {
    PessoaCliente pessoaClienteAdicionada = banco.adicionarPessoaCliente(NOME, CPF_VALIDO, SENHA);
    PessoaCliente pessoaClienteValida = banco.pessoaClienteLogin(CPF_VALIDO, SENHA);
    PessoaCliente pessoaClienteInvalida = banco.pessoaClienteLogin(CPF_INVALIDO, SENHA);

    assertEquals(pessoaClienteAdicionada, pessoaClienteValida);
    assertNull(pessoaClienteInvalida);
  }

  @Test
  @DisplayName("4 - Testa se o método transferir fundos está transferindo corretamente.")
  void depositarTestTransferirFundosTestmostrarExtratoTest() {
    PessoaCliente pessoaCliente = banco.adicionarPessoaCliente(NOME, CPF_VALIDO, SENHA);
    banco.depositar(pessoaCliente, PARA_CONTA, VALOR_DEPOSITADO);
    banco.transferirFundos(pessoaCliente, DA_CONTA, PARA_CONTA, VALOR_TRANSFERIDO);
    banco.mostrarExtrato(pessoaCliente, PARA_CONTA);
    String stringSaida = saida.toString();

    assertTrue(stringSaida.contains("DEPÓSITO"));
    assertTrue(stringSaida.contains("+3000.0"));
    assertTrue(stringSaida.contains("TRANSFERÊNCIA"));
    assertTrue(stringSaida.contains("-1000.0"));
  }

  @Test
  @DisplayName("5 - Testa se o método sacar está funcionando corretamente.")
  void depositarTestSacarTestMostrarExtratoTest() {
    PessoaCliente pessoaCliente = banco.adicionarPessoaCliente(NOME, CPF_VALIDO, SENHA);
    banco.depositar(pessoaCliente, PARA_CONTA, VALOR_DEPOSITADO);
    banco.sacar(pessoaCliente, DA_CONTA, VALOR_TRANSFERIDO);
    banco.mostrarExtrato(pessoaCliente, PARA_CONTA);
    String stringSaida = saida.toString();

    assertTrue(stringSaida.contains("DEPÓSITO"));
    assertTrue(stringSaida.contains("+3000.0"));
    assertTrue(stringSaida.contains("SAQUE"));
    assertTrue(stringSaida.contains("-1000.0"));
  }
}
