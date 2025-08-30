package br.org.sesisenai.estudante.joao_luckow.entities;

import br.org.sesisenai.estudante.joao_luckow.Main;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "atividade")
public class Atividade {
    @DatabaseField(generatedId = true, columnName = "id_atividade")
    private int id;

    @DatabaseField(canBeNull = false, columnName = "desc_atividade")
    private String desc;

    @DatabaseField(canBeNull = false, columnName = "turma_atividade", foreign = true)
    private Turma classroom;

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public Turma getClassroom() {
        return classroom;
    }

    public Atividade(Turma classroom, String desc) {
        this.classroom = classroom;
        this.desc = desc;
    }

    public Atividade() {
    }
}
