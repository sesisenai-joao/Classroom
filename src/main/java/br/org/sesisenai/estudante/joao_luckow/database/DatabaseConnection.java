package br.org.sesisenai.estudante.joao_luckow.database;

import br.org.sesisenai.estudante.joao_luckow.entities.Atividade;
import br.org.sesisenai.estudante.joao_luckow.entities.Professor;
import br.org.sesisenai.estudante.joao_luckow.entities.ProfessorTurma;
import br.org.sesisenai.estudante.joao_luckow.entities.Turma;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/joaorodrigo_saep_db";
    private static String user = "root";
    private static String password = "root";

    Dao<Professor, Integer> teacherDao;
    Dao<Turma, Integer> classDao;
    Dao<ProfessorTurma, Integer> teacherClassDao;
    Dao<Atividade, Integer> activitiesDao;

    public Dao<Professor, Integer> getTeacherDao(ConnectionSource connectionSource) {
           if(teacherDao == null){
               try {
                   teacherDao = DaoManager.createDao(connectionSource, Professor.class);
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
           }
           return teacherDao;
    }

    public Dao<Turma, Integer> getClassroomDao(ConnectionSource connectionSource) {
        if(classDao == null){
            try {
                classDao = DaoManager.createDao(connectionSource, Turma.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return classDao;
    }

    public Dao<ProfessorTurma, Integer> getTeacherClassDao(ConnectionSource connectionSource) {
        if(teacherClassDao == null){
            try {
                teacherClassDao = DaoManager.createDao(connectionSource, ProfessorTurma.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return teacherClassDao;
    }

    public Dao<Atividade, Integer> getActivitiesDao(ConnectionSource connectionSource) {
        if(activitiesDao == null){
            try {
                activitiesDao = DaoManager.createDao(connectionSource, Atividade.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return activitiesDao;
    }

    public ConnectionSource createConnection() {
        try {
            return new JdbcConnectionSource(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
