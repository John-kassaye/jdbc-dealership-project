package dealership.dao;

import dealership.model.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
                    vehicles.add(new Vehicle(resultSet.getInt("price"),
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
                    vehicles.add(new Vehicle(resultSet.getInt("price"),
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
                    vehicles.add(new Vehicle(resultSet.getInt("price"),
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
                    vehicles.add(new Vehicle(resultSet.getInt("price"),
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
                    vehicles.add(new Vehicle(resultSet.getInt("price"),
                            resultSet.getInt("year"),resultSet.getNString("Color"),
                            resultSet.getNString("type")));
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return vehicles;
    }
}

