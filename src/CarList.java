
import java.io.*;
import java.util.*;

public class CarList extends ArrayList<Car> {

    Brandlist brandList;
    static Scanner sc = new Scanner(System.in);

    public CarList(Brandlist bList) {
        this.brandList = bList;
    }

    public boolean loadFromFile(String filename) throws IOException {
        File f = new File(filename); // kiem tra co ton tai file ko
        if (!f.exists()) {
            return false;
        } else {
            System.out.println("Absolute path of file is: " + f.getAbsolutePath());
            FileReader fr = new FileReader(f); // mo file de doc ly tu
            BufferedReader bf = new BufferedReader(fr);// de doc cac dong
            String line; // tung dong se dc doc tu file
            while ((line = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String carID = stk.nextToken().trim();
                String brandID = stk.nextToken().trim();
                String color = stk.nextToken().trim();
                String frameID = stk.nextToken().trim();
                String engineID = stk.nextToken().trim();
                int pos = brandList.searchIDpos(brandID);
                Brand brand = brandList.get(pos);
                Car cr = new Car(carID, brand, color, frameID, engineID);
                this.add(cr);
            }
            bf.close();
            fr.close();
        }
        return true;
    }

    public boolean savetoFile(String f) throws IOException {
        File filename = new File(f);
        if (!filename.exists()) {
            filename.createNewFile();
        }
        // mở file để ghi dữ liệu
        System.out.println("Absolute path of file is: " + filename.getAbsolutePath());
        FileWriter fw = new FileWriter(filename);
        PrintWriter pw = new PrintWriter(fw);// ghi từng dòng
        for (int i = 0; i < this.size(); i++) {
            pw.println(this.get(i));
        }
        pw.close();
        fw.close();
        return true;
    }

    public int searchID(String ID) {
        ID = ID.replaceAll(" ", "").toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().toUpperCase().equals(ID)) {
                return i;
            }
        }
        return -1; // ko dc tim thay
    }

    public int searchBrand(String ID) {
        ID = ID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().toUpperCase().equals(ID)) {
                return i;
            }
        }
        return -1; // ko dc tim thay
    }

    public int searchFrame(String fID) {
        fID = fID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().toUpperCase().equals(fID)) {
                return i;
            }
        }
        return -1; // ko dc tim thay
    }

    public int searchEngine(String eID) {
        eID = eID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().toUpperCase().equals(eID)) {
                return i;
            }
        }
        return -1; // ko dc tim thay
    }

    private boolean isIdDupplicated(String ID) {
        ID = ID.trim().toUpperCase();
        return searchID(ID) != -1;
    }

    private boolean isFidDupplicated(String ID) {
        ID = ID.trim().toUpperCase();
        return searchFrame(ID) != -1;
    }

    private boolean isEidDupplicated(String ID) {
        ID = ID.trim().toUpperCase();
        return searchEngine(ID) != -1;
    }

    public static String inputPattern(String arg, String pattern) {
        String data;
        sc = new Scanner(System.in);
        do {
            System.out.print(arg);
            data = sc.nextLine().trim();
        } while (!data.matches(pattern));
        return data;
    }

    public void addCar() {
        String newCarID, newColor, newFrameID, newEngineID;
        boolean isDupplicated = false;
       // sc = new Scanner(System.in);
        do {
            System.out.println("Input new car ID: ");
            newCarID = sc.nextLine();
            newCarID = newCarID.trim().toUpperCase();
            isDupplicated = isIdDupplicated(newCarID);
            if (isDupplicated) {
                System.err.println("ID is duplicated");
            }
        } while (isDupplicated == true);
        Menu mnea = new Menu();
        Brand bd = (Brand) mnea.ref_getChoice(brandList);
        System.out.println("Input new color: ");
        newColor = sc.nextLine();
        do {
            newFrameID = inputPattern("Frame ID is must be in the F00000: ", "[fF][\\d]{5}");
            newFrameID = newFrameID.toUpperCase();
            isDupplicated = isFidDupplicated(newFrameID);
            if (isDupplicated) {
                System.err.println("Frame ID is duplicated");
            }
        } while (isDupplicated == true);
        do {
            newEngineID = inputPattern("Engine ID is must be in the E00000: ", "[eE][\\d]{5}");
            newEngineID = newEngineID.toUpperCase();
            isDupplicated = isEidDupplicated(newEngineID);
            if (isDupplicated) {
                System.err.println("Engine ID is duplicated");
            }
        } while (isDupplicated == true);
        Car cr = new Car(newCarID, bd, newColor, newFrameID, newEngineID);
        this.add(cr);
        System.out.println("Car " + newCarID + " has been added.");
    }

    public boolean removeCar() {
        if (this.isEmpty()) {
            System.err.println("Empty list. No remove can be performed");
        } else {
            Car cr = null;
            System.out.println("Input car ID of remove performed: ");
            String carId = sc.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (searchID(carId) == i) {
                    cr = this.get(i);
                }
            }
            if (cr == null) {
                System.err.println("Car " + carId + " doesn't existed!");
            } else {
                this.remove(cr);
                System.out.println("Car " + carId + " has been removed");
            }
        }
        return true;
    }

    public boolean updateCar() {
        String newColor, newFrameID, newEngineID;
        boolean isDupplicated = false;
        if (this.isEmpty()) {
            System.err.println("Empty list. No update can be performed!");
        } else {
            Car cr = null;
            System.out.println("Input ID car update car:");
            String carID = sc.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (searchID(carID) == i) {
                    cr = this.get(i);
                }
            }
            if (cr == null) {
                System.err.println("Car has " + carID + " doesn't existed!");
            } else {
                Menu mneu = new Menu();
                Brand bd = (Brand) mneu.ref_getChoice(brandList);
                System.out.println("Old color: " + cr.getColor() + ", new color: ");
                newColor = sc.nextLine();
                cr.setColor(newColor);
                do {
                    newFrameID = inputPattern("Frame ID is must be in the F00000: ", "[fF][\\d]{5}");
                    newFrameID = newFrameID.toUpperCase();
                    isDupplicated = isFidDupplicated(newFrameID);
                    if (isDupplicated) {
                        System.err.println("Frame ID is duplicated");
                    }
                    cr.setFrameID(newFrameID);
                } while (isDupplicated == true);
                do {
                    newEngineID = inputPattern("Engine ID is must be in the E00000: ", "[eE][\\d]{5}").toUpperCase();
                    isDupplicated = isEidDupplicated(newEngineID);
                    if (isDupplicated) {
                        System.err.println("Engine ID is duplicated");
                    }
                    cr.setEngineID(newEngineID);
                } while (isDupplicated == true);

                System.out.println("Car " + carID + " has been updated.");
            }
        }
        return true;
    }

    public void printBasedBrandName() {
        sc = new Scanner(System.in);
        System.out.println("Enter a part of brand name: ");
        String aPart = sc.next();
        int count = 0;
        for (int i = 0; i < this.size(); i++) {
            Car c = this.get(i);
            if (c.brand.getBrandName().contains(aPart)) {
                System.out.println(c.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.err.println("No car is detected!");
        }
    }

    public void listCar() {
        Collections.sort(this, new Comparator<Car>() {
            @Override
            public int compare(Car t, Car t1) {
                return t.brand.brandName.compareTo(t1.brand.brandName);
            }
        });
        for (int i = 0; i < this.size(); i++) {
            Car c = this.get(i);
            System.out.println(c.toString());

        }
    }
    
}
