package com.mosaiker.filegod.service;

import com.alibaba.fastjson.JSONObject;
import com.mosaiker.filegod.entity.Link;

import java.util.List;

public interface LinkService {
    Link getLinkById(long id);

    List<Link> getAllLinks();

    void uploadLink(Link link);

}
