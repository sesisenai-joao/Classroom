package br.org.sesisenai.estudante.joao_luckow.desktop;

import br.org.sesisenai.estudante.joao_luckow.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        this.setSize(500,500);
        this.setTitle("SAEP Classroom | Logged as " + Main.account.getName());
    }
}
