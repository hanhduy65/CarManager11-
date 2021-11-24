
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public int int_getChoice(ArrayList options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i+1) + " " + options.get(i));
        }
        System.out.println("Input your choice 1 to " + options.size() + ": ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
    
    public Object ref_getChoice (ArrayList options) {
        int respone;
        do{
            respone = int_getChoice(options);
        }
        while (respone<0 || respone>options.size());
        return options.get(respone-1);
    }
}
