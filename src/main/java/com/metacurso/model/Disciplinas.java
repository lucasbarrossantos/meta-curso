package com.metacurso.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by luciano on 11/6/18.
 */

@Entity
@Table(name = "disciplinas")
public class Disciplinas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @NotNull
    private String nome;
    private Integer status;
    private Integer lingua_estrangeira;
    @NumberFormat(style = NumberFormat.Style.NUMBER,pattern = "#,##0.00")
    private Double valor;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLingua_estrangeira() {
        return lingua_estrangeira;
    }

    public void setLingua_estrangeira(Integer lingua_estrangeira) {
        this.lingua_estrangeira = lingua_estrangeira;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}