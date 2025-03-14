import java.util.Scanner;

public class Semaforo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número correspondente ao sinal do semáforo (1 = vermelho, 2 = amarelo, 3 = verde): ");
        int numero = scanner.nextInt();

        switch (numero) {
            case 1:
                System.out.println("Vermelho");
                break;
            case 2:
                System.out.println("Amarelo");
                break;
            case 3:
                System.out.println("Verde");
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}