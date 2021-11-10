package com.tensquare.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author EmmanuelHan
 */
public class StringUtil {

    public static final Integer START_PAGE = 1;

    public static final Integer PAGE_SIZE = 15;

    public static final String TOKEN_KEY = "tensquare";


    /**
     * 将异常日志转换为字符串
     *
     * @param e
     * @return
     */
    public static String getException(Exception e) {
        try (Writer writer = new StringWriter();
             PrintWriter printWriter = new PrintWriter(writer)) {
            e.printStackTrace(printWriter);
            return writer.toString();
        } catch (IOException e1) {
            getException(e1);
        }
        return "";
    }


}
