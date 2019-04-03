package com.metacurso.model;

import javax.persistence.*;

/**
 * Created by luciano on 12/6/18.
 */

@Entity
@Table(name = "cursos_materiais")
public class CursosMateriais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "codigo", nullable = false)
    private Cursos curso;
    @ManyToOne
    @JoinColumn(name = "material", referencedColumnName = "codigo")
    private Materiais material;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public Materiais getMaterial() {
        return material;
    }

    public void setMaterial(Materiais material) {
        this.material = material;
    }
}