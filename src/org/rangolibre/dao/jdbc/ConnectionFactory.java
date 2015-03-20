/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alexandre Garcia Aguado
 */
public class ConnectionFactory {
    
    final String User = "root";
    final String PassWord = "senharoot";
    private static Connection conn = null;
    
    public Connection getConnection() {
        try {
            if (conn == null)
                conn = DriverManager.getConnection("jdbc:mysql://localhost/rlv1", User, PassWord);
            return conn;
        
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
