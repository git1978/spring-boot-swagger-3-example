package com.bezkoder.spring.swagger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.swagger.model.Product;
import com.bezkoder.spring.swagger.service.FileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "File", description = "File management APIs")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FileController {
  
  @Autowired
  private FileService fileService;

  @Operation(summary = "Get file content", tags = { "File", "get" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "File content retrieved successfully", content = {
          @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) }) })
  @GetMapping("/file")
  public ResponseEntity<List<Product>> getFileContent() {
    try {
      List<Product> fileList = fileService.readFile();
      return new ResponseEntity<List<Product>>(fileList, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
