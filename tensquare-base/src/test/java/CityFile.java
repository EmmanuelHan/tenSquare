import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CityFile {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\hanlei\\Documents\\城市列表.txt");
        try {
            List<String> strings = FileUtils.readLines(file, StandardCharsets.UTF_8.name());
            int id = 0;
            String city;
            String province;
            String level = "直辖市";
            for (String string : strings) {
                id++;
                String[] split = string.split("\t");
                city = split[0].substring(split[0].indexOf("省") + 1);
                if (string.contains("省")) {
                    province = split[0].substring(0, split[0].indexOf("省") + 1);
                } else if (string.contains("重庆市")) {
                    province = split[0].substring(0, split[0].indexOf("重庆市") + 3);
                    city = split[0].substring(split[0].indexOf("重庆市") + 3);
                    if (split[0].equals("重庆市")) {
                        province = "重庆市";
                        city = "重庆市";
                    }
                } else if (string.contains("新疆")) {
                    province = "新疆";
                    city = split[0].substring(split[0].indexOf("新疆") + 2);
                } else if (string.contains("宁夏")) {
                    province = "宁夏";
                    city = split[0].substring(split[0].indexOf("宁夏") + 2);
                } else if (string.contains("广西")) {
                    province = "广西";
                    city = split[0].substring(split[0].indexOf("广西") + 2);
                } else if (string.contains("内蒙")) {
                    province = "内蒙";
                    city = split[0].substring(split[0].indexOf("内蒙") + 2);
                } else {
                    province = split[0];
                }
                level = split[2];
                String format = String.format("INSERT INTO `tensquare_base`.`tb_city` (`id`, `province`, `name`, `hot`, `level`) " +
                        "VALUES (%s, '%s', '%s', '0', '%s');", id, province, city, level);
                System.out.println(format);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
