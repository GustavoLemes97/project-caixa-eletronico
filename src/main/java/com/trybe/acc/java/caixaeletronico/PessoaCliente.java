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
  }

  public String getCpf() {
    return cpf;
  }

  public boolean validarSenha(String senha) {
    return this.senha.equals(senha);
  }
}
