import java.util.Random;
import java.util.Scanner;

public class pexeso {
    static char[][] pole;
    static short x = -1, y = -1, x1 = -1, y1 = -1;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        short body1 = 0, body2 = 0;
        char b;
        boolean hraIde = true;

        while(hraIde) {
            System.out.println("\nPexeso");
            System.out.println("A: 2x2\nB: 4x4\nC: 6x6");
            b = sc.next().charAt(0);
            b = Character.toLowerCase(b);

            switch (b) {
                case 'a':
                    pole = new char[2][2];
                    break;
                case 'b':
                    pole = new char[4][4];
                    break;
                case 'c':
                    pole = new char[6][6];
                    break;
                default:
                    hraIde = false;
                    continue;
            }
            vypln();
            vypis();

            boolean onTurn = true;
            boolean koniec = false;
            while (!koniec) {
                System.out.print("Player ");
                if (onTurn) System.out.println("1: ");
                else System.out.println("2: ");

                for(int i = 1; i <= 2; i++){
                    System.out.println("Zadajte poziciu "+ i +". karty: ");

                    x1 = x;
                    y1 = y;

                    boolean spravne = false;
                    while(!spravne){
                        System.out.print("x: ");
                        x = sc.nextShort();
                        System.out.print("y: ");
                        y = sc.nextShort();
                        x--; y--;

                        if(x < 0 || x >= pole.length || y < 0 || y >= pole.length) System.out.println("Zle suradnice!");
                        else if(pole[y][x] == ' ') System.out.println("Tato karta uz bola uhadnuta!");
                        else if(x == x1 && y == y1) System.out.println("Tato karta uz je otocena!");
                        else spravne = true;
                    }

                    vypis();
                }

                if(pole[y][x] == pole[y1][x1]){
                    if(onTurn){
                        body1++;
                        if((body2 + body1 + 1) == (pole[0].length / 2 * pole.length)){
                            body1++;
                            koniec = true;
                        }
                        System.out.println("1. player: "+body1+"b");
                    }
                    else{
                        body2++;
                        if((body2 + body1 + 1) == (pole[0].length / 2 * pole.length)){
                            body2++;
                            koniec = true;
                        }
                        System.out.println("2. player: "+body2+"b");
                    }

                    pole[y][x] = ' ';
                    pole[y1][x1] = ' ';
                    onTurn = !onTurn;
                }

                onTurn = !onTurn;  // premenit hraca
                x = y = -1;
            }

            System.out.println("\n\n-----------");
            System.out.println("1. hrac: " +body1 +"b");
            System.out.println("2. hrac: " +body2 +"b");
            if(body1 > body2) System.out.println("1. hrac vyhral!");
            else if(body2 > body1) System.out.println("2. hrac vyhral!");
            else System.out.println("Remiza!");
        }
    }

    public static void vypln(){
        Random rn = new Random();
        int pocPar = pole[0].length / 2 * pole.length;

        for (int i = 65,z = 0; i < pocPar + 65; i++, z++) {
            int y =rn.nextInt(pole.length);
            int x=rn.nextInt(pole.length);

            if (pole[y][x] >= 65 && pole[y][x] <= 90){ i--; z--; continue; }
            else {
                pole[y][x] = (char)i;
            }

            if(z % 2 == 0) i--;
        }
    }

    public static void vypis(){
        System.out.print("      ");
        for (int i = 0; i < pole.length; i++) {
            System.out.print((i+1) +"  ");
        }
        System.out.print("\n     ");
        for (int i = 0; i < pole.length; i++) {
            System.out.print(" - ");
        }
        System.out.println();

        for(int Y = 0; Y < pole.length; Y++) {
            System.out.print((Y + 1) +"  |  ");

            for (int X = 0; X < pole[Y].length; X++) {
                if ((y == Y && x == X) || (y1 == Y && x1 == X) || pole[Y][X] == ' ') System.out.print(pole[Y][X] + "  ");
                else System.out.print("*  ");
            }
            System.out.println();
        }
    }

}
