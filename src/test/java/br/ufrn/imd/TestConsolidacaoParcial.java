package br.ufrn.imd;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

	private static Discente discente;
	private static Docente docente;
    private static Disciplina disciplina;
    private static Turma turma;
	
    @BeforeAll
    public static void setUp() {
    	
    	discente = new Discente();
    	discente.setNome("Aluno");
    	discente.setMatricula(123456789);
    	
    	docente = new Docente();
        docente.setNome("Professor");
        docente.setSiape(1234);
        
        disciplina = new Disciplina();
        disciplina.setNome("TESTE DE SOFTWARE I");
        disciplina.setCodigo("DIM0507");
        
        turma = new Turma(docente, disciplina);
    	
    }
    
	
    @ParameterizedTest(name = "{5}")
    @CsvSource(value = {
        "100, 10.0, 10.0, 10.0, APR, Aprovado por Média 1",
        "75, 6.0, 6.0, 6.0, APR, Aprovado por Média 2",
        "90, 7.0, 7.0, 4.0, APR, Aprovado por Média 3",
        "100, 3.0, 10.0, 10.0, REC, Em Recuperação 1",
        "80, 2.0, 3.0, 4.0, REC, Em Recuperação 2",
        "75, 4.0, 4.0, 1.0, REC, Em Recuperação 3",
        "100, 0.0, 0.0, 0.0, REP, Reprovado por Média 1",
        "75, 3.0, 3.0, 3.0, REP, Reprovado por Média 2",
        "10, 10.0, 10.0, 10.0, REPF, Reprovado por Faltas 1",
        "10, 10.0, 10.0, 10.0, REPF, Reprovado por Faltas 2",
        "10, 0.0, 0.0, 0.0, REPMF, Reprovado por Média e Faltas 1",
        "10, 0.0, 0.0, 0.0, REPMF, Reprovado por Média e Faltas 2",
    })
    @DisplayName("Deve realizar a consolidação parcial através das notas e frequências informadas")
    void deveRealizarAConsolidacaoParcialmente(Integer frequencia, BigDecimal n1, BigDecimal n2, BigDecimal n3, StatusAprovacao statusEsperado, String nomeExibido) {
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
        "-2.0, -4.0, -6.0, Todas as notas abaixo do mínimo",
        "12.0, 12.0, 12.0, Todas as notas acima do máximo",
        "-5.0, 7.0, 8.0, Apenas nota 1 abaixo do mínimo",
        "9.0, -3.0, 1.0, Apenas nota 2 abaixo do mínimo",
        "8.0, 10.0, -10.0, Apenas nota 3 abaixo do mínimo",
        "-5.0, 7.0, 8.0, Apenas nota 1 abaixo do mínimo",
        "9.0, -3.0, 1.0, Apenas nota 2 abaixo do mínimo",
        "8.0, 10.0, -10.0, Apenas nota 3 abaixo do mínimo",
    })
    @DisplayName("Deve verificar as exceções causadas pela inserção de notas inválidas")
    void deveLancarExcecaoPorNotaInvalida(BigDecimal n1, BigDecimal n2, BigDecimal n3, String nomeExibido) {
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
    	Matricula matricula = new Matricula(discente, turma);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	matricula.cadastrarFrequencia(frequencia);        	
        });
    }

}
