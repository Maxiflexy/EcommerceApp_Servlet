package com.maxiflexy.dao;

import com.maxiflexy.model.Customer;
import com.maxiflexy.utility.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    private Connection connection;

    public CustomerDAO(Connection connection){
        this.connection = connection;
    }

    public void addUser(Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql = "INSERT INTO customers (firstName, lastName, email, password) VALUES (?, ?, ?, ?)";

        try {
            connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException exception){
            throw new RuntimeException();
        }
        connection.close();

    }

}
