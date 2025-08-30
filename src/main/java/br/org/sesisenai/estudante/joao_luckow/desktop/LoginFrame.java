package br.org.sesisenai.estudante.joao_luckow.desktop;

import br.org.sesisenai.estudante.joao_luckow.Main;
import br.org.sesisenai.estudante.joao_luckow.entities.Professor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginFrame extends JFrame implements ActionListener {

    private JLabel lblUsername;
    private JLabel lblPassword;

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private JButton btnLogin;

    public LoginFrame() {
        this.setTitle("SAEP Classroom | Login");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel login = new JPanel();

        lblUsername = new JLabel("Nome de usuário:");
        lblPassword = new JLabel("Senha:");
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");

        btnLogin.addActionListener(this);

        Container content = this.getContentPane();
        content.setLayout(new CardLayout());
        login.setLayout(new GridLayout());

        login.add(lblUsername);
        login.add(txtUsername);
        login.add(lblPassword);
        login.add(txtPassword);
        login.add(btnLogin);

        content.add(login);

        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Professor.checkUserPass(txtUsername.getText(), new String(txtPassword.getPassword()))) {
            JOptionPane.showMessageDialog(this, "Você se conectou!");
            try {
                Main.account = Main.databaseConnection.getTeacherDao(Main.databaseConnection.createConnection()).queryForEq("nome_professor", txtUsername.getText()).getFirst();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new MainAppFrame().setVisible(true);
            this.setVisible(false);
        }else {
            JOptionPane.showMessageDialog(this, "Senha incorreta.");
        }
    }
}
