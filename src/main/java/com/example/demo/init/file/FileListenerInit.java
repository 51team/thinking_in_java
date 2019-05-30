package com.example.demo.init.file;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.logging.Logger;


/**
 * 文件监听器
 *
 * @author
 */
@Component
@SuppressWarnings("deprecation")
public class FileListenerInit   implements CommandLineRunner {

   private static  Log logger =LogFactory.getLog(FileListenerInit.class);
    private FileAlterationMonitor monitor;

    private FileAlterationObserver observer;
    // 服务器 update 以上路径
    private static final String FILE_ROOT = "F:\\FileListener01";


    private static final Long INTERVAL = 500L;




    public FileListenerInit() {

    }

    public FileListenerInit(String path) throws Exception {

        fileObServer(path);
        startService(null);
    }

    public FileListenerInit(String path, Long interval) throws Exception {

        fileObServer(path);
        startService(interval);
    }

    public void fileObServer(final String path) {
        File parentDir = FileUtils.getFile(path);

        setObserver(new FileAlterationObserver(parentDir));
        getObserver().addListener(new FileAlterationListenerAdaptor() {

            @Override
            public void onFileCreate(File file) {

                logger.info("File created: " + file.getName() + "||||filePath"
                        + file.getParent());



            }

            @Override
            public void onFileDelete(File file) {
                logger.info("File deleted: " + file.getName());

            }

            @Override
            public void onDirectoryCreate(File dir) {
                logger.info("Directory created: " + dir.getName());
            }

            @Override
            public void onDirectoryDelete(File dir) {
                logger.info("Directory deleted: " + dir.getName());
            }
        });
    }

    public void startService(Long interval) throws Exception {
        if (interval == null) {
            interval = INTERVAL;
        }
        setMonitor(new FileAlterationMonitor(interval, observer));
        getMonitor().start();
    }

    public void stopService(Long interval) throws Exception {
        if (interval == null) {
            interval = INTERVAL;
        }
        setMonitor(new FileAlterationMonitor(interval, observer));
        getMonitor().stop();
    }

    private FileAlterationMonitor getMonitor() {
        return monitor;
    }

    private void setMonitor(FileAlterationMonitor monitor) {
        this.monitor = monitor;
    }

    private FileAlterationObserver getObserver() {
        return observer;
    }

    private void setObserver(FileAlterationObserver observer) {
        this.observer = observer;
    }

    public static void main(String[] args) {
        try {


            FileListenerInit fileListener = new FileListenerInit(FILE_ROOT);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    @Override
    public void run(String... args) throws Exception {
        logger.info("---------------------启动扫描--------------------");
        FileListenerInit fileListener = new FileListenerInit(FILE_ROOT);

        logger.info("---------------------启动完成--------------------");
    }
}

