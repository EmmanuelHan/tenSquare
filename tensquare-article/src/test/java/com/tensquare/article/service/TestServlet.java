package com.tensquare.article.service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class TestServlet extends HttpServlet {
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        File f = new File("opt.txt");
        RandomAccessFile raf = new RandomAccessFile(f, "rw");
        FileChannel fc = raf.getChannel();
        FileLock fl = null;
        Thread t = Thread.currentThread();
        System.out.println(t.getId() + " " + t.getName());
        InetAddress address = InetAddress.getLocalHost();
        try {
            try {
                fl = fc.tryLock();
                if (fl == null) {
                    System.out.println(address.getHostAddress() + " wait 5 sec for reason (null)!");
                    throw new OverlappingFileLockException();
                }
            } catch (OverlappingFileLockException e) {
                System.out.println(address.getHostAddress() + " wait 5 sec once!");
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    fl = fc.tryLock();
                    if (fl == null) {
                        System.out.println(address.getHostAddress() + " wait 5 sec for reason (null)!");
                        throw new OverlappingFileLockException();
                    }
                } catch (OverlappingFileLockException oe) {
                    System.out.println(address.getHostAddress() + " cannot get file lock!");
                    throw new OverlappingFileLockException();
                }
            }
            if (fl != null) {
                System.out.println(address.getHostAddress() + " start write!");
                fc.position(fc.size());
                fc.write(ByteBuffer
                        .wrap((address.getHostAddress() + " =============1=============== /r/n")
                                .getBytes()));
                System.out.println(address.getHostAddress() + " stop write!");
            }
        } catch (Exception e) {
            System.out.println(address.getHostAddress() + " exception or cannot get file lock!");
            response.getWriter().write(address.getHostAddress()
                    + " exception or cannot get file lock!");
            return;
        } finally {
            if (null != fl && fl.isValid()) {
                fl.release();
                System.out.println(address.getHostAddress() + " release filelock");
            }
            fc.close();
            raf.close();
        }
    }
}