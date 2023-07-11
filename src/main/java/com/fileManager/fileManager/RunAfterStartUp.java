package com.fileManager.fileManager;

import com.fileManager.fileManager.model.DocumentTypeEntity;
import com.fileManager.fileManager.repositories.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RunAfterStartUp {
    @Autowired
    private DocumentTypeRepository documentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        if (documentRepository.findAll().size()<1){
            List<DocumentTypeEntity> ld=new ArrayList<>();
            List<String> ls = Arrays.asList("Sample Documents","Select","BEEKEEPER","Checkpoint Goals","Day-1 - Non_Disclosure","Day-1 - Skills Tracker","Email Confirmation","On boarding Checklist","Prudential Delivery - Digital KT Plan","Prudential New colleague","Training");
            AtomicInteger ia=new AtomicInteger(0);
            ls.forEach(a->{
                ld.add(new DocumentTypeEntity(ia.getAndIncrement(),a));
            });
            documentRepository.saveAll(ld);
            System.out.println("doctype size was 0 ,now added all type");
        } else {
            System.out.println("doctype size is "+documentRepository.findAll().size());
        }
    }
}