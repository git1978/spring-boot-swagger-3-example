package com.bezkoder.spring.swagger.service;

import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.swagger.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FileService {

	@Value("${springdoc.path.file}")
	private String filePath; // This will be injected from application.properties
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);


    public List<Product> readFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = null;
        String fullPath = filePath + File.separator + "products.json";
        logger.info("Démarrage du traitement...");
        logger.info("path file : {}", filePath);
        try {
            // Assuming the JSON file is named "products.json"
        	File file = new File(fullPath);
            Product[] productsArray = objectMapper.readValue(file, Product[].class);
            products = Arrays.asList(productsArray);
            logger.debug("Traitement en cours...");
        } catch (IOException e) {
            logger.error("Erreur lors du traitement", e);
        }

        return products;
    }
}