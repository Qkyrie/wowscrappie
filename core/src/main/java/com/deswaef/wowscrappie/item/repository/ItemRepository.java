package com.deswaef.wowscrappie.item.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.item.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}