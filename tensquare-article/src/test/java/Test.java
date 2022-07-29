import com.tensquare.article.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.MapUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Test {

    public static void main(String[] args) {
//        dateHandle(801, 841);
//        dateHandle(1052, 851);
//        dateHandle(1052, 819);
//        dateHandle(2337, 2339);
//        dateHandle(1321, 1331);
//        dateHandle(1446, 1447);
//        dateHandle(951, 953);
//        dateHandle(941, 942);
//        dateHandle(1220, 1221);
//        dateHandle(1256, 1258);
//        dateHandle(1725, 1726);
//        dateHandle(1258, 1259);
//        dateHandle(857, 900);
//        dateHandle(1819, 1820);
//        dateHandle(1123, 1127);
//        dateHandle(1447, 1718);
//        dateHandle(825, 827);
//        dateHandle(1658, 1700);
//        dateHandle(821, 824);
//        dateHandle(1800, 1801);
//        dateHandle(859, 820);
//        dateHandle(802, 803);
//        dateMap.forEach((key, value) -> System.out.println(key + "--" + value));

        List<TimePeriod> dateList = new ArrayList<>();
        dateList.add(new TimePeriod(DateUtils.parse("08"), DateUtils.parse("12")));
        dateList.add(new TimePeriod(DateUtils.parse("13"), DateUtils.parse("16")));
        dateList.add(new TimePeriod(DateUtils.parse("02"), DateUtils.parse("06")));
        dateList.add(new TimePeriod(DateUtils.parse("10"), DateUtils.parse("15")));
        dateList.add(new TimePeriod(DateUtils.parse("17"), DateUtils.parse("21")));
        dateList.add(new TimePeriod(DateUtils.parse("22"), DateUtils.parse("23")));


        Map<Date, Date> timePeriodsUnion = getTimePeriodsUnion(false, dateList);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd HH:mm");

        timePeriodsUnion.forEach((key, value) -> System.out.println(dateFormat.format(key)+ "--" + dateFormat.format(value)));

    }

    static Map<Integer, Integer> dateMap = new ConcurrentSkipListMap<>();

    public static synchronized void dateHandle(int startTime, int endTime) {
        if (startTime > endTime) return;
        if (MapUtils.isEmpty(dateMap)) {
            dateMap.put(startTime, endTime);
            return;
        }
        //TreeMap能自动根据Key排序，只需要合并交集
        //1、有交集则合并
        //2、没有交集则添加
        Iterator<Map.Entry<Integer, Integer>> iterator = dateMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            Integer start = entry.getKey();
            Integer end = entry.getValue();
            if (end < startTime) {
                dateMap.put(startTime, endTime);
            } else if (start < startTime && startTime < end && end < endTime) {
                iterator.remove();
                dateMap.put(start, endTime);
            } else if (start < startTime && endTime < end) {
//                iterator.remove();
//                dateMap.put(start,end);
            } else if (startTime < start && start < endTime && endTime < end) {
                iterator.remove();
                dateMap.put(startTime, end);
            } else {
                dateMap.put(startTime, endTime);
            }
        }
    }

    /**
     * 获取日期中的最大或最小者
     *
     * @param maxOrMin true 最大值 false最小值
     * @param dates    可变参数
     * @return 最大或最小的日期
     */
    public static Date getExtremumDate(Boolean maxOrMin, Date... dates) {
        List<Date> dates1 = new ArrayList<>(dates.length);
        for (Date date : dates) {
            if (date != null) {
                dates1.add(date);
            }
        }
        if (dates1.size() == 0) {
            return null;
        }
        if (maxOrMin) {
            return Collections.max(dates1);
        } else {
            return Collections.min(dates1);
        }
    }

    /**
     * 多个时间段取并集或交集后的时间段列表
     *
     * @param mixedOrUnion true:交集 | false:并集
     * @param periods      时间段列表
     * @return 时间并集 map beginTime -> endTime
     */
    public static Map<Date, Date> getTimePeriodsUnion(Boolean mixedOrUnion, List<TimePeriod> periods) {
        periods.sort(Comparator.comparing(TimePeriod::getBeginTime));
        Map<Date, Date> dateMap = new TreeMap<>();
        for (TimePeriod period : periods) {
            Date startTime = period.getBeginTime();
            Date endTime = period.getEndTime();
            if (MapUtils.isEmpty(dateMap)) {
                dateMap.put(startTime, endTime);
            }
            Map<Date, Date> tempMap = new TreeMap<>();
            //TreeMap能自动根据Key排序，只需要合并交集
            //1.有交集则合并或取交集
            //2.没有交集则添加
            Iterator<Map.Entry<Date, Date>> iterator = dateMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Date, Date> entry = iterator.next();
                Date start = entry.getKey();
                Date end = entry.getValue();
                //两个时间段 [A, B)、[X, Y)，X < B && A < Y 或者 A < Y && X < B则有交集
                if ((startTime.before(end) && endTime.after(start)) || (start.before(endTime) && end.after(startTime))) {
                    iterator.remove();
                    if (mixedOrUnion) {
                        tempMap.put(Test.getExtremumDate(true, startTime, start), Test.getExtremumDate(false, endTime, end));
                    } else {
                        tempMap.put(Test.getExtremumDate(false, startTime, start), Test.getExtremumDate(true, endTime, end));
                    }
                } else {
                    if (!iterator.hasNext()) {
                        tempMap.put(startTime, endTime);
                    }
                }
            }
            dateMap.putAll(tempMap);
        }
        return dateMap;
    }

}

@Getter
@Setter
class TimePeriod {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public TimePeriod(Date beginTime, Date endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }
}

