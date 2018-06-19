package pl.inf.erp.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.inf.erp.service.BackupService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/api/")
public class BackupController {

    private BackupService backupService;

    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @PostMapping(value = "/import" )
    public ResponseEntity importBackup(@RequestParam("backup") MultipartFile backup){
        backupService.restore(backup);
        return ResponseEntity.ok("Restored");

    }

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity exportBackup() throws FileNotFoundException {
        File file = backupService.makeBackup();
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }

}
