public class SomaNumeros {
    public static void main(String[] args) {
        int numero = 6; 
        
        int soma = 0;
        int i = 1;
        while (i <= numero) {
            soma += i;
            i++;
        }
        
        System.out.println("A soma dos números de 1 até " + numero + " é: " + soma);
    }
}
