package com.deswaef.wowscrappie.item.service;

import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

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
    public Observable<Item> findAll() {
        return Observable.from(itemRepository.findAll());
    }
}