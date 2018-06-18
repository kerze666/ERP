package pl.inf.erp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.inf.erp.service.BackupService;

import java.io.File;

@RestController
@RequestMapping(value = "/v1/api/")
public class BackupController {

    private BackupService backupService;

    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @GetMapping(value = "/import" )
    public ResponseEntity importBackup(@RequestParam("backup") MultipartFile backup){
        return null;

    }

    @PostMapping(value = "/export")
    public ResponseEntity exportBackup(){
        File file = backupService.makeBackup();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName()+ "\"")
                .body(file);
    }

}
