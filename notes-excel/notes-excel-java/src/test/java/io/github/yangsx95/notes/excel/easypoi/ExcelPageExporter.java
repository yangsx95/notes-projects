package io.github.yangsx95.notes.excel.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;

/**
 * Excel导出器，基于easy poi的exportBigExcel的增强类
 * 1. 此类不能在多个线程中共享
 * 2. 数据读取不支持多线程并发获取
 * 3. 只单个sheet的工作簿
 *
 * @author yangshunxiang
 * @since 2022/8/12
 */
@Slf4j
public class ExcelPageExporter<T> {

    /**
     * 数据提供方，三个泛型分别为 currentPage, pageSize, 分页查询结果
     */
    private final BiFunction<Long, Long, Collection<T>> dataSupplier;

    /**
     * 一次处理的条数
     */
    @Setter
    private long pageSize = 500;

    /**
     * 已处理的总条数
     */
    @Getter
    private final AtomicLong total = new AtomicLong(0L);

    /**
     * Excel参数
     */
    @Getter
    @Setter
    private ExportParams exportParams;

    /**
     * 导出的数据bean
     */
    private final Class<T> clazz;

    /**
     * 构造工具对象
     *
     * @param exportParams easy poi的导出参数
     * @param clazz        导出数据bean的class类型
     * @param dataSupplier 数据提供者，工具会将分页信息传递给该对象，该对象负责提供数据给Export
     */
    public ExcelPageExporter(ExportParams exportParams,
                             Class<T> clazz,
                             BiFunction<Long, Long, Collection<T>> dataSupplier) {
        if (exportParams == null) {
            throw new IllegalArgumentException("导出属性不能为空");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("导出的数据bean的class不能为空");
        }
        if (dataSupplier == null) {
            throw new IllegalArgumentException("数据提供者不可为空");
        }
        this.dataSupplier = dataSupplier;
        this.exportParams = exportParams;
        this.clazz = clazz;
    }

    private void export2OutputStream(OutputStream outputStream) {
        Assert.notNull(outputStream, "outputStream流不可为空");
        long startTime = System.currentTimeMillis();

        try (Workbook workbook = ExcelExportUtil.exportBigExcel(exportParams, clazz, getExportEServer(), null)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            log.error("写入excel失败", e);
        }

        long endTime = System.currentTimeMillis();
        log.info("导出Excel操作完成，写入记录{}条，共耗时 {} 毫秒", total, (endTime - startTime));
    }

    private IExcelExportServer getExportEServer() {
        return (o, i) -> {
            Collection<T> pageDataList = dataSupplier.apply((long) i, pageSize);
            if (pageDataList == null) {
                return Collections.emptyList();
            }
            total.addAndGet(pageDataList.size());
            return new ArrayList<>(pageDataList);
        };
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
     * 建造器
     */
    public static class Builder<T> {

        private BiFunction<Long, Long, Collection<T>> dataSupplier;

        private int pageSize = 500;

        private final Class<T> clazz;

        private final ExportParams exportParams = new ExportParams();

        public Builder(Class<T> clazz) {
            this.clazz = clazz;
        }

        public static <B> Builder<B> builder(Class<B> clazz) {
            return new Builder<>(clazz);
        }

        public Builder<T> dataSupplier(BiFunction<Long, Long, Collection<T>> dataSupplier) {
            this.dataSupplier = dataSupplier;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> title(String title) {
            this.exportParams.setTitle(title);
            return this;
        }

        public Builder<T> sheetName(String sheetName) {
            this.exportParams.setSheetName(sheetName);
            return this;
        }

        public ExcelPageExporter<T> build() {
            ExcelPageExporter<T> excelPageExport = new ExcelPageExporter<>(exportParams, clazz, dataSupplier);
            excelPageExport.setPageSize(pageSize);
            return excelPageExport;
        }

    }

}