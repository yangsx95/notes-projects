package me.feathers.online.test;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.testng.annotations.Test;

/**
 * @author Feathers
 * @create 2017-06-27-10:41
 */
public class DDLTest {

    @Test
    public void testDDL(){
        Configuration configure = new Configuration().configure();
        SchemaExport export = new SchemaExport(configure);
        export.create(true,true);
    }
}
