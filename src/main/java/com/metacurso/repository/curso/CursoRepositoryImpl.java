package com.metacurso.repository.curso;

import com.metacurso.model.Cursos;
import com.metacurso.model.vo.CursoDTO;
import com.metacurso.repository.filter.CursoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CursoRepositoryImpl implements CursoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<CursoDTO> resumir(CursoFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<CursoDTO> criteria = builder.createQuery(CursoDTO.class);
        Root<Cursos> root = criteria.from(Cursos.class);

        criteria.select(builder.construct(CursoDTO.class,
                root.get("codigo"), root.get("nome"),
                root.get("descricao"), root.get("observacoes"),
                root.get("taxa_matricula"), root.get("total_material"),
                root.get("valorCurso"), root.get("status")

        ));

        Predicate[] predicates = criarRestrincoes(filter, builder, root);

        Sort sort = pageable.getSort();
        if (sort != null) {
            Sort.Order order = sort.iterator().next();
            String property = order.getProperty();
            criteria.orderBy(order.isAscending() ? builder.asc(root.get(property))
                    : builder.desc(root.get(property)));
        }

        criteria.where(predicates);
        TypedQuery<CursoDTO> query = manager.createQuery(criteria);
        adicionarRestrincoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    private void adicionarRestrincoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(CursoFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Cursos> root = criteria.from(Cursos.class);

        Predicate[] predicates = criarRestrincoes(filter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private Predicate[] criarRestrincoes(CursoFilter filter,
                                         CriteriaBuilder builder,
                                         Root<Cursos> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(filter.getNome())) {
            predicates.add(builder.like(
                    builder.lower(root.get("nome")),
                    "%" + filter.getNome().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(filter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get("descricao")),
                    "%" + filter.getDescricao().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(filter.getStatus())) {
            predicates.add(builder.equal(root.get("status"), filter.getStatus()));
        }


        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
