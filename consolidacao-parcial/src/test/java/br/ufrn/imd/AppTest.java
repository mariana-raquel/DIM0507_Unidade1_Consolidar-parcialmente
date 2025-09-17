package br.ufrn.imd;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.ufrn.imd.models.Discente;
import br.ufrn.imd.models.Disciplina;
import br.ufrn.imd.models.Docente;
import br.ufrn.imd.models.Matricula;
import br.ufrn.imd.models.Turma;
import br.ufrn.imd.models.StatusAprovacao;

public class AppTest {

    @ParameterizedTest(name = "{5}")
    @CsvSource(value = {
        "100, 10.0, 10.0, 10.0, APR, Aprovado por Média",
        "10, 0.0, 0.0, 0.0, REPMF, Reprovado por Média e Faltas"
    })
    void deveRealizarAConsolidacaoParcialmente(Integer frequencia, BigDecimal n1, BigDecimal n2, BigDecimal n3, StatusAprovacao statusEsperado, String nomeExibido) {
        Discente discente = new Discente();
        Docente docente = new Docente();
        Disciplina disciplina = new Disciplina();
        Turma turma = new Turma(docente, disciplina);
        Matricula matricula = new Matricula(discente, turma);
        matricula.cadastrarFrequencia(frequencia);
        matricula.cadastrarNota1(n1);
        matricula.cadastrarNota2(n2);
        matricula.cadastrarNota3(n3);

        matricula.consolidarParcialmente();

        StatusAprovacao statusRetornado = matricula.getStatus();

        Assertions.assertEquals(statusEsperado, statusRetornado);
    }

}
