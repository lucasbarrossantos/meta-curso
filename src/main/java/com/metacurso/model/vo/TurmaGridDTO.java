package com.metacurso.model.vo;


import java.util.Date;

public class TurmaGridDTO {

    private Integer codigo;
    private String nome;
    private Date datainicio;
    private Date datatermino;
    private Integer status;
    private Integer cursoId;
    private String nomeCurso;
    private Integer nvagas;
    private Integer vagasdisponiveis;
    private String turno;

    public TurmaGridDTO(Integer codigo,
                        String nome,
                        Date datainicio,
                        Date datatermino,
                        Integer status,
                        Integer cursoId,
                        String nomeCurso,
                        Integer nvagas,
                        Integer vagasdisponiveis,
                        String turno) {
        this.codigo = codigo;
        this.nome = nome;
        this.datainicio = datainicio;
        this.datatermino = datatermino;
        this.status = status;
        this.cursoId = cursoId;
        this.nomeCurso = nomeCurso;
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

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
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
