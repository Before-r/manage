package org.csu.manage.service;

import org.csu.manage.domain.*;
import org.csu.manage.persistence.CategoryMapper;
import org.csu.manage.persistence.ItemMapper;
import org.csu.manage.persistence.ProductMapper;
import org.csu.manage.persistence.AccountMapper;
import org.csu.manage.persistence.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CatalogService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private OrderMapper orderMapper;

    public List<Account> getAccountList() {
        return accountMapper.getAccountList();
    }

    public List<Order> getOrderList() {
        return orderMapper.getOrderList();
    }


    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productMapper.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productMapper.getProductListByCategory(categoryId);
    }

    public List<Product> searchProductList(String keyword) {
        return productMapper.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId){
        return itemMapper.getItemListByProduct(productId);
    }

    public Item getItem(String itemId){
        return itemMapper.getItem(itemId);
    }

    public boolean isItemInStock(String itemId){
        return itemMapper.getInventoryQuantity(itemId) > 0;
    }

    public void updateItem(Item item) {
        itemMapper.updateItem(item);
    }

    public void insertItem(Item item) {
        itemMapper.insertItem(item);
    }

    public void insertItemQuantity(Item item) {
        itemMapper.insertItemQuantity(item);
    }

    public void deleteItem(Item item) {
        itemMapper.deleteItem(item);
    }

    public void deleteItemQuantity(Item item) {
        itemMapper.deleteItemQuantity(item);
    }

    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
    }

    public Account getAccount(String username) {
        return accountMapper.getAccount(username);
    }

}
