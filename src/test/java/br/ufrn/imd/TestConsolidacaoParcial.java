package br.ufrn.imd;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.ufrn.imd.models.Discente;
import br.ufrn.imd.models.Disciplina;
import br.ufrn.imd.models.Docente;
import br.ufrn.imd.models.Matricula;
import br.ufrn.imd.models.Turma;
import br.ufrn.imd.models.StatusAprovacao;

public class TestConsolidacaoParcial {

    @ParameterizedTest(name = "{5}")
    @CsvSource(value = {
        "100, 10.0, 10.0, 10.0, APR, Aprovado por Média",
        "100, 3.0, 10.0, 10.0, REC, Em Recuperação",
        "100, 0.0, 0.0, 0.0, REP, Reprovado por Média",
        "10, 10.0, 10.0, 10.0, REPF, Reprovado por Faltas",
        "10, 0.0, 0.0, 0.0, REPMF, Reprovado por Média e Faltas",
    })
    @DisplayName("Deve realizar a consolidação parcial através das notas e frequências informadas")
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
    
    
    @ParameterizedTest(name = "{3}")
    @CsvSource(value = {
        "-2.0, -4.0, -6.0, Teste 1",
        "12.0, 12.0, 12.0, Teste 2",
        "-5.0, 7.0, 8.0, Teste 3",
        "9.0, -3.0, 1.0, Teste 4",
        "8.0, 10.0, 15.0, Teste 5",
    })
    @DisplayName("Deve verificar as exceções causadas pela inserção de notas inválidas")
    void deveLancarExcecaoPorNotaInvalida(BigDecimal n1, BigDecimal n2, BigDecimal n3, String nomeExibido) {
        Discente discente = new Discente();
        Docente docente = new Docente();
        Disciplina disciplina = new Disciplina();
        Turma turma = new Turma(docente, disciplina);
        Matricula matricula = new Matricula(discente, turma);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	matricula.cadastrarNota1(n1);
        	matricula.cadastrarNota2(n2);
        	matricula.cadastrarNota3(n3);        	
        });
    }

    
    @ParameterizedTest(name = "{1}")
    @CsvSource(value = {
        "-6, Teste 1",
        "120, Teste 2",
        "-8, Teste 3",
        "101, Teste 4"
    })
    @DisplayName("Deve verificar as exceções causadas pela inserção da frequência inválida")
    void deveLancarExcecaoPorFrequenciaInvalida(Integer frequencia, String nomeExibido) {
        Discente discente = new Discente();
        Docente docente = new Docente();
        Disciplina disciplina = new Disciplina();
        Turma turma = new Turma(docente, disciplina);
        Matricula matricula = new Matricula(discente, turma);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	matricula.cadastrarFrequencia(frequencia);        	
        });
    }

}
