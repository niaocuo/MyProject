package com.hang.up.service;

import com.hang.up.dao.ManagerDao;
import com.hang.up.model.*;
import com.hang.up.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 顺 on 2018/12/8.
 */
@Service("managerService")
public class ManagerService {

    @Autowired
   private ManagerDao managerDao;

    public void saveHome(List<Home> homeList) {
       List<Home> addHomes = new ArrayList<>();
        List<Home> updateHomes = new ArrayList<>();
        for(Home home:homeList){
            if(home.getId()!=null){
                updateHomes.add(home);
            }else{
                addHomes.add(home);
            }
        }
        if(addHomes.size()>0){
            managerDao.insertHomeList(addHomes);
        }
       if(updateHomes.size()>0){
           managerDao.updateHomeList(updateHomes);
       }
    }

    public  List<Home>  getHomeAll() {
    return managerDao.getHomeAll();
    }

    public void deleteHomeById(Integer id) {
        managerDao.deleteHomeById(id);
    }

    public Page getProductPage(Page page) {
        Map<String,Object> map =new HashMap<>();
        map.put("firstIndex",(page.getCurrentPage() - 1) * page.getPageSize());//从第几条数据开始
        map.put("lastIndex", page.getCurrentPage() * page.getPageSize()); //到第几条数据结束
        if(page.getQueryCondition().get("title")!=null && !"".equals(page.getQueryCondition().get("title").toString().trim())){
            map.put("title","%"+page.getQueryCondition().get("title")+"%");
        }
        if(page.getQueryCondition().get("imageId")!=null && !"".equals(page.getQueryCondition().get("imageId").toString().trim())){
            map.put("imageId",page.getQueryCondition().get("imageId"));
        }
        int total = managerDao.findProductTotal(map);
        page.setTotal(total);
        page.setTableData(managerDao.getProductList(map));
        return page;
    }

    public void addProduct(Product product, Image image) {
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        if(image.getUrl()!=null){
            managerDao.addImage(image);
            product.setImageId(image.getId());
            managerDao.addProduct(product);
        }else{
            managerDao.addProduct(product);
        }
    }

    public void updateProduct(Product product, Image image) {
        product.setUpdateTime(new Date());
        if(image.getUrl()!=null){
            if(image.getId()==null){
                managerDao.addImage(image);
            }
            product.setImageId(image.getId());
            managerDao.updateProduct(product);
        }else{
            managerDao.updateProduct(product);
        }
    }

    public Product getProductById(Integer id) {
        return managerDao.getProductById(id);
    }

    public void deleteProduct(Integer id) {
        managerDao.deleteProduct(id);
    }

    public Page getInformationPage(Page page) {
        Map<String,Object> map =new HashMap<>();
        map.put("firstIndex",(page.getCurrentPage() - 1) * page.getPageSize());//从第几条数据开始
        map.put("lastIndex", page.getCurrentPage() * page.getPageSize()); //到第几条数据结束
        if(page.getQueryCondition().get("title")!=null && !"".equals(page.getQueryCondition().get("title").toString().trim())){
            map.put("title","%"+page.getQueryCondition().get("title")+"%");
        }
        if(page.getQueryCondition().get("imageId")!=null && !"".equals(page.getQueryCondition().get("imageId").toString().trim())){
            map.put("imageId",page.getQueryCondition().get("imageId"));
        }
        int total = managerDao.findInformationTotal(map);
        page.setTotal(total);
        page.setTableData(managerDao.getInformationList(map));
        return page;
    }


    public void addInformation(Information information, Image image) {
        information.setCreateTime(new Date());
        information.setUpdateTime(new Date());
        if(image.getUrl()!=null){
            managerDao.addImage(image);
            information.setImageId(image.getId());
            managerDao.addInformation(information);
        }else{
            managerDao.addInformation(information);
        }
    }

    public void updateInformation(Information information, Image image) {
        information.setUpdateTime(new Date());
        if(image.getUrl()!=null){
            if(image.getId()==null){
                managerDao.addImage(image);
            }
            information.setImageId(image.getId());
            managerDao.updateInformation(information);
        }else{
            managerDao.updateInformation(information);
        }
    }

    public Information getInformationById(Integer id) {
        return managerDao.getInformationById(id);
    }

    public void deleteInformation(Integer id) {
        managerDao.deleteInformation(id);
    }

    public void saveAboutUs(AboutUs aboutUs) {
        managerDao.saveAboutUs(aboutUs);
    }

    public AboutUs getAboutUs() {
       return managerDao.getAboutUs();
    }

    public void saveContactUs(ContactUs contactUs) {
        managerDao.saveContactUs(contactUs);
    }

    public ContactUs getContactUs() {
        return managerDao.getContactUs();
    }

    public void saveMessage(Message message) {
        message.setCreateTime(new Date());
        managerDao.saveMessage(message);
    }

    public Page getMessagePage(Page page) {
        Map<String,Object> map =new HashMap<>();
        map.put("firstIndex",(page.getCurrentPage() - 1) * page.getPageSize());//从第几条数据开始
        map.put("lastIndex", page.getCurrentPage() * page.getPageSize()); //到第几条数据结束
        if(page.getQueryCondition().get("title")!=null && !"".equals(page.getQueryCondition().get("title").toString().trim())){
            map.put("title","%"+page.getQueryCondition().get("title")+"%");
        }
        int total = managerDao.findMessageTotal(map);
        page.setTotal(total);
        page.setTableData(managerDao.getMessageList(map));
        return page;
    }

    public void deleteMessage(Integer id) {
        managerDao.deleteMessage(id);
    }

    public User getUserByUserName(String userName) {
        return  managerDao.getUserByUserName(userName);
    }
}
