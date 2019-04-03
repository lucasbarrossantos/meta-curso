package com.metacurso.model;

import javax.persistence.*;

@Entity
@Table(name = "interessados_eventos")
public class InteressadosEventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    @JoinColumn(name = "interessado", referencedColumnName = "codigo", nullable = false)
    private Interessados interessados;

    @ManyToOne
    @JoinColumn(name = "evento", referencedColumnName = "codigo", nullable = false)
    private Eventos eventos;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Interessados getInteressados() {
        return interessados;
    }

    public void setInteressados(Interessados interessados) {
        this.interessados = interessados;
    }

    public Eventos getEventos() {
        return eventos;
    }

    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }
}
