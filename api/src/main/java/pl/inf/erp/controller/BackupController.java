package pl.inf.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.inf.erp.service.BackupService;

import java.io.FileNotFoundException;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/api/")
@Slf4j
public class BackupController {

    private BackupService backupService;

    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @PostMapping(value = "/import")
    public ResponseEntity importBackup(@RequestParam("backup") MultipartFile backup) {
        try {
            backupService.restore(backup);
            return ResponseEntity.ok("Restored");
        } catch (RuntimeException e) {
            log.error("Error during restore process...", e);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity exportBackup() throws FileNotFoundException {
        try{
            InputStreamResource backup = backupService.makeBackup();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + backup.getFilename())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(backup);
        }catch (RuntimeException e){
            log.error("Error during preparing backup process...", e);
            return ResponseEntity.noContent().build();
        }
    }

}
