
import java.io.*;
import java.util.*;

public class Brandlist extends ArrayList<Brand> {

    public Brandlist() {
        super();
    }

    Scanner sc = new Scanner(System.in);

    public boolean loadFromFile(String filename) throws FileNotFoundException, IOException {
        File f = new File(filename); // kiem tra co ton tai file ko
        if (!f.exists()) {
            return false;
        } else {
            System.out.println("Absolute path of file is: " + f.getAbsolutePath());
            FileReader fr = new FileReader(f); // mo file de doc ly tu
            BufferedReader bf = new BufferedReader(fr);// de doc cac dong
            String line;
            while ((line = bf.readLine()) != null) { // đọc 1 dòng kí tự 
                StringTokenizer stk = new StringTokenizer(line, ",:");//Tạo ra một đối tượng StringTokenizer mới dựa trên chuỗi được chỉ định và một tập các dấu phân cách.
                String brandID = stk.nextToken().trim(); // trả về 1 đối tượng
                String brandName = stk.nextToken().trim();
                String soundBrand = stk.nextToken().trim();
                Double price = Double.parseDouble(stk.nextToken().trim());
                Brand br = new Brand(brandID, brandName, soundBrand, price);
                this.add(br);
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

    public Brand searchID(String ID) {
        ID = ID.toUpperCase();
        ID = ID.replaceAll(" ", "");
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equals(ID)) {
                return this.get(i);
            }
        }
        return null; // ko dc tim thay
    }
    
    public int searchIDpos (String ID) {    
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equals(ID)){
                return i;
            }
        }
        return -1;
    }

    public void searchBrand() {
        if (this.isEmpty()) {
            System.err.println("Empty list. No search can be performed!");
        } else {
            System.out.println("Input brand ID for search: ");
            sc = new Scanner(System.in);
            String ID = sc.nextLine();
            Brand br = this.searchID(ID);// search brand based on brand ID
            if (br == null) {
                System.err.println("Brand " + ID + " doesn't existed!");
            } else {
                System.out.println("Found: " + br);
            }
        }
    }
    
    public Brand getUserChoice(){
        Menu mnu = new Menu();
        return (Brand) mnu.ref_getChoice(this);
    }

    private boolean isIdDupplicated(String ID) {
        ID = ID.trim().toUpperCase();
        return searchID(ID) != null;
    }

    public void addBrand() {
        String newBrandID, newBrandName, newSoundBrand;
        double newPrice;
        boolean idDupplicated = false;
        do {
            System.out.println("Input new brand ID: ");
            sc = new Scanner(System.in);
            newBrandID = sc.nextLine().replaceAll(" ", "").toUpperCase();
            idDupplicated = isIdDupplicated(newBrandID);
            if (idDupplicated) {
                System.err.println("ID is duplicated");
            }
        } while (idDupplicated == true);
        System.out.println("Input new brand name: ");
        newBrandName = sc.nextLine().toUpperCase();
        System.out.println("Input new sound brand: ");
        newSoundBrand = sc.nextLine().toUpperCase();
        System.out.println("Input new price: ");
        newPrice = sc.nextDouble();
        Brand br = new Brand(newBrandID, newBrandName, newSoundBrand, newPrice);
        this.add(br);
        System.out.println("Brand " + newBrandID + " has been added.");
    }

    public void updateBrand() {
        if (this.isEmpty()) {
            System.err.println("Empty list. No update can be performed!");
        } else {
            System.out.println("Input ID brand of update Brand:");
            sc = new Scanner(System.in);
            String ID = sc.nextLine();
            Brand br = this.searchID(ID);
            if (br == null) {
                System.err.println("Brand has " + ID + " doesn't existed!");
            } else {
                System.out.println("Old brand name: " + br.getBrandName() + ", new brand name: ");
                String newBrandName = sc.nextLine();
                br.setBrandName(newBrandName.toUpperCase());
                System.out.println("Old sound brand: " + br.getSoundBrand() + ", new sound brand: ");
                String newSoundBrand = sc.nextLine();
                br.setSoundBrand(newSoundBrand.toUpperCase());
                System.out.println("Old price: " + br.getPrice() + ", new price: ");
                double newPrice = sc.nextDouble();
                br.setPrice(newPrice);
                System.out.println("Brand has " + ID + " has been update.");
            }
        }
    }

    public void listBrand() {
        if (this.isEmpty()) {
            System.err.println("Empty list");
        } else {
            System.out.println("Brands list: ");
            for (int i = 0; i < this.size(); i++) {
                System.out.println(this.get(i));
            }
            System.out.println("Total: " + this.size() + " brand(s).");
        }
    }

}
