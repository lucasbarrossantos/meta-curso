package com.metacurso.model.enumeration;

public enum TipoOperacao {

    ENTRADA("Entrada"), SAIDA("Saída");

    private String descricao;


    TipoOperacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}