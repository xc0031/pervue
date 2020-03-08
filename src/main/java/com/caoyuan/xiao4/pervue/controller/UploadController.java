package com.caoyuan.xiao4.pervue.controller;

import com.caoyuan.xiao4.pervue.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        //文件上传
        if (file != null && !file.isEmpty()) {
            //获取文件的原始名称
            String fileOriginalFilename = file.getOriginalFilename();
            //拼接一个新名称
            String fileName = UUID.randomUUID() + "_" + fileOriginalFilename;
            //保存的文件地址
            File destFile = new File("D:\\pic", fileName);

            try {
                //执行保存
                file.transferTo(destFile);
                //头像保存的地址
                String userface = "http://localhost/pic/" + fileName;
                return Result.success(userface);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.error(404, "上传图片失败");
    }
}
