package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSubCategory;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseCategoryService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping(value = "/auctionhouse/subcategory")
public class AuctionHouseCategoryController {

    @Autowired
    private AuctionHouseCategoryService auctionHouseCategoryService;

    @RequestMapping(method = RequestMethod.GET, value = "/{subcategoryId}")
    public String bySubcategory(ModelMap modelMap, @PathVariable("subcategoryId") Long subcategoryId) {
        Optional<AuctionHouseSubCategory> auctionHouseSubCategory = auctionHouseCategoryService.subCategoryById(subcategoryId);
        if (auctionHouseSubCategory.isPresent()) {
            modelMap.put("subcategory", auctionHouseSubCategory.get());
            return "auctionhouse/subcategory/subcategory";
        } else {
            return "auctionhouse/subcategory/not-found";
        }
    }

}
