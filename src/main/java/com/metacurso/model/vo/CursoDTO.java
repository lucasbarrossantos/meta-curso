package com.metacurso.model.vo;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import java.math.BigDecimal;

public class CursoDTO {

    private Integer codigo;
    private String nome;
    private String descricao;
    private String observacoes;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double taxa_matricula;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double total_material;
    @Column(name = "valor_curso")
    private BigDecimal valorCurso;
    private Integer status;

    public CursoDTO(Integer codigo, String nome, String descricao, String observacoes, Double taxa_matricula, Double total_material, BigDecimal valorCurso, Integer status) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.observacoes = observacoes;
        this.taxa_matricula = taxa_matricula;
        this.total_material = total_material;
        this.valorCurso = valorCurso;
        this.status = status;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getTaxa_matricula() {
        return taxa_matricula;
    }

    public void setTaxa_matricula(Double taxa_matricula) {
        this.taxa_matricula = taxa_matricula;
    }

    public Double getTotal_material() {
        return total_material;
    }

    public void setTotal_material(Double total_material) {
        this.total_material = total_material;
    }

    public BigDecimal getValorCurso() {
        return valorCurso;
    }

    public void setValorCurso(BigDecimal valorCurso) {
        this.valorCurso = valorCurso;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
