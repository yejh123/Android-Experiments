package com.example.exp2.util;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PWD = "861861";
    public static Connection connection = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet resultSet = null;


    //通用的：查询总数
    public static int getTotalCount(String sql){
        int count = -1;
        try{
            pstmt = createPreparedStatement(sql, null);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return count;
    }


    //通用的增删改
    public static boolean executeUpdate(String sql, Object[] params){
        try {
            //Object[] obs = { name,age ,...,x} ;
//			  String sql = "delete from xxx where Name = ? or id = ?  " ;
//			  pstmt.setInt(1,sno );
            //setXxx()方法的个数 依赖于 ?的个数， 而?的个数 又和 数组params的个数一致
            //setXxx()方法的个数 ->数组params的个数一致
            pstmt = createPreparedStatement(sql, params);
            int count = pstmt.executeUpdate();
            if (count > 0)
                return true;
            else
                return false;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    //通用的查: 通用 表示  适合与任何查询
    public static ResultSet executeQuery(String sql, Object[] params){
        try{
            pstmt = createPreparedStatement(sql, params);
            resultSet = pstmt.executeQuery();
            return resultSet;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //closeAll
    public static void closeAll(){
        if(resultSet != null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if(pstmt != null){
            try{
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //getConn
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("加载驱动类");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(URL, USERNAME, PWD);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }.start();
        return connection;
    }

    //createPre
    private static PreparedStatement createPreparedStatement(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        connection = getConnection();
        pstmt = connection.prepareStatement(sql);
        if(params != null && params.length != 0){
            for(int i = 0; i < params.length; ++i){
                if(params[i] != null){
                    pstmt.setObject(i+1, params[i]);
                }
            }
        }
        return pstmt;
    }



}
