package com.example.L08jdbcdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.util.EnumUtils;

import java.sql.*;

@Repository
public class ProductDAO {

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.user}")
    private String username;

    @Value("${db.password}")
    private String password;


    public Product getProductById(Long id){
        Connection connection = null;
        Product product = null;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            try (Statement statement = connection.createStatement()) {
                String sql = "Select * from product where id=" + id;
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    product = new Product(resultSet.getLong("Id"), resultSet.getString("name"), resultSet.getDouble("price"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return product;
    }

    public Product createProduct(Product product){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            String sql = "insert into product values (null,'"+product.getName()+"',"+product.getCost()+")";
            int affectedRow = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            if(affectedRow == 0){
                throw new SQLException("Creating Product Failed");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                product.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return product;
    }


    public Product createProductWithPS(Product product){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            String sql = "insert into product values (null,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setDouble(2,product.getCost());
            int affectedRow = statement.executeUpdate();
            if(affectedRow == 0){
                throw new SQLException("Creating Product Failed");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                product.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return product;
    }


    public Product createProductWithPSWithTxn(Product product){
        Connection connection = null;
        boolean autocommit = false;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            String sql = "insert into product values (null,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setDouble(2,product.getCost());
            int affectedRow = statement.executeUpdate();
            if(affectedRow == 0){
                throw new SQLException("Creating Product Failed");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                product.setId(generatedKeys.getLong(1));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            if(connection != null){
                try {
                    connection.setAutoCommit(autocommit);
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return product;
    }

}
