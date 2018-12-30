package com.hang.up.dao;

import com.hang.up.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 顺 on 2018/12/8.
 */
public interface ManagerDao {

    void insertHomeList(List<Home> addHomes);

    void updateHomeList(List<Home> updateHomes);

    List<Home> getHomeAll();

    void deleteHomeById(Integer id);

    int findProductTotal(Map<String, Object> map);

    List<Product> getProductList(Map<String, Object> map);

    Integer addImage(Image image);

    void addProduct(Product product);

    void updateProduct(Product product);

    Product getProductById(Integer id);

    void deleteProduct(Integer id);

    int findInformationTotal(Map<String, Object> map);

    List<Information> getInformationList(Map<String, Object> map);

    void addInformation(Information information);

    void updateInformation(Information information);

    Information getInformationById(Integer id);

    void deleteInformation(Integer id);

    void saveAboutUs(AboutUs aboutUs);

    AboutUs getAboutUs();

    void saveContactUs(ContactUs contactUs);

    ContactUs getContactUs();

    void saveMessage(Message message);

    int findMessageTotal(Map<String, Object> map);

    List<Message> getMessageList(Map<String, Object> map);

    void deleteMessage(Integer id);

    User getUserByUserName(String userName);
}
