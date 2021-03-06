package com.ccc.my.project.web.ui.controller;

import com.ccc.my.project.commons.utils.HttpClientUtils;
import com.ccc.my.project.commons.utils.MapperUtils;
import com.ccc.my.project.web.ui.api.API;
import com.ccc.my.project.web.ui.api.ContentsApi;
import com.ccc.my.project.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author ccc
 * @create 2019-11-29-15:32
 */
@Controller
public class indexController {

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        //请求幻灯片轮播
        requetsContentsPPT(model);
        return "index";
    }

    /**
     * 请求首页幻灯片
     * @param model
     */
   private void requetsContentsPPT(Model model) {
    List<TbContent> tbContents = null;
    String result = HttpClientUtils.doGet(API.API_CONTENTS_PPT);
       try {
           tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
       } catch (Exception e) {
           e.printStackTrace();
       }
       model.addAttribute("ppt",tbContents);
   }

}
