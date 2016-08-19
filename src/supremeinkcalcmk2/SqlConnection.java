/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeinkcalcmk2;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Archa
 */
public class SqlConnection {

    public static Connection CustomerConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:CustomerSQLDB.db");
            return conn;
        } catch (Exception e) {
            return null;
        }

    }

    public static Connection FormulaConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:PantoneFormulas.db");
            return conn;
        } catch (Exception e) {
            return null;
        }

    }

}
