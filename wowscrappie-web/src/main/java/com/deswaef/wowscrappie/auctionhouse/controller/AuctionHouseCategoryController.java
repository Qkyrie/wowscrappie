package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.controller.dto.AuctionHouseCategoryDto;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseCategoryService;
import com.deswaef.wowscrappie.item.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/rest/auctionhouse")
public class AuctionHouseCategoryController {

    @Autowired
    private AuctionHouseCategoryService auctionHouseCategoryService;


    @RequestMapping(value = "/category", method = GET)
    @ResponseBody
    public DeferredResult<List<AuctionHouseCategoryDto>> findAll() {
        DeferredResult<List<AuctionHouseCategoryDto>> result = new DeferredResult<>();
        auctionHouseCategoryService
                .findAll()
                .map(AuctionHouseCategoryDto::from)
                .toList()
                .subscribe(
                        result::setResult
                );
        return result;
    }

    @RequestMapping(value = "/subcategory/{subcategoryId}/items")
    @ResponseBody
    public DeferredResult<List<Item>> itemsBySubCategory(@PathVariable("subcategoryId") long subcategoryId) {
        DeferredResult<List<Item>> result = new DeferredResult<>();
        auctionHouseCategoryService
                .findItems(subcategoryId)
                .toList()
                .subscribe(
                        result::setResult
                );
        return result;
    }

}
