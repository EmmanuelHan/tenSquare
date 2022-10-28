import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.tensquare.base.entity.City;
import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExcelExport {

    @Test
    public void test1() {
        String filename = "/usr/a.csv";

        List<City> cities = new ArrayList<>();
        City city = new City();
        city.setName("北京");
        city.setHot("1");
        cities.add(city);

        Set<String> excludeColumns = new HashSet<>();
        excludeColumns.add("hot");

        ExcelWriterBuilder write = EasyExcel.write(filename, City.class);
        write.excelType(ExcelTypeEnum.CSV).charset(Charset.forName("GBK")).sheet("sheet1").doWrite(cities);
        if(!ObjectUtils.isEmpty(excludeColumns)){
            write.excludeColumnFieldNames(excludeColumns);
        }
    }

}
