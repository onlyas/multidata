package com.onlyas.multidata.utils.generator;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.JDBCConnectionFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mybatis-Generator代码生成
 */
public class GeneratorStartUp {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        InputStream resourceAsStream = GeneratorStartUp.class.getClassLoader().getResourceAsStream("generator/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(resourceAsStream);
        //新增处理MSSQL的注释
        List<Context> llc = config.getContexts();
        Map<String, Object> dbColumnRemarkMap = new HashMap<>();
        Map<String, Object> dbTableRemarkMap = new HashMap<>();
        for (Context context : llc) {
            //JDBC
            JDBCConnectionConfiguration jdbcConnectionConfiguration = context.getJdbcConnectionConfiguration();
            //判断是否是MSSQL数据库
            String driver = jdbcConnectionConfiguration.getDriverClass();
            JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory(jdbcConnectionConfiguration);
            if (driver.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
                Connection conn = jdbcConnectionFactory.getConnection();
                QueryRunner qr = new QueryRunner();
                //Tables
                List<TableConfiguration> tableConfigurations = context.getTableConfigurations();
                Map<String, Object> tableColumnMap = new HashMap<>();
                Map<String, Object> tableRemarkMap = new HashMap<>();
                for (TableConfiguration table : tableConfigurations) {
                    List<ColRemark> tableRemark = new ArrayList<>();
                    String sql_table = "SELECT objname,value FROM  ::fn_listextendedproperty(NULL, 'user', 'dbo', 'table', '" + table.getTableName() + "', default, default)";
                    try {
                        tableRemark = qr.query(conn, sql_table, new BeanListHandler<>(ColRemark.class));
                    } catch (Exception e) {
                        //e.printStackTrace();
                        tableRemark = new ArrayList<>();
                    }
                    if (tableRemark.size() > 0) {
                        ColRemark remark = tableRemark.get(0);
                        tableRemarkMap.put(remark.getObjname(), remark.getValue());
                    }

                    List<ColRemark> list = new ArrayList<>();
                    String sql = "SELECT objname,value FROM  ::fn_listextendedproperty(NULL, 'user', 'dbo', 'table', '" + table.getTableName() + "', 'column', default)";
                    try {
                        list = qr.query(conn, sql, new BeanListHandler<>(ColRemark.class));
                    } catch (Exception e) {
                        //e.printStackTrace();
                        list = new ArrayList<>();
                    }

                    Map<String, Object> colMap = new HashMap<>();
                    for (ColRemark col : list) {
                        colMap.put(col.getObjname(), col.getValue());
                        //System.out.println(col.toString());
                    }

                    tableColumnMap.put(table.getTableName(), colMap);
                }
                dbColumnRemarkMap.put(context.getId(), tableColumnMap);
                dbTableRemarkMap.put(context.getId(), tableRemarkMap);
                conn.close();
            }
        }
        MBGlobal.dbColumnRemarkMap = dbColumnRemarkMap;
        MBGlobal.dbTableRemarkMap = dbTableRemarkMap;

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

}
