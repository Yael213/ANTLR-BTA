import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import org.antlr.runtime.*;

public class IDE extends JFrame {
    private JTextArea codeArea;
    private JTextArea errorArea;
    private JTextArea optimizationArea;

    private JButton loadButton, compileButton;
    private boolean hasErrors = false;

    public IDE() {
        setTitle("ANTLR IDE - Light Mode");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Paleta de colores para modo claro
        Color lightBackground = new Color(240, 240, 245);
        Color panelBackground = new Color(250, 250, 255);
        Color accentColor = new Color(0, 120, 215);
        Color darkText = new Color(50, 50, 50);
        Color greenAccent = new Color(0, 158, 71);
        Color errorColor = new Color(200, 50, 50);
        
        // Area para insertar el codigo
        codeArea = new JTextArea();
        codeArea.setFont(new Font("Fira Code", Font.PLAIN, 15));
        codeArea.setBackground(panelBackground);
        codeArea.setForeground(darkText);
        codeArea.setCaretColor(accentColor);
        codeArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);
        
        // Area pata mostrar errores
        errorArea = new JTextArea();
        errorArea.setEditable(false);
        errorArea.setFont(new Font("Fira Code", Font.PLAIN, 13));
        errorArea.setForeground(errorColor);
        errorArea.setBackground(panelBackground);
        errorArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 230)), "Errores"),
            new EmptyBorder(5, 5, 5, 5)
        ));
        
        // Area donde se muestra las funciones a optimizar
        optimizationArea = new JTextArea();
        optimizationArea.setEditable(false);
        optimizationArea.setFont(new Font("Fira Code", Font.PLAIN, 13));
        optimizationArea.setForeground(greenAccent);
        optimizationArea.setBackground(panelBackground);
        optimizationArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 230)), "Optimización"),
            new EmptyBorder(5, 5, 5, 5)
        ));
        
        ImageIcon loadIcon = resizeIcon(new ImageIcon("icons/open.png"), 18, 18);
        ImageIcon compileIcon = resizeIcon(new ImageIcon("icons/compile.png"), 18, 18);
        
        // Botones
        
        loadButton = new JButton(" Cargar", loadIcon);
        compileButton = new JButton(" Compilar", compileIcon);
        
        for (JButton button : new JButton[]{loadButton, compileButton}) {
            button.setFocusPainted(false);
            button.setBackground(panelBackground);
            button.setForeground(darkText);
            button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 210)),
                new EmptyBorder(5, 10, 5, 10)
            ));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(lightBackground);
        topPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        topPanel.add(loadButton);
        topPanel.add(Box.createHorizontalStrut(5));
        topPanel.add(compileButton);
        
        JSplitPane errorAndOptPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(errorArea),
                new JScrollPane(optimizationArea));
        errorAndOptPane.setResizeWeight(0.5);
        errorAndOptPane.setDividerSize(5);
        errorAndOptPane.setBackground(lightBackground);
        errorAndOptPane.setBorder(BorderFactory.createEmptyBorder());
        
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(codeArea),
                errorAndOptPane);
        mainSplitPane.setResizeWeight(0.7);
        mainSplitPane.setDividerSize(5);
        mainSplitPane.setBackground(lightBackground);
        mainSplitPane.setBorder(BorderFactory.createEmptyBorder());
        
        add(topPanel, BorderLayout.NORTH);
        add(mainSplitPane, BorderLayout.CENTER);
        
        loadButton.addActionListener(e -> loadFile());
        compileButton.addActionListener(e -> compileCode());
        
        redirectSystemErrToErrorArea();
        redirectSystemOutToOptimizationArea();

        customizeScrollBarsForLightMode();
    }
    
    private void customizeScrollBarsForLightMode() {
        UIManager.put("ScrollBar.thumb", new Color(200, 200, 210));
        UIManager.put("ScrollBar.track", new Color(240, 240, 245));
        UIManager.put("ScrollBar.width", 12);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
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
                        hasErrors = true;
                        buffer.setLength(0);
                    });
                }
            }
        });
        System.setErr(errorStream);
    }

    private void redirectSystemOutToOptimizationArea() {
        PrintStream outStream = new PrintStream(new OutputStream() {
            private StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {
                buffer.append((char) b);
                if (b == '\n') {
                    SwingUtilities.invokeLater(() -> {
                        optimizationArea.append(buffer.toString());
                        buffer.setLength(0);
                    });
                }
            }
        });
        System.setOut(outStream);
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
        errorArea.setText("");
        optimizationArea.setText("");
        hasErrors = false;

        try {
            ANTLRStringStream input = new ANTLRStringStream(code);
            proyectoLexer lexer = new proyectoLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            proyectoParser parser = new proyectoParser(tokens);
            parser.program();

            SwingUtilities.invokeLater(() -> {
                if (!hasErrors) {
                    errorArea.append(">>MyCompiler>> No se encontraron errores.\n");
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
