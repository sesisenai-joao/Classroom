package br.org.sesisenai.estudante.joao_luckow;

import br.org.sesisenai.estudante.joao_luckow.database.DatabaseConnection;
import br.org.sesisenai.estudante.joao_luckow.desktop.NewLoginFrame;
import br.org.sesisenai.estudante.joao_luckow.entities.Professor;
public class Main {

    public static DatabaseConnection databaseConnection;
    public static Professor account;

    public static void main(String[] args) {
        databaseConnection = new DatabaseConnection();

        databaseConnection.createConnection();
        new NewLoginFrame().setVisible(true);
    }
}