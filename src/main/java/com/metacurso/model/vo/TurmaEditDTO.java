package com.metacurso.model.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TurmaEditDTO {

    private Integer codigo;
    private String nome;
    private Integer curso;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date datainicio;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date datatermino;
    private Integer status;
    private Integer nvagas;
    private Integer vagasdisponiveis;
    private String turno;

    public TurmaEditDTO(Integer codigo,
                        String nome,
                        Integer curso,
                        Date datainicio,
                        Date datatermino,
                        Integer status,
                        Integer nvagas,
                        Integer vagasdisponiveis,
                        String turno) {
        this.codigo = codigo;
        this.nome = nome;
        this.curso = curso;
        this.datainicio = datainicio;
        this.datatermino = datatermino;
        this.status = status;
        this.nvagas = nvagas;
        this.vagasdisponiveis = vagasdisponiveis;
        this.turno = turno;
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

    public Integer getCurso() {
        return curso;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatatermino() {
        return datatermino;
    }

    public void setDatatermino(Date datatermino) {
        this.datatermino = datatermino;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNvagas() {
        return nvagas;
    }

    public void setNvagas(Integer nvagas) {
        this.nvagas = nvagas;
    }

    public Integer getVagasdisponiveis() {
        return vagasdisponiveis;
    }

    public void setVagasdisponiveis(Integer vagasdisponiveis) {
        this.vagasdisponiveis = vagasdisponiveis;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
