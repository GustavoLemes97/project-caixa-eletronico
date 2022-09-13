package com.trybe.acc.java.caixaeletronico;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste dos métodos da classe PessoaCliente")
class PessoaClienteTest {
  public static final String NOME = "Alexiania Silva";
  public static final String CPF = "8514540006";
  public static final String SENHA = "asdas40ad006";
  public static final String SENHA_INVALIDA = "asdSSas40ad006";
  public static final String TIPO_CONTA = "Corrente";
  public static final double QUANTIA_DEPOSITO = 5000;

  public static final double QUANTIA_SEGUNDO_DEPOSITO = 10000;
  public static final String DESCRICAO_DEPOSITO = "Depósito recebido";
  Banco banco = new Banco();
  PessoaCliente pessoaCliente;
  Conta conta;

  @BeforeEach
  void initEach() {
    pessoaCliente = new PessoaCliente(NOME, CPF, SENHA);
    conta = new Conta(TIPO_CONTA, banco, pessoaCliente);
  }

  private final ByteArrayOutputStream saida = new ByteArrayOutputStream();

  @Test
  @DisplayName("12 - Testa o construtor da classe Pessoa Cliente.")
  void construtorTest() {
    String stringSaida = saida.toString();

    assertTrue(stringSaida.contains("Nova pessoa cliente " + NOME + " com CPF: " + CPF + " criada!"));
  }

  @Test
  @DisplayName("13 - Testa o método adicionar conta e o método retornar número de contas.")
  void adicionarContaTestRetornaNumeroDeContasTest() {
    int numeroDeContas = pessoaCliente.retornaNumeroDeContas();

    assertEquals(0, numeroDeContas);
    pessoaCliente.adicionarConta(conta);
    assertEquals(1, numeroDeContas);
  }

  @Test
  @DisplayName("14 - Testa o método retornar saldo de uma conta específica da pessoa cliente.")
  void retornarSaldoContaEspecificaTest() {
    pessoaCliente.adicionarConta(conta);
    conta.adicionarTransacao(QUANTIA_DEPOSITO, DESCRICAO_DEPOSITO);
    double saldoContaEspecifica = pessoaCliente.retornarSaldoContaEspecifica(0);

    assertEquals(saldoContaEspecifica, QUANTIA_DEPOSITO);
  }

  @Test
  @DisplayName("15 - Testa o método retornar id de uma conta específica da pessoa cliente.")
  void retornarIdContaEspecificaTest() {
    SENHA_INVALIDA
    pessoaCliente.adicionarConta(conta);
    String idContaEspecificada = pessoaCliente.retornarIdContaEspecifica(0);

    assertEquals(10, idContaEspecificada.length());
  }

  @Test
  @DisplayName("16 - Testa o método retornar o extrato de uma conta específica da pessoa cliente.")
  void retornarExtratoContaEspecificaTest() {
    pessoaCliente.adicionarConta(conta);
    conta.adicionarTransacao(QUANTIA_DEPOSITO, DESCRICAO_DEPOSITO);
    conta.adicionarTransacao(QUANTIA_SEGUNDO_DEPOSITO, DESCRICAO_DEPOSITO);
    pessoaCliente.retornarExtratoContaEspecifica(0);

    String stringSaida = saida.toString();

    assertTrue(stringSaida.contains(DESCRICAO_DEPOSITO));
    assertTrue(stringSaida.contains(String.valueOf(QUANTIA_DEPOSITO)));
    assertTrue(stringSaida.contains(String.valueOf(QUANTIA_SEGUNDO_DEPOSITO)));
  }

  @Test
  @DisplayName("17 - Testa o método adiciona transacao de uma conta específica da pessoa cliente.")
  void adicionarTransacaoContaEspecificaTest() {
    pessoaCliente.adicionarConta(conta);

    pessoaCliente.adicionarTransacaoContaEspecifica(0, QUANTIA_DEPOSITO, DESCRICAO_DEPOSITO);
    pessoaCliente.retornarExtratoContaEspecifica(0);

    String stringSaida = saida.toString();

    assertTrue(stringSaida.contains(DESCRICAO_DEPOSITO));
    assertTrue(stringSaida.contains(String.valueOf(QUANTIA_DEPOSITO)));
  }

  @Test
  @DisplayName("18 - Testa o método validar senha.")
  void validarSenhaTest() {
    boolean senhaValida = pessoaCliente.validarSenha(SENHA);
    boolean senhaInvalida = pessoaCliente.validarSenha(SENHA_INVALIDA);

    assertTrue(senhaValida);
    assertFalse(senhaInvalida);
  }

  @Test
  @DisplayName("19 - Testa o método retornar resumo contas.")
  void retornarResumoContasTest() {
    conta.adicionarTransacao(QUANTIA_DEPOSITO, DESCRICAO_DEPOSITO);
    pessoaCliente.retornarResumoContas();

    String idConta = conta.getIdConta();
    double saldoTotal = conta.retornarSaldo();
    String resumoConta = conta.retornarResumoConta();

    assertTrue(resumoConta.contains(idConta));
    assertTrue(resumoConta.contains(TIPO_CONTA));
    assertTrue(resumoConta.contains(String.valueOf(saldoTotal)));
  }

  @Test
  @DisplayName("20 - Testa o método Getter do atributo cpf está retornando.")
  void getCpfTest() {
    String cpf = pessoaCliente.getCpf();

    assertEquals(CPF, cpf);
  }

}
