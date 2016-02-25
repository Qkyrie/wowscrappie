package com.deswaef.wowscrappie.item.service;

import com.deswaef.wowscrappie.item.domain.Item;
import rx.Observable;

import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    long count();

    Observable<Item> findAll();

    Observable<Item> findByNameQuery(String query);

    Optional<Item> findOne(long id);
}