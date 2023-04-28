import javax.swing.text.html.StyleSheet;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int col;
    String field[][];
    String map[][];
    //String map1[][];
    int countCell;
    int enterRow;
    int enterCol;
    boolean durum=true;
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);


    MineSweeper(int row , int col){
        this.row = row;
        this.col = col;
        this.countCell = (row*col);
        this.field = new String[this.row][this.col];
        map = new String[this.row][this.col];

    }
    void run(){
        //program metotları buradan çalışcak

        fillField(this.map);
        fillMap(this.map);

        printMap(this.map);

        System.out.println("*****oyun alanı test *****************************");

        System.out.println("----    GAME BOARD  ---- ");
        fillField(this.field);
        printField(this.field);

        while (durum){

            System.out.print("satır : ");
            enterRow = sc.nextInt();
            System.out.print("sütun : ");
            enterCol = sc.nextInt();
            System.out.println("****************");
            if (enterRow <0 || enterRow >=this.row || enterCol <0 || enterCol>=this.col){
                System.out.println("aralık dışı değer girdiniz");
                continue;
            }else {
                checkAround(enterRow,enterCol);

                fieldUpdate(enterRow,enterCol);
            }
            System.out.println("Haritanın güncel durumu");
            printField(this.field);
            System.out.println("****************");
            if (isWin()){
                break;
            }
        }












    }
    boolean isWin(){
        int m = (countCell / 4),count=0;

        for (int i = 0 ; i < this.field.length;i++){
            for (int j = 0 ; j < this.field[0].length;j++){
                if (this.field[i][j].equals("-")){
                    count++;

                }
            }
        }
        if (count==m){
            System.out.println("oyunu kazandın!!!!");
            System.out.println();
            System.out.println("MAYIN HARİTASI");
            printMap(this.map);
            return true;
        }
        return false;
    }
    //map'i ekrana basar
    void printMap(String[][] arr){ // Str map1 basar
        for (int i = 0 ; i < arr.length;i++){
            for (int j = 0 ; j < arr[0].length;j++){

                System.out.print(arr[i][j] + " ");

            }
            System.out.println();
        }
    }
    //haritaya mayınları yerleştirir test için
    void mayinTest(String[][] arr){
        for (int i = 0 ; i < arr.length;i++){
            for (int j = 0; j < arr[0].length;j++)
                if(!(i==2 && j ==2)){
                    arr[i][j] = "*";
                }
        }
    }

    void fillMap(String[][] arr){
        int col,row,count;

        count = (countCell / 4);

        while (count > 0){
            col = this.rand.nextInt(this.col);
            row = this.rand.nextInt(this.row);
            if(arr[row][col].equals("-")){
                arr[row][col] = "*";
                count--;
            }
        }
    }

    //oyun alanını ekrana basar
    void printField(String[][] arr){
        //field'i ekrana basar
        for (int i = 0 ; i < arr.length;i++){
            for (int j = 0 ; j < arr[0].length;j++){

                System.out.print(" ");

                System.out.print(arr[i][j] + " ");

            }
            System.out.println();
        }
    }
    //oyun alanının görüntüsünü hazırlar / map içinde kullanılır
    void fillField(String[][] arr){
        for (int i = 0 ; i < arr.length;i++){
            for (int j = 0 ; j < arr[0].length;j++){
                arr[i][j] = "-";
            }
        }

    }
    void fieldUpdate(int r,int c){
        this.field[r][c] =this.map[r][c];

    }
    //mapte ki mayınları kontrol edicek
    void checkAround(int r,int c){
        int mine =0;

        if(this.map[r][c].equals("-")){
            this.durum = true;

            if((c -1 >= 0)&& (r -1 >= 0)&& (this.map[r-1][c-1].equals("*"))){
                //System.out.println("sol üst ");
                mine++;
                //sol üst colon
            }
            if((r-1>=0)&&(this.map[r-1][c].equals("*"))){
                //System.out.println("üst");
                mine++;
                //üst
            }
            if((r-1 >=0 ) && (c+1 < this.col)&&(this.map[r-1][c+1].equals("*"))){
                mine++;
                //System.out.println("sağ üst");
                //sağ üst
            }
            if((c-1>=0)&&(this.map[r][c-1].equals("*"))) {
                //System.out.println("sol");
                mine++;
                //sol
            }

            if(   ( c+1 < this.col) && (this.map[r][c+1].equals("*"))) {
                //System.out.println("sağ");
                mine++;
                //sağ
            }
            if((c-1 >= 0) && (r+1 < this.row) && (this.map[r+1][c-1].equals("*"))) {
                //System.out.println("alt sol");
                mine++;
                //alt - sol
            }
            if((r < this.col -1) && (c <= this.col -1)&&(this.map[r+1][c].equals("*"))) {
                mine++;
                //  System.out.println("alt");
                //alt
            }
            if( (r+1 < this.row)&& (c+1 < this.col)&&(this.map[r+1][c+1].equals("*"))) {
                //System.out.println("alt sağ");
                mine++;
                //alt - sağ  // bunu bitir 0-0
            }


            map[r][c] = (String.valueOf(mine));
        }else {
            System.out.println("GAME OVER !!!");
            this.durum = false;
            System.out.println("****************");
            System.out.println("MAYIN HARİTASI");
            printMap(this.map);
            System.out.println("****************");

        }

    }
}
