package com.mosaiker.filegod.controller;

import com.alibaba.fastjson.JSONObject;
import com.mosaiker.filegod.entity.Link;
import com.mosaiker.filegod.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LinkController {
    @Autowired
    LinkService linkService;

    @RequestMapping(value = "/links/upload", method = RequestMethod.POST)
    public String uploadLink(@RequestBody JSONObject params) {
        String title = params.getString("title");
        String author = params.getString("author");
        String description = params.getString("description");
        String link = params.getString("link");
        Link myLink = new Link(title, author, "", description, link);
        System.out.println("title:"+title+"author:"+author);
        linkService.uploadLink(myLink);
        return "upload link ok";
    }

    @RequestMapping(value = "/contents/get", method = RequestMethod.GET)
    public JSONObject getAllContents() {
        JSONObject result = new JSONObject();
        List<Link> contents = linkService.getAllLinks();
        result.put("contents", contents);
        return result;
    }
}
