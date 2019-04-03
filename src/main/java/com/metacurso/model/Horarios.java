package com.metacurso.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "horarios")
public class Horarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @ManyToOne
    @JoinColumn(name = "turma", referencedColumnName = "codigo")
    private Turmas turma;
    @ManyToOne
    @JoinColumn(name = "disciplina", referencedColumnName = "codigo")
    private Disciplinas disciplina;
    @ManyToOne
    @JoinColumn(name = "professor", referencedColumnName = "codigo")
    private Pessoas professor;
    private Integer dia;
    private String inicio;
    private String fim;
    @Transient
    private String dia_extenso;
    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "codigo")
    private Matriculas matricula;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,##0.00")
    private Double valor_disciplina;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_aula")
    private Date dataAula;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Turmas getTurma() {
        return turma;
    }

    public void setTurma(Turmas turma) {
        this.turma = turma;
    }

    public Disciplinas getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplinas disciplina) {
        this.disciplina = disciplina;
    }

    public Pessoas getProfessor() {
        return professor;
    }

    public void setProfessor(Pessoas professor) {
        this.professor = professor;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getDia_extenso() {

        if (dia != null) {
            if (dia == 0) {
                dia_extenso = "Domingo";
            }
            if (dia == 1) {
                dia_extenso = "Segunda-feira";
            }
            if (dia == 2) {
                dia_extenso = "Terça-feira";
            }
            if (dia == 3) {
                dia_extenso = "Quarta-feira";
            }
            if (dia == 4) {
                dia_extenso = "Quinta-feira";
            }
            if (dia == 5) {
                dia_extenso = "Sexta-feira";
            }
            if (dia == 6) {
                dia_extenso = "Sábado";
            }
        }

        return dia_extenso;
    }

    public void setDia_extenso(String dia_extenso) {
        this.dia_extenso = dia_extenso;
    }

    public Matriculas getMatricula() {
        return matricula;
    }

    public void setMatricula(Matriculas matricula) {
        this.matricula = matricula;
    }

    public Double getValor_disciplina() {
        return valor_disciplina;
    }

    public void setValor_disciplina(Double valor_disciplina) {
        this.valor_disciplina = valor_disciplina;
    }

    public Date getDataAula() {
        return dataAula;
    }

    public void setDataAula(Date dataAula) {
        this.dataAula = dataAula;
    }
}
