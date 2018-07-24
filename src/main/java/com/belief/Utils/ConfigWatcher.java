package com.belief.Utils;

import com.belief.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;

@Repository
public class ConfigWatcher {

    private static final Logger logger = LoggerFactory.getLogger(ConfigWatcher.class);

    private static WatchService watchService;

    private ConfigurableWebApplicationContext wac;

    @PostConstruct
    public void init() {
        logger.info("启动配置文件监控器");
        try {
            watchService = FileSystems.getDefault().newWatchService();
            URL url = ConfigWatcher.class.getResource("/dataSource/");
            Path path = Paths.get(url.toURI());
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (Exception e1) {
            logger.error("启动配置文件监听器失败");
            e1.printStackTrace();
        }

        /**
         * 启动监控线程
         */
        Thread watchThread = new Thread(new WatchThread());
        watchThread.setDaemon(true);
        watchThread.start();

        /**注册关闭钩子*/
        Thread hook = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    watchService.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(hook);
    }

    public class WatchThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    // 尝试获取监控池的变化，如果没有则一直等待
                    WatchKey watchKey = watchService.take();
                    for (WatchEvent<?> event : watchKey.pollEvents()) {
                        String editFileName = event.context().toString();
                        logger.info(editFileName);
                        /**
                         * 重新加载配置
                         */
                        UserController refresh = new UserController();
                        refresh.regresh();
                    }
                    watchKey.reset();//完成一次监控就需要重置监控器一次
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}