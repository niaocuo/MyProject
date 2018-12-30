package com.hang.up.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hang.up.model.*;
import com.hang.up.service.ManagerService;
import com.hang.up.util.BaseResult;
import com.hang.up.util.Page;
import com.hang.up.util.ResultCode;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by 顺 on 2018/12/8.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @RequestMapping(value = "/uploadPicture")
    @ResponseBody
    public BaseResult uploadPicture(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        BaseResult baseResult = new BaseResult();
        // MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
        if (!file.isEmpty()) {
            String type = file.getOriginalFilename().substring(
                    file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
            String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
            String path = request.getSession().getServletContext()
                    .getRealPath("/upload/" + filename);// 存放位置
            File destFile = new File(path);
            try {
                // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
            } catch (IOException e) {
                e.printStackTrace();
                return new BaseResult(ResultCode.UNKNOWN_ERROR);
            }
            baseResult.setData("/upload/" + filename);
            return baseResult;
        }
        return new BaseResult(ResultCode.UNKNOWN_ERROR);
    }

    @RequestMapping(value = "/saveHome")
    @ResponseBody
    public BaseResult saveHome(String homeDate){
        BaseResult baseResult = new BaseResult();
        List<Home> homeList = JSON.parseObject(homeDate,new TypeReference<List<Home>>(){});
        managerService.saveHome(homeList);
        return baseResult;
    }

    @RequestMapping(value = "/getHomeAll")
    @ResponseBody
    public List<Home> getHomeAll(){
        return managerService.getHomeAll();
    }

    @RequestMapping(value = "/deleteHomeById")
    @ResponseBody
    public BaseResult deleteHomeById(Integer id){
        BaseResult baseResult = new BaseResult();
        managerService.deleteHomeById(id);
        return baseResult;
    }

    @RequestMapping(value = "/getProductPage")
    @ResponseBody
    public Page getProductPage(@RequestBody Page page){
       return managerService.getProductPage(page);
    }


    @RequestMapping(value = "/getProductById")
    @ResponseBody
    public Product getProductById(Integer id){
        return managerService.getProductById(id);
    }


    @RequestMapping(value = "/addProduct")
    @ResponseBody
    public BaseResult addProduct(String image, String product){
        BaseResult baseResult = new BaseResult();
        Image imageObj = JSON.parseObject(image,Image.class);
        Product productObj = JSON.parseObject(product,Product.class);
        managerService.addProduct(productObj,imageObj);
        return baseResult;
    }

    @RequestMapping(value = "/updateProduct")
    @ResponseBody
    public BaseResult updateProduct(String image, String product){
        BaseResult baseResult = new BaseResult();
        Image imageObj = JSON.parseObject(image,Image.class);
        Product productObj = JSON.parseObject(product,Product.class);
        managerService.updateProduct(productObj,imageObj);
        return baseResult;
    }

    @RequestMapping(value = "/deleteProduct")
    @ResponseBody
    public BaseResult deleteProduct(@RequestBody Integer id){
        BaseResult baseResult = new BaseResult();
        managerService.deleteProduct(id);
        return baseResult;
    }

    @RequestMapping(value = "/getInformationPage")
    @ResponseBody
    public Page getInformationPage(@RequestBody Page page){
        return managerService.getInformationPage(page);
    }

    @RequestMapping(value = "/addInformation")
    @ResponseBody
    public BaseResult addInformation(String image, String information){
        BaseResult baseResult = new BaseResult();
        Image imageObj = JSON.parseObject(image,Image.class);
        Information informationObj = JSON.parseObject(information,Information.class);
        managerService.addInformation(informationObj,imageObj);
        return baseResult;
    }

    @RequestMapping(value = "/updateInformation")
    @ResponseBody
    public BaseResult updateInformation(String image, String information){
        BaseResult baseResult = new BaseResult();
        Image imageObj = JSON.parseObject(image,Image.class);
        Information informationObj = JSON.parseObject(information,Information.class);
        managerService.updateInformation(informationObj,imageObj);
        return baseResult;
    }

    @RequestMapping(value = "/getInformationById")
    @ResponseBody
    public Information getInformationById(Integer id){
        return managerService.getInformationById(id);
    }

    @RequestMapping(value = "/deleteInformation")
    @ResponseBody
    public BaseResult deleteInformation(@RequestBody Integer id){
        BaseResult baseResult = new BaseResult();
        managerService.deleteInformation(id);
        return baseResult;
    }

    @RequestMapping(value = "/saveAboutUs")
    @ResponseBody
    public BaseResult saveAboutUs(String aboutUs){
        AboutUs aboutUsObi = JSON.parseObject(aboutUs,AboutUs.class);
        BaseResult baseResult = new BaseResult();
        managerService.saveAboutUs(aboutUsObi);
        return baseResult;
    }

    @RequestMapping(value = "/getAboutUs")
    @ResponseBody
    public AboutUs getAboutUs(){
       return managerService.getAboutUs();
    }


    @RequestMapping(value = "/saveContactUs")
    @ResponseBody
    public BaseResult saveContactUs(@RequestBody ContactUs contactUs){
        BaseResult baseResult = new BaseResult();
        managerService.saveContactUs(contactUs);
        return baseResult;
    }

    @RequestMapping(value = "/getContactUs")
    @ResponseBody
    public ContactUs getContactUs(){
        return managerService.getContactUs();
    }

    @RequestMapping(value = "/getMessagePage")
    @ResponseBody
    public Page getMessagePage(@RequestBody Page page){
        return managerService.getMessagePage(page);
    }

    @RequestMapping(value = "/deleteMessage")
    @ResponseBody
    public BaseResult deleteMessage(@RequestBody Integer id){
        BaseResult baseResult = new BaseResult();
        managerService.deleteMessage(id);
        return baseResult;
    }


}
