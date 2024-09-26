import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);

        int moedas[] = new int[6];

        for (int i = 0; i < 6; i++) {
            int a = stdin.nextInt();
            moedas[i] = a;
        }

        int a = stdin.nextInt();
        int b = stdin.nextInt();

        int count_retido = 0;
        int count_total = 0;
        int retido = 0;
        int troco=0;

        while (a != 0 || b != 0) {
            int total = 0;

            a*=100;

            total += a + b;
            //retido += a + b;

            //System.out.println("Total: " + total);

            int c = stdin.nextInt();

            int total_introduzido=0;

            while (c != 0) {
                if (c == 2) {
                    moedas[0]+=1;
                    c*=100;
                }

                if (c == 1) {
                    moedas[1]+=1;
                    c*=100;
                }

                if (c==50) moedas[2]+=1;
                if (c==20) moedas[3]+=1;
                if (c==10) moedas[4]+=1;
                if (c==5) moedas[5]+=1;

                total_introduzido += c;
                //retido -= c;
                
                c = stdin.nextInt(); 
            }

            troco=total_introduzido-total;

            //System.out.println("Troco " + troco);

            for(int j=0;j<6;j++){
                if ((troco-200>=0) && moedas[0]!=0){
                    troco-=200;
                    moedas[0]-=1;
                }
                else if ((troco-100>=0) && moedas[1]!=0){ 
                    troco-=100;
                    moedas[1]-=1;
                }
                else if ((troco-50>=0) && moedas[2]!=0){
                    troco-=50;
                    moedas[2]-=1;
                }
                else if ((troco-20>=0) && moedas[3]!=0){
                    troco-=20;
                    moedas[3]-=1;
                }
                else if ((troco-10>=0) && moedas[4]!=0){
                    troco-=10;
                    moedas[4]-=1;
                }
                else if ((troco-5>=0) && moedas[5]!=0){
                    troco-=5;
                    moedas[5]-=1;
                }
            }

            //System.out.println();

            if (troco != 0){
                retido+=troco;
                count_retido += 1;
            }
            count_total += 1;

            //System.out.println(troco);
            //System.out.println(count_retido + "/" + count_total);

            a = stdin.nextInt();
            b = stdin.nextInt();

        } 

        System.out.println(retido/100 + " " + retido % 100);
        System.out.println(count_retido + "/" + count_total);

        stdin.close();
    }
}
