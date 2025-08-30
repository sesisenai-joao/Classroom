package br.org.sesisenai.estudante.joao_luckow.entities;

import br.org.sesisenai.estudante.joao_luckow.Main;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "turma")
public class Turma {
    @DatabaseField(generatedId = true, columnName = "id_turma")
    private int id;

    @DatabaseField(canBeNull = false, columnName = "nome_turma")
    private String name;

    public Turma() {
    }

    public Turma(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void deleteById(int id) {
        Dao<ProfessorTurma, Integer> teacherClassDao = Main.databaseConnection.getTeacherClassDao(Main.databaseConnection.createConnection());
        Dao<Turma, Integer> classroomDao = Main.databaseConnection.getClassroomDao(Main.databaseConnection.createConnection());
        try {
            teacherClassDao.queryForEq("id_turma", id).forEach((x) -> {
                try {
                    teacherClassDao.deleteById(x.id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            classroomDao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Turma getById(int id) {
        Dao<Turma, Integer> classroomDao = Main.databaseConnection.getClassroomDao(Main.databaseConnection.createConnection());
        try {
            return classroomDao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Turma getByName(String name) {
        Dao<Turma, Integer> classroomDao = Main.databaseConnection.getClassroomDao(Main.databaseConnection.createConnection());
        try {
            return classroomDao.queryForEq("nome_turma", name).getFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteByName(String name) {
        Dao<Turma, Integer> classroomDao = Main.databaseConnection.getClassroomDao(Main.databaseConnection.createConnection());
        try {
            deleteById(classroomDao.queryForFirst(classroomDao.queryBuilder().where().eq("nome_turma", name).prepare()).getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Professor> getTeachers() {
        Dao<ProfessorTurma, Integer> teacherClassDao = Main.databaseConnection.getTeacherClassDao(Main.databaseConnection.createConnection());
        Dao<Professor, Integer> teacherDao = Main.databaseConnection.getTeacherDao(Main.databaseConnection.createConnection());

        ArrayList<Professor> teachers = new ArrayList<>();

        try {
            teacherClassDao.queryForEq("id_turma", id).forEach((professorTurma -> {
                try {
                    teachers.add(teacherDao.queryForEq("id_professor", professorTurma.professor).get(0));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return teachers;
    }

    public List<Atividade> getActivities() {
        Dao<Atividade, Integer> activitiesDao = Main.databaseConnection.getActivitiesDao(Main.databaseConnection.createConnection());
        try {
            return activitiesDao.queryForEq("turma_atividade", this.id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Atividade createActivity(String desc) {
        Atividade activity = new Atividade(this, desc);

        Dao<Atividade, Integer> activitiesDao = Main.databaseConnection.getActivitiesDao(Main.databaseConnection.createConnection());
        try {
            activitiesDao.create(activity);
            return activity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
