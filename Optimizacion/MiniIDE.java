import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.antlr.runtime.*;

public class MiniIDE extends JFrame {
    private JTextArea codeArea;
    private JTextArea errorArea;
    private JButton loadButton, compileButton;
    private boolean hasErrors = false; // Variable para rastrear si hay errores

    public MiniIDE() {
        setTitle("Mini IDE con ANTLR");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        codeArea = new JTextArea();
        errorArea = new JTextArea();
        errorArea.setEditable(false);

        loadButton = new JButton("Cargar");
        compileButton = new JButton("Compilar");

        JPanel topPanel = new JPanel();
        topPanel.add(loadButton);
        topPanel.add(compileButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(codeArea), BorderLayout.CENTER);
        add(new JScrollPane(errorArea), BorderLayout.SOUTH);

        loadButton.addActionListener(e -> loadFile());
        compileButton.addActionListener(e -> compileCode());

        // Redirigir la salida de errores de System.err a errorArea
        redirectSystemErrToErrorArea();
    }

    private void redirectSystemErrToErrorArea() {
        PrintStream errorStream = new PrintStream(new OutputStream() {
            private StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {
                buffer.append((char) b);
                if (b == '\n') {
                    SwingUtilities.invokeLater(() -> {
                        errorArea.append(buffer.toString());
                        hasErrors = true; // Marcar que hubo errores
                        buffer.setLength(0);
                    });
                }
            }
        });
        System.setErr(errorStream);
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                codeArea.read(br, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void compileCode() {
        String code = codeArea.getText();
        errorArea.setText(""); // Limpiar el área de errores antes de compilar
        hasErrors = false; // Reiniciar el estado de errores

        try {
            ANTLRStringStream input = new ANTLRStringStream(code);
            proyectoLexer lexer = new proyectoLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            proyectoParser parser = new proyectoParser(tokens);
            parser.program();

            // Esperar un poco para asegurar que los errores capturados sean reflejados
            SwingUtilities.invokeLater(() -> {
                if (!hasErrors) {
                    errorArea.setText("Compilación exitosa. No se encontraron errores.");
                }
            });

        } catch (Exception e) {
            errorArea.append("Error durante la compilación: " + e.getMessage() + "\n");
            hasErrors = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IDE().setVisible(true));
    }
}
