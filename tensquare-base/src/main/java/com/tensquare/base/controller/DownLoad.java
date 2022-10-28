package com.tensquare.base.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tensquare.base.entity.City;
import com.tensquare.base.service.ICityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.zip.ZipEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName DownLoad
 * @Author SEN
 * @Date 2020/2/10 0010 17:49
 * @Version 1.0
 * @From <a href="http://events.jianshu.io/p/c41e571acc43">...</a>
 **/

@Slf4j
@Controller
@RequestMapping("/download")
public class DownLoad {
    // 获取当前系统的临时目录
    private static final String FilePath = System.getProperty("java.io.tmpdir") + File.separator;

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(20,
            20, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Resource
    private ICityService cityService;

    @Resource
    private HttpServletResponse httpServletResponse;

    @GetMapping("/execute")
    public void execute(HttpServletResponse response) {
        //生成的ZIP文件名为Demo.zip
        String tmpFileName = "Demo.zip";
        // zip文件路径
        String strZipPath = FilePath + tmpFileName;
        String filename = FilePath + generateRandomFilename() + ".xlsx";
        File newFile = creatFile(filename);
        try {
            long start = System.currentTimeMillis();
            //创建zip输出流
            ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(Paths.get(strZipPath)));
//            ExcelWriter excelWriter = EasyExcel.write(filename).excelType(ExcelTypeEnum.CSV).charset(Charset.forName("GBK")).build();
            // 这里注意 如果同一个sheet只要创建一次
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            long begin = System.currentTimeMillis();
//            CompletableFuture<Integer> id = CompletableFuture.supplyAsync(() -> {
//                QueryWrapper<City> queryWrapper = new QueryWrapper<>();
//                queryWrapper.lt("id", 650000);
//                List<City> list1 = cityService.list(queryWrapper);
//                writeExcel(newFile, City.class, list1, ExcelTypeEnum.XLSX);
//                excelWriter.write(list1, writeSheet);
//                return 1;
//            }, THREAD_POOL_EXECUTOR);

            QueryWrapper<City> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.gt("id", 1530502);//.lt("id", 1000000);
            List<City> list2 = cityService.list(queryWrapper1);

            writeExcel(newFile, City.class, list2, ExcelTypeEnum.XLSX);

            long end = System.currentTimeMillis();
            log.info("写入花费时间{}ms", end - begin);
            log.info("导出花费时间{}ms", end - start);

            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(newFile);
            out.putNextEntry(new ZipEntry(newFile.getName()));
            int len;
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            fis.close();
            out.close();
            //下载zip文件
            this.downFile(response, tmpFileName, filename);
        } catch (Exception e) {
            // 下载失败删除生成的文件
            log.error("文件下载出错", e);
        } finally {
            deleteFile(filename);
        }
    }

    /**
     * 文件下载
     */
    private void downFile(HttpServletResponse response, String str, String filePaths) {
        try {
            String path = FilePath + str;
            File file = new File(path);
            if (file.exists()) {
                InputStream ins = Files.newInputStream(Paths.get(path));
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                response.setContentType("application/x-download");// 设置response内容的类型
                response.setHeader(
                        "Content-disposition",
                        "attachment;filename="
                                + URLEncoder.encode(str, "UTF-8"));// 设置头部信息
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                // 开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();// 这里一定要调用flush()方法
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            }
        } catch (IOException e) {
            log.error("文件下载出错", e);
        } finally {
            deleteFile(filePaths);
        }
    }

    //创建文件File对象
    private File creatFile(String filePath) {
        return new File(filePath);
    }

    //生成随机文件名
    public String generateRandomFilename() {
        String RandomFilename = "";
        Random rand = new Random();//生成随机数
        int random = rand.nextInt();
        Calendar calCurrent = Calendar.getInstance();
        int intDay = calCurrent.get(Calendar.DATE);
        int intMonth = calCurrent.get(Calendar.MONTH) + 1;
        int intYear = calCurrent.get(Calendar.YEAR);
        String now = intYear + "_" + intMonth + "_" + intDay + "_";

        RandomFilename = now + (random > 0 ? random : (-1) * random);
        return RandomFilename;
    }

    //删除文件
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * @Title: writeExcel
     * @Description: 写入excel文件到输出流web端
     */
    private void writeExcel(OutputStream outputStream, Class<?> clazz, List<?> datalist, ExcelTypeEnum excelType, String sheetName) throws IOException {
        EasyExcel.write(outputStream, clazz).excelType(excelType).sheet(sheetName == null ? "sheet1" : sheetName).doWrite(datalist);
        outputStream.flush();
    }

    /**
     * @Title: writeExcel
     * @Description: 写入excel到本地路径
     */
    private void writeExcel(File newFile, Class<?> clazz, List<?> datalist, ExcelTypeEnum excelType) {
        EasyExcel.write(newFile, clazz).excelType(excelType).sheet("sheet1").doWrite(datalist);
    }

    /**
     * @Title: readExcel
     * @Description: 读取excel内容（从输入流）
     */
    private List<?> readExcel(InputStream inputStream, Class<?> clazz, ReadListener<?> listener) {
        return EasyExcel.read(inputStream, clazz, listener).sheet().doReadSync();
    }

}


