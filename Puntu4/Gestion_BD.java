package Puntu4;

import javax.swing.*;
import java.awt.*;

public class Gestion_BD {
    public Gestion_BD() {
        JFrame marco = new JFrame("Ejercicio PEP3T_4_JJ");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(400,400);


        //Elementos de la interfaz
        JLabel titu = new JLabel("GESTION DE LA TABLA NOTAS");
        JLabel cod_matri = new JLabel("Código de la matricula: ");
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

        //Paneles
        JPanel pane_titu = new JPanel();
        JPanel pane_matri = new JPanel();
        JPanel pane_asig = new JPanel();
        JPanel notas = new JPanel();
        JPanel botones = new JPanel();

        //Estilos
        Font estilo = new Font("Arial", Font.BOLD, 20);
        titu.setFont(estilo);

        //Gestión del espaciado
        marco.setLayout(new BoxLayout(marco.getContentPane(), BoxLayout.Y_AXIS));

        //Añadir los elementos a los paneles
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
        marco.setVisible(true);
    }

    public static void main(String[] args) {
        new Gestion_BD();
    }
}
