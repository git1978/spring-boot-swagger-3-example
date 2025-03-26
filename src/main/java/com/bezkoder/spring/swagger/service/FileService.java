package com.bezkoder.spring.swagger.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.bezkoder.spring.swagger.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class FileService {

	@Value("${springdoc.path.file}")
	private String filePath; // This will be injected from application.properties
	
	@Value("${azure.storage.account.name}")
    private String accountName;

    @Value("${azure.storage.account.key}")
    private String accountKey;

    @Value("${azure.storage.container.name}")
    private String containerName;

    @Value("${azure.storage.blob.name}")
    private String blobName;

    private BlobServiceClient blobServiceClient;
    private BlobContainerClient containerClient;
	
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    
    
    @PostConstruct
    public void init() {
        String connectionString = String.format(
            "DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net",
            accountName, accountKey
        );

        this.blobServiceClient = new BlobServiceClientBuilder()
            .connectionString(connectionString)
            .buildClient();

        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
        logger.info("Azure Blob Storage initialized successfully.");
    }


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
    
    public List<Product> readFileFromBlob() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = null;

        try {
            // Get the blob client for the specific blob in the container (products.json)
            BlobClient blobClient = containerClient.getBlobClient(blobName);

            // Download the blob's content into a byte array
            byte[] content = blobClient.downloadContent().toBytes();

            // Convert byte array to InputStream for Jackson ObjectMapper
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content);

            // Deserialize JSON data to List<Product>
            Product[] productsArray = objectMapper.readValue(byteArrayInputStream, Product[].class);
            products = Arrays.asList(productsArray);

            logger.debug("Blob file processed successfully.");
        } catch (IOException e) {
            logger.error("Error reading blob file", e);
        }

        return products;
    }
}