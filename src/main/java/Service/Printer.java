package Service;

public class Printer {
    public static void printFirstMenu(String[] menu){
        for(int i = 0; i < menu.length; i++){
            System.out.println(i + 1 + "-" + menu[i]);
        }
    }
    public static void printSecondMenu(String[] menu){
        for(int i = 0; i < menu.length; i++){
            System.out.println(i + 1 + "-" + menu[i]);
        }
    }
}
