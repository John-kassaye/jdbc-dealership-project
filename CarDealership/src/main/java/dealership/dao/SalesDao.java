package dealership.dao;

import dealership.model.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(SalesContract contract) {
        String sql = "INSERT INTO sales_contracts (vin, customer_name, sale_price) " +
                "VALUES (?, ?, ?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, contract.getVin());
            stmt.setString(2, contract.getCustomerName());
            stmt.setDouble(3, contract.getSalePrice());

            stmt.executeUpdate();
            System.out.println("✅ Sale contract saved.");
        } catch (SQLException e) {
            System.out.println("❌ Error saving sale contract: " + e.getMessage());
        }
    }
}

