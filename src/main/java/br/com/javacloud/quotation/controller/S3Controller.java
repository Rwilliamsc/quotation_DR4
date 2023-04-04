package br.com.javacloud.quotation.controller;

import br.com.javacloud.quotation.model.service.S3Service;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

@RestController
@RequestMapping("/api/files")
public class S3Controller {
    @Autowired
    private S3Service s3Service;;



    @ApiParam(allowMultiple=true)
    @PostMapping(value = "/upload")
    @ApiOperation(value = "Upload file to S3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "File to upload", dataType = "file", paramType = "form", required = true)
    })
    public String upload(@RequestParam("file") MultipartFile file){
        return s3Service.saveFile(file);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename){
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename="+filename);
        byte[] bytes = s3Service.downloadFile(filename);
        return  ResponseEntity.status(HTTP_OK).headers(headers).body(bytes);
    }


    @DeleteMapping("/{filename}")
    public  String deleteFile(@PathVariable("filename") String filename){
        return s3Service.deleteFile(filename);
    }

    @GetMapping("/list")
    public List<String> getAllFiles(){

        return s3Service.listAllFiles();

    }
}
