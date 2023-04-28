import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("Tahta Oluştur!");
        System.out.println("oyun tahtasının satır ve sütun bilgilerini girin ");
        Scanner sc = new Scanner(System.in);
        int row,col;
        System.out.print("Satır : ");
        row = sc.nextInt();
        System.out.print("Sütun : ");
        col = sc.nextInt();
        MineSweeper mine = new MineSweeper(row,col);
        mine.run();




    }

}