package com.how2java.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;
import com.how2java.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.pojo.Category;

public class TestMybatis {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

       // listAll(session);
       // List<Category> cs = session.selectList("listCategoryByName","cat");
       // for (Category c : cs) {
       //     System.out.println(c.getName());
       // }
        /*
        List<Category>cs=session.selectList("listCategory");
        for(Category c:cs){
            System.out.println(c);
            List<Product> ps=c.getProducts();
            for (Product p:ps
                 ) {
                System.out.println("\t"+p);
            }
        }
        List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p+" 对应的分类是 \t "+ p.getCategory());
        }
*/
        //addOrderItem(session);
//addOrderItem(session);
//listOrder(session);
        /*
System.out.println("查询所有：");
List<Product> ps=session.selectList("listAllProduct");
        for (Product p:ps
             ) {
            System.out.println(p);
        }
        System.out.println("模糊查询：");
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("name","a");
        List<Product>ps2=session.selectList("listAllProduct",params);
        for (Product p2:ps2
             ) {
            System.out.println(p2);
        }
        */
        /*
System.out.println("多条件查询：");
        Map<String,Object>params2=new HashMap<>();
        params2.put("name","p");
        params2.put("price","20");
        List<Product> ps3=session.selectList("listAllProduct",params2);
        for (Product p3:ps3
             ) {
            System.out.println(p3);
        }

        System.out.println("修改数据：");
        Product p4=new Product();
        p4.setId(6);
        p4.setName("product zz");
        p4.setPrice(82.34f);
        session.update("updateProduct",p4);
        listOrder(session);
        */
        /*
        System.out.println("{choose标签}上面没条件执行下面条件");
        Map<String,Object>param3=new HashMap<>();
        List<Product>ps5=session.selectList("listAllProduct",param3);
        for (Product p5:ps5
             ) {
            System.out.println(p5);
        }
*/
        /*
        System.out.println("foreach标签使用：");
        List<Integer>ids=new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<Product> ps6=session.selectList("listAllProduct",ids);
        for (Product p6:ps6
             ) {
            System.out.println(p6);
        }
        */
        //二级缓存测试
        Category c1 = session.selectOne("getCategory", 1);
        System.out.println(c1);
        Category c2 = session.selectOne("getCategory", 1);
        System.out.println(c2);

        session.commit();
        session.close();

    }
    private  void deleteOrderItem(SqlSession session){
        Order o1=session.selectOne("getOrder",1);
        Product p6=session.selectOne("getProduct",6);
        OrderItem oi=new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        session.delete("deleteOrderItem",oi);
    }
    private static void addOrderItem(SqlSession session){
        Order o1=session.selectOne("getOrder",1);
        Product p6=session.selectOne("getProduct",6);
        OrderItem oi=new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        oi.setNumber(200);
        session.insert("addOrderItem",oi);
    }




    public static void listOrder(SqlSession session)
    {
        List<Order> os=session.selectList("listOrder");
        for (Order o:os
             ) {
            System.out.println(o.getCode());
            List<OrderItem> ois=o.getOrderItems();
            for (OrderItem oi:ois
                 ) {
                System.out.format("\t%s\t%f\t%d%n",
                        oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
            }
        }
    }




	private static void update(SqlSession session) {
		Category c= session.selectOne("getCategory",3);
        c.setName("�޸��˵�Category���Q");
        session.update("updateCategory",c);

        listAll(session);
	}

	private static void get(SqlSession session) {
		Category c= session.selectOne("getCategory",3);

        System.out.println(c.getName());
	}

	private static void delete(SqlSession session) {
		Category c = new Category();
        c.setId(6);
        session.delete("deleteCategory",c);
        listAll(session);
	}

	private static void add(SqlSession session) {
		Category c = new Category();
        c.setName("�����ӵ�Category");
        session.insert("addCategory",c);

        listAll(session);
	}

    private static void listAll(SqlSession session) {
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
}