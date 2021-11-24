
import java.io.*;
import java.util.*;

public class CarManager {

    public static void main(String[] args) throws IOException {
        ArrayList<String> options = new ArrayList<>();
        options.add("List all brands");
        options.add("Add a new brand");
        options.add("Search a brand based on its ID");
        options.add("Update a brand");
        options.add("Save brands to the file, named brand.txt");
        options.add("List all cars in ascending order of brand names");
        options.add("List cars based on a part of an input brand name");
        options.add("Add a car");
        options.add("Remove a car based on its ID");
        options.add("Update a car based on its ID");
        options.add("Save cars to file, named car.txt");
        int choice;
        String filePathBrand = "Brand.txt";
        String filePathCar = "Car.txt";
        Brandlist list0 = new Brandlist();       
        CarList list1 = new CarList(list0);
        Menu mne = new Menu();
        list0.loadFromFile(filePathBrand);
        list1.loadFromFile(filePathCar);
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n");
            choice = mne.int_getChoice(options);
            switch (choice) {
                case 1:
                    list0.listBrand();
                    break;
                case 2:
                    list0.addBrand();
                    break;
                case 3:
                    list0.searchBrand();
                    break;
                case 4:
                    list0.updateBrand();
                    break;
                case 5:
                    list0.savetoFile(filePathBrand);
                    break;
                case 6:
                    list1.listCar();
                    break;
                case 7:
                    list1.printBasedBrandName();
                    break;
                case 8:
                    list1.addCar();
                    break;
                case 9:
                    list1.removeCar();
                    break;
                case 10:
                    list1.updateCar();
                    break;
                case 11:
                    list1.savetoFile(filePathCar);
                    break;
                default:
                    System.out.println("Thanks");
                    break;
            }
        } while (choice > 0 && choice <= options.size());
    }
}
