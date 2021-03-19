package com.van.SmartHome.controller;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.van.SmartHome.dao.FileDAO;
import com.van.SmartHome.dao.UserPicureDAO;
import com.van.SmartHome.model.BasicResult;
import com.van.SmartHome.model.UserPicture;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.Map;

import static com.van.SmartHome.mongodb.DBNames.USE_DB;


@Slf4j
@RestController
@RequestMapping("api")
public class FileController {
    private final String dbName =USE_DB;

    @Autowired
    FileDAO fileDAO;

    @Autowired
    UserPicureDAO userPicureDAO;


    /**
     * 上传文件接口 主要是图片
     * @param filename
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/file")
    public BasicResult uploadFile(

            @RequestParam(value = "filename",required = false,defaultValue = "picture") String filename,
            @RequestParam(value = "uid",required = true) ObjectId uid,
            @RequestParam  MultipartFile file)throws Exception {
        log.debug("！！！form file uploaded");



        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") );
        log.info("!!!file.getName():{},suffix:{}",file.getOriginalFilename(),suffix);
        Map<String, Object> lhm = fileDAO.addFile(dbName, filename, suffix,file.getInputStream());
        log.debug("!!!form file upload result {}", lhm);
        //图片上传完了要保存是谁上传的
        UserPicture userPicture = new UserPicture();
        userPicture.setUid(uid);
        userPicture.setFileId(new ObjectId(lhm.get("_id").toString()));
        //设置时间
        Instant now = Instant.now();
        userPicture.setUploadTime(now.getEpochSecond());

        userPicureDAO.addUserPicture(dbName,userPicture);

        return BasicResult.of(lhm);
    }

    @GetMapping(value = "/file", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public Resource downloadFile(
            HttpServletResponse response,
            @RequestParam(value = "fileId",required = true) ObjectId fileId) {

        GridFSFile file = fileDAO.getFile(dbName, fileId);

        if (file.getFilename() != null) {
            ContentDisposition disposition =
                    ContentDisposition.builder("attachment")
                            .filename(file.getFilename()+ file.getMetadata().get("suffix"),
                                    Charset.forName("UTF-8"))
                            .build();
            response.setHeader("Content-Disposition", disposition.toString());
            response.setContentType("application/octet-stream");

        }
        return fileDAO.getResource(dbName, file);
    }


}
