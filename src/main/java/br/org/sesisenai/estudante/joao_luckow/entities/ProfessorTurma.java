package br.org.sesisenai.estudante.joao_luckow.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "professor_turma")
public class ProfessorTurma {
    @DatabaseField(generatedId = true, columnName = "id_professor_turma")
    int id;

    @DatabaseField(columnName = "id_professor")
    int professor;

    @DatabaseField(columnName = "id_turma")
    int turma;

    public ProfessorTurma(int professor, int turma) {
        this.professor = professor;
        this.turma = turma;
    }
}
