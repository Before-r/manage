package org.csu.manage.controller;

import org.csu.manage.domain.*;
import org.csu.manage.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/catalog/")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("index")
    public String index() {
        return "catalog/main";
    }

    @GetMapping("viewAccount")
    public String viewAccount(Model model) {
        List<Account> accountList = catalogService.getAccountList();
        model.addAttribute("accountList",accountList);
        return "catalog/account";
    }

    @PostMapping("updateAccount")
    public String updateAccount(Account account,Model model) {
        catalogService.updateAccount(account);
        model.addAttribute("account",account);
        return "catalog/main";
    }


    @PostMapping("updateItem")
    public String updateItem(Item item,Model model) {
        catalogService.updateItem(item);
        Item item1 = item;
        System.out.println(item1.getItemId());
        model.addAttribute("item",item);
        return "catalog/main";
    }


    @GetMapping("viewCategory")
    public String viewCategory(String categoryId, Model model) {
        if (categoryId != null) {
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            Category category = catalogService.getCategory(categoryId);
            model.addAttribute("productList", productList);
            model.addAttribute("category", category);
        }
        return "catalog/category";
    }

    @GetMapping("viewProduct")
    public String viewProduct(String productId, Model model) {
        if (productId != null) {
            List<Item> itemList = catalogService.getItemListByProduct(productId);
            Product product = catalogService.getProduct(productId);
            model.addAttribute("product", product);
            model.addAttribute("itemList", itemList);
        }
        return "catalog/product";
    }

    @GetMapping("viewItem")
    public String viewItem(String itemId, Model model){
        Item item = catalogService.getItem(itemId);
        Product product = item.getProduct();
        processProductDescription(product);
        model.addAttribute("item",item);
        model.addAttribute("product",product);
        return "catalog/item";
    }

    @GetMapping("addItem")
    public String addItem(Model model){
        model.addAttribute("newItem",new Item());
        return "catalog/add_Item";
    }

    @PostMapping("add_Item")
    public String add_Item(Item item,Model model) {
        catalogService.insertItem(item);
        catalogService.insertItemQuantity(item);
        model.addAttribute("item",item);
        return "catalog/main";
    }

    @GetMapping("deleteItem")
    public String deleteItem(String itemId) {
        Item item = new Item();
        item.setItemId(itemId);
        System.out.println(item.getItemId());
        catalogService.deleteItem(item);
        catalogService.deleteItemQuantity(item);
        return "catalog/main";
    }


    @GetMapping("editAccount")
    public String editAccount(String username, Model model){
        Account account = catalogService.getAccount(username);
        model.addAttribute("account",account);
        return "catalog/update_Account";
    }

    @GetMapping("viewOrder")
    public String viewOrder(Model model) {
        return "catalog/order";
    }

    @GetMapping("updateOrder")
    public String updateOrder() {
        return "catalog/update_Order";
    }

    @GetMapping("sendOrder")
    public String sendOrder() {
        return "catalog/sendOrder";
    }


    @PostMapping("searchProducts")
    public String searchProducts(String keyword, Model model){
        if(keyword == null || keyword.length() < 1){
            String msg = "Please enter a keyword to search for, then press the search button.";
            model.addAttribute("msg",msg);
            return "common/error";
        }else {
            List<Product> productList = catalogService.searchProductList(keyword.toLowerCase());
            processProductDescription(productList);
            model.addAttribute("productList",productList);
            return "catalog/search_products";
        }

    }


    private void processProductDescription(Product product){
        String [] temp = product.getDescription().split("\"");
        product.setDescriptionImage(temp[1]);
        product.setDescriptionText(temp[2].substring(1));
    }
    private void processProductDescription(List<Product> productList){
        for(Product product : productList) {
            processProductDescription(product);
        }
    }

}
