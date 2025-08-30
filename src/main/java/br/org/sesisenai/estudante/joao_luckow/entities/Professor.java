package br.org.sesisenai.estudante.joao_luckow.entities;

import br.org.sesisenai.estudante.joao_luckow.Main;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.ArrayList;

@DatabaseTable(tableName = "professor")
public class Professor {
    @DatabaseField(generatedId = true, columnName = "id_professor")
    private int id;

    @DatabaseField(canBeNull = false, columnName = "nome_professor")
    private String name;

    @DatabaseField(canBeNull = false, columnName = "senha_professor")
    private String password; // MD5 Encrypted

    public Professor() {
    }

    public Professor(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public static boolean checkUserPass(String username, String password) {
        password = DigestUtils.md5Hex(password);
        try {
            String finalPassword = password;
            System.out.println("Final Password: " + finalPassword);
            return Main.databaseConnection.getTeacherDao(Main.databaseConnection.createConnection()).queryForEq("nome_professor", username).stream().filter((el) -> el.getPassword().equals(finalPassword)).count() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Turma> getClassrooms() {
        Dao<ProfessorTurma, Integer> teacherClassDao = Main.databaseConnection.getTeacherClassDao(Main.databaseConnection.createConnection());
        Dao<Turma, Integer> classroomDao = Main.databaseConnection.getClassroomDao(Main.databaseConnection.createConnection());

        ArrayList<Turma> classrooms = new ArrayList<>();

        try {
            teacherClassDao.queryForEq("id_professor", id).forEach((professorTurma -> {
                try {
                    classrooms.add(classroomDao.queryForEq("id_turma", professorTurma.turma).get(0));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return classrooms;
    }

    public Turma createClassroom(String name) {
        Turma classroom = new Turma(name);

        Dao<Turma, Integer> classroomDao = Main.databaseConnection.getClassroomDao(Main.databaseConnection.createConnection());
        Dao<ProfessorTurma, Integer> teacherClassDao = Main.databaseConnection.getTeacherClassDao(Main.databaseConnection.createConnection());
        try {
            classroomDao.create(classroom);
            teacherClassDao.create(new ProfessorTurma(this.id, classroom.getId()));
            return classroom;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
