package com.metacurso.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "turmas")
public class Turmas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @NotNull
    private String nome;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_inicio")
    private Date datainicio;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_termino")
    private Date datatermino;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "codigo")
    private Cursos curso;
    @Transient
    private Horarios horario;
    @Column(name = "n_vagas")
    @NotNull(message = "Nº de vagas é obrigatório")
    private Integer nvagas;
    @Column(name = "vagas_disponiveis")
    private Integer vagasdisponiveis;

    private String turno;

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

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
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

    @Override
    public String toString() {
        return "Turmas{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", datainicio=" + datainicio +
                ", datatermino=" + datatermino +
                ", status=" + status +
                ", curso=" + curso +
                ", horario=" + horario +
                ", nvagas=" + nvagas +
                ", vagasdisponiveis=" + vagasdisponiveis +
                ", turno='" + turno + '\'' +
                '}';
    }
}
