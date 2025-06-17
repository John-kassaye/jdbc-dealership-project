package dealership;

import dealership.dao.VehicleDao;
import dealership.model.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static VehicleDao vehicleDao;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        init(args);

        boolean input = true;

        while (input) {
            System.out.println("What are u looking for");
            System.out.println("1 See all vehicles");
            System.out.println("2 See vehicles by color");
            System.out.println("3 See vehicles by type");
            System.out.println("4 See vehicles with price range");
            System.out.println("5 See vehicles with year range");

            switch (scanner.nextLine()) {
                case "1":
                    print(vehicleDao.findAll());
                    break;
                case "2":
                    System.out.println("What color?");
                    print(vehicleDao.findByColor(scanner.nextLine()));
                    break;
                case "3":
                    System.out.println("What type?");
                    print(vehicleDao.findByType(scanner.nextLine()));
                    break;
                case "4":
                    System.out.println("Min price");
                    int min = Integer.parseInt(scanner.nextLine());
                    System.out.println("Max price");
                    int max = Integer.parseInt(scanner.nextLine());
                    print(vehicleDao.findByPriceRange(min, max));
                    break;
                case "5":
                    System.out.println("Min year");
                    int minYear = Integer.parseInt(scanner.nextLine());
                    System.out.println("Max year");
                    int maxYear = Integer.parseInt(scanner.nextLine());
                    print(vehicleDao.findByPriceRange(minYear, maxYear));
                    break;
                case "6":
                    System.out.println("Bye!");
                    input = false;
            }
        }
    }

    public static void print(List<Vehicle> vehicles){
        for (Vehicle vehicle : vehicles){
            System.out.println(vehicle.toString());
        }
    }
    public static void init(String[] args){
        if (args.length != 2) {
            System.out.println("I need to parameters!");
        }

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/car_dealership");
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);

        vehicleDao = new VehicleDao(dataSource);
    }
}
