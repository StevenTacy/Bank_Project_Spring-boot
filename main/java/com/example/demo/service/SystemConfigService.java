package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import com.example.demo.entity.SystemConfiguration;
import com.example.demo.repository.SystemConfigRepository;



@Service
public class SystemConfigService {
	@Autowired
    private final SystemConfigRepository systemConfigRepository;
	
  public SystemConfigService(SystemConfigRepository systemConfigRepository) {
    	this.systemConfigRepository = systemConfigRepository;
   
    }
  
    @EventListener(ApplicationReadyEvent.class)
    public void reloadAllConfig(){
        List<SystemConfiguration> sysConf= systemConfigRepository.findAll();
        for (SystemConfiguration sysConf2 : sysConf) {
            String key=sysConf2.getConfigKey();
            String value=sysConf2.getConfigValue();
            SystemConfigUtil.systemConfMap.put(key, value);
        }
     
    }
 
}
