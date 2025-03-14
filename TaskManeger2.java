import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskManeger2 {

    private static final Logger logger = Logger.getLogger(TaskManeger2.class.getName());

    private static class Task {
        private final String description;
        private final String deadline;
        private boolean completed;

        public Task(String description, String deadline) {
            this.description = description;
            this.deadline = deadline;
            this.completed = false;
        }

        public void markAsCompleted() {
            this.completed = true;
        }

        public String getDescription() {
            return description;
        }

        public String getDeadline() {
            return deadline;
        }

        public boolean isCompleted() {
            return completed;
        }

        public String toString() {
            String status = completed ? "[✔]" : "[ ]";
            String deadlineInfo = (deadline != null && !deadline.isEmpty()) ? " (Data limite: " + deadline + ")" : "";
            return status + " " + description + deadlineInfo;
        }
    }

    private static final List<Task> tasks = new ArrayList<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static void addTask() {
        String description = JOptionPane.showInputDialog("Digite a descrição da tarefa:");
        if (description != null && !description.trim().isEmpty()) {
            String deadline = JOptionPane.showInputDialog("Digite a data limite (dd/MM/yyyy) (opcional):");
            if (deadline != null && !deadline.trim().isEmpty()) {
                try {
                    dateFormat.parse(deadline);
                } catch (ParseException e) {
                    logger.log(Level.WARNING, "Formato de data inválido: " + deadline, e);
                    JOptionPane.showMessageDialog(null, "Formato de data inválido! Use dd/MM/yyyy.");
                    return;
                }
            }
            tasks.add(new Task(description, deadline));
            JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "A descrição não pode ser vazia.");
        }
    }

    private static void listTasks() {
        StringBuilder list = new StringBuilder("======= Lista de Tarefas =======\n");
        for (Task task : tasks) {
            list.append(task.toString()).append("\n");
        }
        list.append("================================");

        JTextArea textArea = new JTextArea(list.toString());
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Tarefas", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void completeTask() {
        String input = JOptionPane.showInputDialog("Digite o nome da tarefa concluída:");
        if (input != null && !input.trim().isEmpty()) {
            boolean found = false;
            for (Task task : tasks) {
                if (task.getDescription().equalsIgnoreCase(input.trim())) {
                    task.markAsCompleted();
                    JOptionPane.showMessageDialog(null, "Tarefa marcada como concluída!");
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Tarefa não encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A descrição não pode ser vazia.");
        }
    }

    private static void checkDeadlines() {
        new Thread(() -> {
            while (true) {
                Date today = new Date();
                for (Task task : tasks) {
                    if (!task.isCompleted() && task.getDeadline() != null && !task.getDeadline().isEmpty()) {
                        try {
                            Date deadline = dateFormat.parse(task.getDeadline());
                            long difference = deadline.getTime() - today.getTime();
                            long daysToDeadline = difference / (1000 * 60 * 60 * 24);

                            if (daysToDeadline == 1) {
                                JOptionPane.showMessageDialog(null,
                                        "A tarefa \"" + task.getDescription() + "\" está chegando ao prazo (amanhã).",
                                        "Aviso de Prazo", JOptionPane.WARNING_MESSAGE);
                            } else if (daysToDeadline == 0) {
                                JOptionPane.showMessageDialog(null,
                                        "Hoje é o prazo para a tarefa: \"" + task.getDescription() + "\".",
                                        "Prazo Final", JOptionPane.WARNING_MESSAGE);
                            }

                        } catch (ParseException e) {
                            logger.log(Level.SEVERE, "Erro ao analisar a data limite da tarefa: " + task.getDescription(), e);
                        }
                    }
                }
                try {
                    Thread.sleep(60000);  // Verifica a cada 60 segundos
                } catch (InterruptedException e) {
                    logger.log(Level.WARNING, "A thread de verificação de prazos foi interrompida.", e);
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        checkDeadlines();
        while (true) {
            String[] options = {"Adicionar Tarefa", "Listar Tarefas", "Concluir Tarefa", "Sair"};
            int choice = JOptionPane.showOptionDialog(null, "Selecione uma opção:", "Gerenciador de Tarefas",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0 -> addTask();
                case 1 -> listTasks();
                case 2 -> completeTask();
                case 3 -> System.exit(0);
                default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }
}