package com.trybe.acc.java.caixaeletronico;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes dos métodos da classe Transacao")
class TransacaoTest {
  public static final double QUANTIA_DEPOSITO = 5000;
  public static final String DESCRICAO_DEPOSITO = "Depósito recebido";
  public static final DateTimeFormatter PADRAO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
  Transacao transacao;

  @BeforeEach void initEach() {
    transacao = new Transacao(QUANTIA_DEPOSITO, DESCRICAO_DEPOSITO);
  }
  @Test
  @DisplayName("21 - Testa o método construtor da classe Transacao.")
  void construtorTest() {
    String resumo = transacao.retornarResumoTransacao();
    LocalDateTime dataAtual = LocalDateTime.now();
    String dataFormatada = PADRAO_DATA.format(dataAtual);

    assertTrue(resumo.contains(String.valueOf(QUANTIA_DEPOSITO)));
    assertTrue(resumo.contains(DESCRICAO_DEPOSITO));
    assertTrue(resumo.contains(dataFormatada));
  }


  @Test
  @DisplayName("22 - Testa o método Getter do atributo quantia.")
  void getQuantiaTest() {
    double quantia = transacao.getQuantia();

    assertEquals(QUANTIA_DEPOSITO, quantia);
  }

  @Test
  @DisplayName("23 - Testa o método retornar resumo transacao.")
  void retornarResumoTransacaoTest() {
    String resumo = transacao.retornarResumoTransacao();

    LocalDateTime dataAtual = LocalDateTime.now();
    String dataFormatada = PADRAO_DATA.format(dataAtual);

    assertTrue(resumo.contains(String.valueOf(QUANTIA_DEPOSITO)));
    assertTrue(resumo.contains(DESCRICAO_DEPOSITO));
    assertTrue(resumo.contains(dataFormatada));
  }

  @Test
  @DisplayName("24 - Testa o método instante está gerando o instante corretamente.")
  void retornarInstanteTest() {
    String instante = transacao.retornarInstante();
    LocalDateTime dataAtual = LocalDateTime.now();
    String dataFormatada = PADRAO_DATA.format(dataAtual);

    assertEquals(dataFormatada, instante);
  }

}
