package Puntu4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Gestion_BD {
    public Gestion_BD() {
        JFrame marco = new JFrame("Ejercicio PEP3T_4_JJ");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(400, 400);
        // Instanciacion de un objeto para la conexion a la BD
        ConexionBD conexBD = new ConexionBD();

        // Elementos de la interfaz
        JLabel titu = new JLabel("GESTION DE LA TABLA NOTAS");
        JLabel cod_matri = new JLabel("Código de la matrícula: ");
        JTextField txt_cod = new JTextField(8);
        JLabel nom_asig = new JLabel("Nombre de la asignatura: ");
        JTextField txt_asig = new JTextField(15);
        JLabel nota1 = new JLabel("Nota 1: ");
        JTextField txt_nota1 = new JTextField(5);
        JLabel nota2 = new JLabel("Nota 2: ");
        JTextField txt_nota2 = new JTextField(5);
        JButton inser = new JButton("Insertar");
        JButton modi = new JButton("Modificar");
        JButton borra = new JButton("Borrar");
        JButton consu = new JButton("Consultar");
        JLabel mensa_inser = new JLabel("Registro insertado");
        JLabel mensa_modi = new JLabel("Registro modificado");
        JLabel mensa_eli = new JLabel("Registro eliminado");
        JLabel mensa_encon = new JLabel("Registro encontrado");

        // Paneles
        JPanel pane_titu = new JPanel();
        JPanel pane_matri = new JPanel();
        JPanel pane_asig = new JPanel();
        JPanel notas = new JPanel();
        JPanel botones = new JPanel();
        JPanel mensaje = new JPanel();

        // Estilos
        Font estilo = new Font("Arial", Font.BOLD, 20);
        titu.setFont(estilo);

        // Gestión del espaciado
        marco.setLayout(new BoxLayout(marco.getContentPane(), BoxLayout.Y_AXIS));

        // Añadir los elementos a los paneles
        pane_titu.add(titu);
        pane_matri.add(cod_matri);
        pane_matri.add(txt_cod);
        pane_asig.add(nom_asig);
        pane_asig.add(txt_asig);
        notas.add(nota1);
        notas.add(txt_nota1);
        notas.add(nota2);
        notas.add(txt_nota2);
        botones.add(inser);
        botones.add(modi);
        botones.add(borra);
        botones.add(consu);

        marco.add(pane_titu);
        marco.add(pane_matri);
        marco.add(pane_asig);
        marco.add(notas);
        marco.add(botones);
        marco.add(mensaje);
        mensaje.setVisible(false);
        marco.setVisible(true);

        inser.addActionListener((ActionEvent e) -> {
            // Obtener los valores de los campos de texto
            String txtcod = txt_cod.getText();
            String txtnom = txt_asig.getText();
            String nota1Text = txt_nota1.getText();
            String nota2Text = txt_nota2.getText();

            // Comprobar campos y obtener valores de notas
            if (comprueba_campos(txtcod, txtnom, nota1Text, nota2Text)) {
                // Si se pasaron todas las validaciones, se puede proceder a insertar en la base de datos
                float not1 = Float.parseFloat(nota1Text);
                float not2 = Float.parseFloat(nota2Text);
                conexBD.insertar(txtcod, txtnom, not1, not2);
                mensaje.add(mensa_inser);
                mensaje.setVisible(true);
            }
        });
        modi.addActionListener((ActionEvent e) -> {

        });
    }

    public boolean comprueba_campos(String txtcod, String txtnom, String nota1Text, String nota2Text) {
        // Verificar si los campos están vacíos
        if (txtcod.isEmpty() || txtnom.isEmpty() || nota1Text.isEmpty() || nota2Text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Convertir las notas a números flotantes
        float not1, not2;
        try {
            not1 = Float.parseFloat(nota1Text);
            not2 = Float.parseFloat(nota2Text);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Las notas deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Verificar que las notas estén en el rango válido
        if (not1 < 0 || not1 > 10 || not2 < 0 || not2 > 10) {
            JOptionPane.showMessageDialog(null, "Las notas deben estar entre 0 y 10.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        new Gestion_BD();
    }
}
