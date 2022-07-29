package com.tensquare.article;

import com.tensquare.article.utils.DateUtils;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

public class Test2 {


    private static Map<Date, Date> dateMap = new ConcurrentSkipListMap<>();

    public static void dateHandle(Date startTime, Date endTime) {
        if (startTime.after(endTime)) return;
        if (MapUtils.isEmpty(dateMap)) {
            dateMap.put(startTime, endTime);
            return;
        }
        //ConcurrentSkipListMap能自动根据Key排序，只需要合并交集
        //1、有交集则合并
        //2、没有交集则添加
        Iterator<Map.Entry<Date, Date>> iterator = dateMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Date, Date> entry = iterator.next();
            Date start = entry.getKey();
            Date end = entry.getValue();
            if (startTime.before(start) && (endTime.after(start) || endTime.equals(start)) && (endTime.before(end) || endTime.equals(end))) {
                iterator.remove();
                dateMap.put(startTime, end);
            } else if (startTime.before(start) && endTime.after(end)) {
                iterator.remove();
                dateMap.put(startTime, endTime);
            } else if (endTime.after(end) && (startTime.after(start) || startTime.equals(start)) && (startTime.before(end) || startTime.equals(end))) {
                iterator.remove();
                dateMap.put(start, endTime);
            } else if (endTime.before(start) || startTime.after(end)) {
                if (!iterator.hasNext())
                    dateMap.put(startTime, endTime);
            }
        }
    }

    //测试数据
    public static void main(String[] args) {
        ArrayList<HashMap<Date, Date>> objects = new ArrayList<>();
        HashMap<Date, Date> dateDateHashMap = new HashMap<>();
        Date startTime = DateUtils.parse("2020-12-21 08:00:00", "yyyy-MM-dd hh:mm:ss");
        Date endTime = DateUtils.parse("2020-12-31 18:00:00", "yyyy-MM-dd hh:mm:ss");
        dateDateHashMap.put(startTime, endTime);
        objects.add(dateDateHashMap);

        HashMap<Date, Date> dateDateHashMap2 = new HashMap<>();
        Date startTime2 = DateUtils.parse("2021-01-01 00:00:00", "yyyy-MM-dd hh:mm:ss");
        Date endTime2 = DateUtils.parse("2021-01-31 18:00:00", "yyyy-MM-dd hh:mm:ss");
        dateDateHashMap2.put(startTime2, endTime2);
        objects.add(dateDateHashMap2);


        HashMap<Date, Date> dateDateHashMap3 = new HashMap<>();
        Date startTime3 = DateUtils.parse("2021-02-01 08:00:00", "yyyy-MM-dd hh:mm:ss");
        Date endTime3 = DateUtils.parse("2021-02-28 18:00:00", "yyyy-MM-dd hh:mm:ss");
        dateDateHashMap3.put(startTime3, endTime3);
        objects.add(dateDateHashMap3);


        HashMap<Date, Date> dateDateHashMap4 = new HashMap<>();
        Date startTime4 = DateUtils.parse("2021-03-01 08:00:00", "yyyy-MM-dd hh:mm:ss");
        Date endTime4 = DateUtils.parse("2021-03-31 18:00:00", "yyyy-MM-dd hh:mm:ss");
        dateDateHashMap4.put(startTime4, endTime4);
        objects.add(dateDateHashMap4);


        HashMap<Date, Date> dateDateHashMap5 = new HashMap<>();
        Date startTime5 = DateUtils.parse("2021-03-01 08:00:00", "yyyy-MM-dd hh:mm:ss");
        Date endTime5 = DateUtils.parse("2021-12-31 18:00:00", "yyyy-MM-dd hh:mm:ss");
        dateDateHashMap5.put(startTime5, endTime5);
        objects.add(dateDateHashMap5);

        HashMap<Date, Date> dateDateHashMap6 = new HashMap<>();
        Date startTime6 = DateUtils.parse("2021-08-01 08:30:00", "yyyy-MM-dd hh:mm:ss");
        Date endTime6 = DateUtils.parse("2021-08-31 19:00:00", "yyyy-MM-dd hh:mm:ss");
        dateDateHashMap6.put(startTime6, endTime6);
        objects.add(dateDateHashMap6);

        HashMap<Date, Date> dateDateHashMap7 = new HashMap<>();
        Date startTime7 = DateUtils.parse("2021-09-01 08:30:00", "yyyy-MM-dd hh:mm:ss");
        Date endTime7 = DateUtils.parse("2021-09-30 18:30:00", "yyyy-MM-dd hh:mm:ss");
        dateDateHashMap7.put(startTime7, endTime7);
        objects.add(dateDateHashMap7);

        HashMap<Date, Date> dateDateHashMap8 = new HashMap<>();
        Date startTime8 = DateUtils.parse("2021-10-01 08:30:00", "yyyy-MM-dd hh:mm:ss");
        Date endTime8 = DateUtils.parse("2021-10-31 18:30:00", "yyyy-MM-dd hh:mm:ss");
        dateDateHashMap8.put(startTime8, endTime8);
        objects.add(dateDateHashMap8);

        HashMap<Date, Date> dateDateHashMap9 = new HashMap<>();
        Date startTime9 = DateUtils.parse("2021-11-01 08:30:00", "yyyy-MM-dd hh:mm:ss");
        Date endTime9 = DateUtils.parse("2021-11-30 18:30:00", "yyyy-MM-dd hh:mm:ss");
        dateDateHashMap9.put(startTime9, endTime9);
        objects.add(dateDateHashMap9);


        for (HashMap<Date, Date> object : objects) {
            Set<Date> dates = object.keySet();
            for (Date date : dates) {
                //end
                Date end = object.get(date);
                dateHandle(date, end);
            }
        }
        int i = 0;
        //去重之后结果

        for (Date date : dateMap.keySet()) {

            //end
            String startT = DateUtils.format(date, "yyyy-MM-dd hh:mm:ss");
            String endT = DateUtils.format(dateMap.get(date), "yyyy-MM-dd hh:mm:ss");

            i = i + 1;
            System.out.println("第" + i + "个时间区间：开始时间" + startT + "----结束时间：" + endT);

        }
        System.out.println("各区间累加合计-----------------");
        //累加时间
        long sumTime = 0L;
        for (Date date : dateMap.keySet()) {
            long end = dateMap.get(date).getTime();
            long start = date.getTime();
            long startEnd = end - start;

            sumTime = sumTime + startEnd;

        }
        //分钟
        long ss = sumTime / 1000;
        long min = sumTime / 1000 / 60;
        long hour = sumTime / 1000 / 60 / 60;
        long day = sumTime / 1000 / 60 / 60 / 24;

        System.out.println("单位--秒---" + ss);
        System.out.println("单位--分钟---" + min);
        System.out.println("单位--小时---" + hour);
        System.out.println("单位--天---" + day);
    }

}
