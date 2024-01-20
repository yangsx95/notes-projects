package io.github.yangsx95.notes.excel.hutools;

import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;

/**
 * 导出大批量分页数据工具
 * (是否加入多线程处理支持，待处理)
 *
 * @param <T> 行数据实体类型
 * @author yangshunxiang
 * @since 2022/8/10
 */
@Slf4j
public class ExcelPageExport<T> {

    /**
     * excel导出工具，hutools provide
     */
    @Getter
    private final ExcelWriter excelWriter;

    /**
     * 数据提供方
     */
    private final BiFunction<Long, Long, Collection<T>> dataSupplier;

    /**
     * 一次处理的条数
     */
    @Setter
    private long pageSize = 500;

    /**
     * 全局日期格式
     */
    @Setter
    private String dateFormatPattern = "yyyy-MM-dd";

    @Getter
    private final AtomicLong total = new AtomicLong(0);

    /**
     * 构造工具对象
     *
     * @param columns      第一行数据，也是表头，包含了表头字段的顺序，与实体字段之间的对应关系
     * @param dataSupplier 数据提供者，工具会将分页信息传递给该对象，该对象负责提供数据给Export
     */
    public ExcelPageExport(List<Column> columns,
                           BiFunction<Long, Long, Collection<T>> dataSupplier) {
        if (Objects.isNull(columns) || columns.size() == 0) {
            throw new IllegalArgumentException("表头不能为空");
        }
        if (dataSupplier == null) {
            throw new IllegalArgumentException("数据提供者布恩那个为空");
        }
        excelWriter = new BigExcelWriter();

        // 数据格式化(sheet级别)
        DataFormat dataFormat = excelWriter.getWorkbook().createDataFormat();
        excelWriter.getStyleSet().getCellStyleForDate().setDataFormat(dataFormat.getFormat(dateFormatPattern));

        Sheet sheet = excelWriter.getSheet();
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            // 列宽处理
            sheet.setColumnWidth(i, column.getWidth() * 256);
            // 表头别名处理
            excelWriter.addHeaderAlias(column.getFieldName(), column.getHeadDscp());
        }
        excelWriter.setOnlyAlias(true);
        this.dataSupplier = dataSupplier;
    }

    /**
     * 导出到输出流中，此方法不会关闭流
     *
     * @param outputStream 输出流
     */
    @SneakyThrows
    private void export2OutputStream(OutputStream outputStream) {
        Assert.notNull(outputStream, "outputStream流不可为空");
        long startTime = System.currentTimeMillis();
        int currentPage = 1;
        while (true) {
            // 到达最后一页，退出循环
            int size = processPage(currentPage);
            if (size <= 0) {
                break;
            }
            total.addAndGet(size);
            currentPage++;
        }
        excelWriter.flush(outputStream);
        excelWriter.close();
        long endTime = System.currentTimeMillis();
        log.info("Excel写入记录耗时 " + (endTime - startTime)  + "毫秒");

    }

    @SneakyThrows
    public void export2HttpResponse(String fileName, HttpServletResponse response) {
        Assert.notNull(response, "HttpServletResponse对象不可为null");
        //response.setContentType(Constants.EXCEL_MIME_XLSX);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
        response.addHeader("Access-Control-Expose-Headers", "filename");
        response.setHeader("filename", URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()) + ".xlsx");
        export2OutputStream(response.getOutputStream());
    }
    
    @SneakyThrows
    public void export2File(String filepath) {
        Assert.notNull(filepath, "导出文件不可为null");
        FileOutputStream fos = new FileOutputStream(filepath);
        export2OutputStream(fos);
    }

    /**
     * 处理一页，并返回处理的条数
     *
     * @param currentPage 分页对象
     * @return 处理的条数
     */
    private int processPage(long currentPage) {
        Collection<T> dataPageList = dataSupplier.apply(currentPage, pageSize);
        if (Objects.isNull(dataPageList) || dataPageList.size() == 0) {
            log.debug("excel导出数据已经处理完毕，总条数{}", total);
            return 0;
        }
        excelWriter.write(dataPageList, true);
        return dataPageList.size();
    }
    
    /**
     * 列表示
     */
    @Data
    public static class Column {

        /**
         * 字段名
         */
        private String fieldName;

        /**
         * 字段描述
         */
        private String headDscp;

        /**
         * 列宽
         */
        private Integer width = 10;
    }

    /**
     * 建造器
     */
    public static class Builder<T> {

        private BiFunction<Long, Long, Collection<T>> dataSupplier;

        private final List<Column> columns = new ArrayList<>();

        private int pageSize = 500;

        public static <B> Builder<B> builder(@SuppressWarnings("unused") Class<B> clazz) {
            return new Builder<>();
        }

        public Builder<T> header(String fieldName, String columnName) {
            Column column = new Column();
            column.setFieldName(fieldName);
            column.setHeadDscp(columnName);
            columns.add(column);
            return this;
        }

        public Builder<T> header(String fieldName, String columnName, int width) {
            Column column = new Column();
            column.setFieldName(fieldName);
            column.setHeadDscp(columnName);
            column.setWidth(width);
            columns.add(column);
            return this;
        }

        public Builder<T> dataSupplier(BiFunction<Long, Long, Collection<T>> dataSupplier) {
            this.dataSupplier = dataSupplier;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ExcelPageExport<T> build() {
            ExcelPageExport<T> excelPageExport = new ExcelPageExport<>(columns, dataSupplier);
            excelPageExport.setPageSize(pageSize);
            return excelPageExport;
        }

    }
}