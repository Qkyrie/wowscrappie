package com.deswaef.wowscrappie.item.service;

import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return itemRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Observable<Item> findAll() {
        return Observable.from(itemRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Observable<Item> findByNameQuery(String query) {
        return Observable.from(itemRepository.findTop20ByNameContainingIgnoreCase(query));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Item> findOne(long id) {
        return itemRepository.findOne(id);
    }

}