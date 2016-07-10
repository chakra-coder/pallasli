package com.pallasli.study.apachecommons.dbutils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

/**
 * 
 * 
 * 三、ResultSetHandler接口使用讲解
 * 
 * 该接口用于处理java.sql.ResultSet，将数据按要求转换为另一种形式。 ResultSetHandler接口提供了一个单独的方法：Object
 * handle (java.sql.ResultSet .rs)
 * 
 * 3.1、ResultSetHandler接口的实现类
 * 
 * ArrayHandler：把结果集中的第一行数据转成对象数组。
 * ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
 * BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
 * BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
 * ColumnListHandler：将结果集中某一列的数据存放到List中。
 * KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map里，其key为指定的key。
 * MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
 * MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
 * 
 * 
 * @ClassName: ResultSetHandlerTest
 * @Description:测试dbutils各种类型的处理器
 * @author: 孤傲苍狼
 * @date: 2014-10-6 上午8:39:14
 *
 */
public class ResultSetHandlerTest {

	@Test
	public void testArrayHandler() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users";
		Object result[] = qr.query(sql, new ArrayHandler());
		System.out.println(Arrays.asList(result)); // list toString()
	}

	@Test
	public void testArrayListHandler() throws SQLException {

		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users";
		List<Object[]> list = qr.query(sql, new ArrayListHandler());
		for (Object[] o : list) {
			System.out.println(Arrays.asList(o));
		}
	}

	@Test
	public void testColumnListHandler() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users";
		List list = (List) qr.query(sql, new ColumnListHandler("id"));
		System.out.println(list);
	}

	@Test
	public void testKeyedHandler() throws Exception {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users";

		Map<Integer, Map> map = (Map) qr.query(sql, new KeyedHandler("id"));
		for (Map.Entry<Integer, Map> me : map.entrySet()) {
			int id = me.getKey();
			Map<String, Object> innermap = me.getValue();
			for (Map.Entry<String, Object> innerme : innermap.entrySet()) {
				String columnName = innerme.getKey();
				Object value = innerme.getValue();
				System.out.println(columnName + "=" + value);
			}
			System.out.println("----------------");
		}
	}

	@Test
	public void testMapHandler() throws SQLException {

		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users";

		Map<String, Object> map = qr.query(sql, new MapHandler());
		for (Map.Entry<String, Object> me : map.entrySet()) {
			System.out.println(me.getKey() + "=" + me.getValue());
		}
	}

	@Test
	public void testMapListHandler() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users";
		List<Map> list = (List) qr.query(sql, new MapListHandler());
		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> me : map.entrySet()) {
				System.out.println(me.getKey() + "=" + me.getValue());
			}
		}
	}

	@Test
	public void testScalarHandler() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select count(*) from users"; // [13] list[13]
		int count = ((Long) qr.query(sql, new ScalarHandler(1))).intValue();
		System.out.println(count);
	}
}