import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.antlr.runtime.*;

public class Test{
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        proyectoLexer lexer = new proyectoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        proyectoParser parser = new proyectoParser(tokens);
        parser.program();
    }
}