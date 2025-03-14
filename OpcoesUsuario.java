import javax.swing.JOptionPane;

public class OpcoesUsuario {
    public static void main(String[] args) {
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja continuar?", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Você escolheu SIM!");
        } else if (resposta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Você escolheu NÃO!");
        } else {
            JOptionPane.showMessageDialog(null, "Você cancelou a operação.");
        }
    }
}
