package pl.inf.erp.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.inf.erp.repository.ServiceRepository;

import java.io.File;

@Service
@Slf4j
public class BackupService {

    private ServiceRepository serviceRepository;

//    private CostInvoiceRepository costInvoiceRepository;

    public File makeBackup(){
        Gson gson = new Gson();
        String json = gson.toJson(serviceRepository.findAll());
//        String json2 = gson.toJson(costInvoiceRepository.findAll());

        return null;
    }

    public void restore(File file){
//        Files.
    }

}
