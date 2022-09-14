package com.trybe.acc.java.caixaeletronico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilizada para representar a transação nas contas do banco..
 */
public class Transacao {
  private final double quantia;
  private final String instante;
  private final String descricao;

  public static final DateTimeFormatter PADRAO_DATA = DateTimeFormatter
          .ofPattern("dd/MM/yyyy HH:mm:ss");

  /**
   * Método construtor que cria transações.
   */
  public Transacao(double quantia, String descricao) {
    this.quantia = quantia;
    this.descricao = descricao;
    this.instante = retornarInstante();
  }

  public String retornarInstante() {
    LocalDateTime dataAtual = LocalDateTime.now();
    return PADRAO_DATA.format(dataAtual);
  }

  public double getQuantia() {
    return this.quantia;
  }

  /**
   * Método que gera resumo da transação.
   */
  public String retornarResumoTransacao() {
    return "Horário da Transação: " + this.instante
            + " Quantia: " + this.quantia
            + " Descrição: " + this.descricao;
  }
}

