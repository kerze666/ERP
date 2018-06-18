package pl.inf.erp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.inf.erp.model.Invoice;
import pl.inf.erp.repository.InvoiceRepository;
import pl.inf.erp.utils.DateUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@Slf4j
public class BackupService {

    private InvoiceRepository serviceRepository;

    private ObjectMapper objectMapper;

    private String pathToTemp;

    public BackupService(InvoiceRepository serviceRepository, ObjectMapper objectMapper, @Value("${temp.directory}") String pathToTemp) {
        this.serviceRepository = serviceRepository;
        this.objectMapper = objectMapper;
        this.pathToTemp = pathToTemp;
    }

    public File makeBackup() {
        try {
            String json = objectMapper.writeValueAsString(serviceRepository.findAll());
            Path path = Files.createFile(Paths.get(String.format("%s/backup_%s.txt", pathToTemp, DateUtils.currentData())));
            Files.write(path, json.getBytes(), StandardOpenOption.APPEND);
            return path.toFile();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void restore(MultipartFile file) {
        try {
            List<Invoice> data = objectMapper.readValue(file.getBytes(), objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Invoice.class));
            serviceRepository.deleteAll();
            serviceRepository.saveAll(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
