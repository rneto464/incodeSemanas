public class RepetirAcao {
    public static void main(String[] args) {
        int vezes = 0;

        while (vezes <= 0) {
            System.out.println("Quantas vezes você deseja repetir a ação? (Digite um valor maior que 0): " + vezes);

            if (vezes <= 0) {
                System.out.println("Valor inválido! Tente novamente.");
                vezes = 3; 
            }
        }

        for (int i = 1; i <= vezes; i++) {
            System.out.println("Repetindo a ação pela " + i + "ª vez.");
        }
    }
}