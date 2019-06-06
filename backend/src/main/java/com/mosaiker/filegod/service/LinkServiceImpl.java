package com.mosaiker.filegod.service;

import com.mosaiker.filegod.entity.Link;
import com.mosaiker.filegod.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService{
    @Autowired
    public LinkRepository linkRepository;

    @Override
    public Link getLinkById(long id) {
        return linkRepository.findById(id).get();
    }
    @Override
    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }
    @Override
    public void uploadLink(Link link) {
        linkRepository.save(link);
    }

}
