package com.trybe.acc.java.caixaeletronico;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
  private double quantia;
  private String instante;
  private String descricao;
  private Conta conta;

  public static final DateTimeFormatter PADRAO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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

  public String retornarResumoTransacao() {
    return "Horário da Transação: " + this.instante
            + " Quantia: " + this.quantia
            + " Descrição: " + this.descricao;
  }
}

