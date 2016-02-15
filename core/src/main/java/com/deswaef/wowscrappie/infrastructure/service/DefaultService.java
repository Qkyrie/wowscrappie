package com.deswaef.wowscrappie.infrastructure.service;


import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * User: Quinten
 * Date: 5-8-2014
 * Time: 22:03
 *
 * @author Quinten De Swaef
 */
@Transactional(readOnly = true)
public interface DefaultService<ENTITY, DTO, ID extends Serializable> {

    default Optional<DTO> findOne(DtoMapper<ENTITY, DTO> mapper, ID id) {
        return getRepository().findOne(id).map(mapper::map);
    }

    default List<? extends DTO> findAll(DtoMapper<ENTITY, DTO> mapper) {
        Assert.notNull(mapper);
        return findAll(mapper, x -> true);
    }

    default List<? extends DTO> findAll(DtoMapper<ENTITY, DTO> mapper, Predicate<? super ENTITY> filter) {
        Assert.notNull(mapper);
        return getRepository().findAll()
                .stream()
                .filter(filter)
                .map(mapper::map)
                .collect(toList());
    }

    JpaRepository<ENTITY, ID > getRepository();
}
