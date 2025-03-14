import java.util.Scanner;

public class EscolhaComida {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu de Pratos:");
            System.out.println("1. Pizza");
            System.out.println("2. Hamburguer");
            System.out.println("3. Sushi");
            System.out.print("Escolha um prato (1-3): ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Você escolheu Pizza!");
                    return;
                case 2:
                    System.out.println("Você escolheu Hamburguer!");
                    return;
                case 3:
                    System.out.println("Você escolheu Sushi!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}