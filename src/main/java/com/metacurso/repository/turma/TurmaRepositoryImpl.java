package com.metacurso.repository.turma;

import com.metacurso.model.Turmas;
import com.metacurso.model.vo.TurmaGridDTO;
import com.metacurso.repository.filter.TurmaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaRepositoryImpl implements TurmaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<TurmaGridDTO> resumo(TurmaFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TurmaGridDTO> criteria = builder.createQuery(TurmaGridDTO.class);
        Root<Turmas> root = criteria.from(Turmas.class);
        From<?, ?> cursoJoin = root.join("curso", JoinType.INNER);


        criteria.select(builder.construct(TurmaGridDTO.class,
                root.get("codigo"), root.get("nome"),
                root.get("datainicio"), root.get("datatermino"),
                root.get("status"), cursoJoin.get("codigo"),
                cursoJoin.get("nome"), root.get("nvagas"),
                root.get("vagasdisponiveis"), root.get("turno")
        ));

        Predicate[] predicates = criarRestrincoes(filter, builder, root, cursoJoin);

        Sort sort = pageable.getSort();
        if (sort != null) {
            Sort.Order order = sort.iterator().next();
            String property = order.getProperty();
            criteria.orderBy(order.isAscending() ? builder.asc(root.get(property))
                    : builder.desc(root.get(property)));
        }

        criteria.where(predicates);
        TypedQuery<TurmaGridDTO> query = manager.createQuery(criteria);
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

    private Long total(TurmaFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Turmas> root = criteria.from(Turmas.class);
        From<?, ?> cursoJoin = root.join("curso", JoinType.INNER);

        Predicate[] predicates = criarRestrincoes(filter, builder, root, cursoJoin);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private Predicate[] criarRestrincoes(TurmaFilter filter,
                                         CriteriaBuilder builder,
                                         Root<Turmas> root, From<?, ?> cursoJoin) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(filter.getNome())) {
            predicates.add(builder.like(
                    builder.lower(root.get("nome")),
                    "%" + filter.getNome().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(filter.getNomeCurso())) {
            predicates.add(builder.like(
                    builder.lower(cursoJoin.get("nome")),
                    "%" + filter.getNomeCurso().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(filter.getStatus())) {
            predicates.add(builder.equal(root.get("status"), filter.getStatus()));
        }

        if (filter.getInicioDe() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("datainicio"),
                            filter.getInicioDe()));

        }

        if (filter.getInicioAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("datainicio"),
                            filter.getInicioAte()));
        }

        if (filter.getTerminoDe() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("datatermino"),
                            filter.getTerminoDe()));

        }

        if (filter.getTerminoAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("datatermino"),
                            filter.getTerminoAte()));
        }


        return predicates.toArray(new Predicate[predicates.size()]);
    }
    
}
