package top.zhacker.gateway.resource.filter;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;


// 启动时加载groovy filter
@Component
@Slf4j
public class GroovyLoadLineRunner implements CommandLineRunner {
 
    @Value("${zuul.groovy.path}")
    private String groovyPath;
 
    @Override
    public void run(String... args) throws Exception {
        MonitoringHelper.initMocks();
        FilterLoader.getInstance().setCompiler(new GroovyCompiler());
        try {
            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
            log.info(groovyPath);
            Files.createDirectories(Paths.get(groovyPath));
            Files.createDirectories(Paths.get(groovyPath, "pre"));
            Files.createDirectories(Paths.get(groovyPath, "post"));
            FilterFileManager.init(1, groovyPath + "pre", groovyPath + "post");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}