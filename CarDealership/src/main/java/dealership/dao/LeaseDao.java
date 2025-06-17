package dealership.dao;

import dealership.model.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(LeaseContract contract) {
        String sql = "INSERT INTO lease_contracts (vin, customer_name, lease_start_date, lease_end_date, monthly_payment) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, contract.getVin());
            stmt.setString(2, contract.getCustomerName());
            stmt.setDate(3, contract.getStartDate());
            stmt.setDate(4, contract.getEndDate());
            stmt.setDouble(5,contract.getMonthlyPayment());


            stmt.executeUpdate();
            System.out.println("✅ Lease contract saved.");
        } catch (SQLException e) {
            System.out.println("❌ Error saving lease contract: " + e.getMessage());
        }
    }
}

