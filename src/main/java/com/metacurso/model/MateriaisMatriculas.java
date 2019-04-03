package com.metacurso.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luciano on 25/6/18.
 */

@Entity
@Table(name = "materiais_matriculas")
public class MateriaisMatriculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "codigo", nullable = false)
    private Matriculas matricula;
    @ManyToOne
    @JoinColumn(name = "material", referencedColumnName = "codigo")
    private Materiais material;
    private Boolean disponivel;
    @Column(name = "data_entrega")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataentrega;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Matriculas getMatricula() {
        return matricula;
    }

    public void setMatricula(Matriculas matricula) {
        this.matricula = matricula;
    }

    public Materiais getMaterial() {
        return material;
    }

    public void setMaterial(Materiais material) {
        this.material = material;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(Date dataentrega) {
        this.dataentrega = dataentrega;
    }
}