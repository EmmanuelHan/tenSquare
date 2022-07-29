package com.tensquare.article.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.StandardCharsets;

public class FileTest {


    public static void main(String[] args) {

        new FileWrite("1111").run();
        new FileWrite("2222").run();

    }

}
class FileWrite implements Runnable{

    private String name;

    public FileWrite(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void test() throws IOException {
        File f = new File("opt.txt");
        RandomAccessFile raf = new RandomAccessFile(f, "rw");
        FileOutputStream fileOutputStream = null;
        BufferedWriter br = null;
        FileChannel fc = raf.getChannel();
        FileLock fl = null;
        try {
            fileOutputStream = new FileOutputStream(f, true);
            br = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            try {
                fl = fc.tryLock();
                if (fl == null) {
                    System.out.println(" wait 5 sec for reason (null)!");
                    throw new OverlappingFileLockException();
                }
                for (int i = 0; i < 10; i++) {
                    br.write(name + "\n");
                    br.flush();
                }
            } catch (OverlappingFileLockException e) {
                System.out.println(" wait 5 sec once!");
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    fl = fc.tryLock();
                    if (fl == null) {
                        System.out.println(" wait 5 sec for reason (null)!");
                        throw new OverlappingFileLockException();
                    }
                } catch (OverlappingFileLockException oe) {
                    System.out.println(" cannot get file lock!");
                    throw new OverlappingFileLockException();
                }
            }
            if (fl != null) {
                System.out.println(" start write!");
                fc.position(fc.size());
                fc.write(ByteBuffer
                        .wrap((" =============1=============== /r/n")
                                .getBytes()));
                System.out.println(" stop write!");
            }
        } catch (Exception e) {
            System.out.println(" exception or cannot get file lock!");
            return;
        } finally {
            if (null != fl && fl.isValid()) {
                fl.release();
                System.out.println(" release filelock");
            }
            br.close();
            fileOutputStream.close();
            fc.close();
            raf.close();
        }
    }

}