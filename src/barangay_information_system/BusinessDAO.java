/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barangay_information_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Neil
 */
public class BusinessDAO {
      public static void saveBusinessInfo(int residentId, String name, String address) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if exists
            PreparedStatement check = conn.prepareStatement(
                "SELECT BusinessID FROM BusinessInfo WHERE ResidentID = ?");
            check.setInt(1, residentId);
            
            if (check.executeQuery().next()) {
                // Update existing
                PreparedStatement update = conn.prepareStatement(
                    "UPDATE BusinessInfo SET BusinessName = ?, BusinessAddress = ? WHERE ResidentID = ?");
                update.setString(1, name);
                update.setString(2, address);
                update.setInt(3, residentId);
                update.executeUpdate();
            } else {
                // Insert new
                PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO BusinessInfo (ResidentID, BusinessName, BusinessAddress) VALUES (?,?,?)");
                insert.setInt(1, residentId);
                insert.setString(2, name);
                insert.setString(3, address);
                insert.executeUpdate();
            }
        }
    }

    public static String[] loadBusinessInfo(int residentId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT BusinessName, BusinessAddress FROM BusinessInfo WHERE ResidentID = ?");
            stmt.setInt(1, residentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new String[]{
                    rs.getString("BusinessName"),
                    rs.getString("BusinessAddress")
                };
            }
            return new String[]{"", ""};
        }
    }
    
}
