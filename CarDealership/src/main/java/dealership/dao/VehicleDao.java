package dealership.dao;

import dealership.model.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles;")

        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                    vehicles.add(new Vehicle(resultSet.getString("VIN"),resultSet.getInt("price"),
                            resultSet.getInt("year"),resultSet.getNString("Color"),
                            resultSet.getNString("type")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> findByPriceRange(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE price >= ? AND price <= ?;")

                ) {
            preparedStatement.setInt(1,min);
            preparedStatement.setInt(2,max);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    vehicles.add(new Vehicle(resultSet.getString("VIN"), resultSet.getInt("price"),
                            resultSet.getInt("year"),resultSet.getNString("Color"),
                            resultSet.getNString("type")));
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> findByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE year >= ? AND year <= ?;")

        ) {
            preparedStatement.setInt(1,minYear);
            preparedStatement.setInt(2,maxYear);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    vehicles.add(new Vehicle(resultSet.getString("VIN"), resultSet.getInt("price"),
                            resultSet.getInt("year"),resultSet.getNString("Color"),
                            resultSet.getNString("type")));
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> findByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE color = ?;")

        ) {
            preparedStatement.setString(1,color);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    vehicles.add(new Vehicle(resultSet.getString("VIN"), resultSet.getInt("price"),
                            resultSet.getInt("year"),resultSet.getNString("Color"),
                            resultSet.getNString("type")));
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


        return vehicles;
    }

    public List<Vehicle> findByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE type = ?;")

        ) {
            preparedStatement.setString(1,type);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    vehicles.add(new Vehicle(resultSet.getString("VIN"),resultSet.getInt("price"),
                            resultSet.getInt("year"),resultSet.getNString("Color"),
                            resultSet.getNString("type")));
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (vin, price, year, color, type) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getPrice());
            preparedStatement.setInt(3, vehicle.getYear());
            preparedStatement.setString(4, vehicle.getColor());
            preparedStatement.setString(5, vehicle.getType());

            preparedStatement.executeUpdate();
            System.out.println("✅ Vehicle added successfully.");
        } catch (SQLException e) {
            System.out.println("❌ Error adding vehicle: " + e.getMessage());
        }
    }

    public void removeVehicleByVin(String vin) {
        String sql = "DELETE FROM vehicles WHERE vin = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, vin);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Vehicle with VIN " + vin + " removed.");
            } else {
                System.out.println("⚠️ No vehicle found with VIN " + vin);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error removing vehicle: " + e.getMessage());
        }
    }

}

