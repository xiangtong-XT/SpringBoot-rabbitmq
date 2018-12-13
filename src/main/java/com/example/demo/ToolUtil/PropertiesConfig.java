package com.example.demo.ToolUtil;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static jdk.internal.util.xml.XMLStreamWriter.DEFAULT_ENCODING;


@Configuration
@Component
public class PropertiesConfig {

    private static final String[] properties = {"/application.properties"};
    private static Properties props;
    private static Map<Object, Object> propertiesMap = new HashMap();

    public PropertiesConfig() {
        try {
            for (String propertie : properties) {
                Resource resource = new ClassPathResource(propertie);
                if(null != resource && resource.exists()){
                    EncodedResource encodeResource = new EncodedResource(resource, DEFAULT_ENCODING);
                    props = PropertiesLoaderUtils.loadProperties(encodeResource);
                    propertiesMap.putAll(props);
                }
            }
            if(null != propertiesMap){
                props.clear();
                for (Map.Entry<Object, Object> item : propertiesMap.entrySet()) {
                    if(!StringUtils.isEmpty(item.getKey())){
                        props.setProperty(item.getKey().toString(),StringUtils.isEmpty(item.getValue()) ? "" : item.getValue().toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return props == null ? null : props.getProperty(key);

    }
}
