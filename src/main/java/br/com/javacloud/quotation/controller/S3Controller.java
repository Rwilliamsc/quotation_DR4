package br.com.javacloud.quotation.controller;

import br.com.javacloud.quotation.model.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping(value = "/upload", consumes = "multipart/form-data", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "File are Sent to the client AWS S3", responses = {
            @ApiResponse(responseCode = "200", description = "Sed File") })
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "plan/text");
        return ResponseEntity.status(HTTP_OK).headers(headers).body(s3Service.saveFile(file));
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        byte[] bytes = s3Service.downloadFile(filename);
        return ResponseEntity.status(HTTP_OK).headers(headers).body(bytes);
    }

    @DeleteMapping("/{filename}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "delete file"),
            @ApiResponse(responseCode = "404", description = "file not found") })
    public String deleteFile(@PathVariable("filename") String filename) {
        return s3Service.deleteFile(filename);
    }

    @GetMapping("/list")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get all files") })
    public List<String> getAllFiles() {
        return s3Service.listAllFiles();
    }
}
