package com.mosaiker.filegod.controller;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mosaiker.filegod.entity.Link;
import com.mosaiker.filegod.service.LinkService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@Controller
public class FileController {
    @Autowired
    private LinkService linkService;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFSBucket gridFSBucket;

    @PostMapping("/files/upload")
    @ResponseBody
    public JSONObject uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("author") String author,
                                 @RequestParam("title") String title,@RequestParam("description") String description) throws IOException, ServletException {
        // 获得提交的文件名
        String fileName = file.getOriginalFilename();
        // 获取文件输入流
        InputStream ins = file.getInputStream();
        // 获取文件类型
        String contentType = file.getContentType();
        // 将文件存储到mongodb中
        ObjectId objectId = gridFsTemplate.store(ins, fileName, contentType);
        JSONObject result = new JSONObject();
        String file_id = objectId.toString();
        Link myLink = new Link(title, author, file_id, description, "");
        linkService.uploadLink(myLink);
        result.put("file_id", objectId);
        return result;
    }

    @RequestMapping(value = "/files/download", method = RequestMethod.GET)
    public void downloadFile(@RequestParam(name = "file_id") String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Query query = Query.query(Criteria.where("_id").is(fileId));
        // 查询单个文件
        System.out.println("querying..."+fileId);
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        if (gridFSFile == null) {
            return;
        }
        System.out.println("downloading...");
        String fileName = gridFSFile.getFilename().replace(",", "");
        String contentType = gridFSFile.getMetadata().get("_contentType").toString();

        // 通知浏览器进行文件下载
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource resource = new GridFsResource(gridFSFile, gridFSDownloadStream);

        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = resource.getInputStream();
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
