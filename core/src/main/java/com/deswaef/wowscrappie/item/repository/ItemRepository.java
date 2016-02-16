package com.deswaef.wowscrappie.item.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.item.domain.Item;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findTop20ByNameContainingIgnoreCase(@Param("name") String name);
}