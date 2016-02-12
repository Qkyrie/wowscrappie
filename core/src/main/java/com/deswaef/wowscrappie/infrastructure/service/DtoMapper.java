package com.deswaef.wowscrappie.infrastructure.service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Quinten
 * Date: 5-8-2014
 * Time: 22:06
 *
 * @author Quinten De Swaef
 */
public interface DtoMapper<ENTITY, DTO> {

    DTO map(ENTITY entity);

    default List<? extends DTO> mapList(List<ENTITY> toMap) {
        return toMap.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
