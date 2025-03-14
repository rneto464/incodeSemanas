import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite seu nome:");
        JOptionPane.showMessageDialog(null, "Bem-vindo, " + nome + "!");
    }
}
