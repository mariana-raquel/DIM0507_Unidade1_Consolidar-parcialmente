# Consolidação parcial das notas

## Sumário:
* **[Descrição](#descrição)**
* **[Estrutura](#estrutura-do-projeto)**
* **[Como executar?](#como-executar)**
* **[Discente](#discente)**


## Descrição:
Projeto prático da 1º Unidade para disciplina: DIM0507 -  TESTE DE SOFTWARE I  2025.2

Implementar a funcionalidade de consolidação parcial das notas de uma turma de graduação, calculando o status de aprovação dos alunos com base no desempenho acadêmico e na frequência mínima exigida, de acordo com os seguintes critérios estabelecidos no regulamento:

	1. Critérios de Aprovação Direta:
		- O aluno é considerado aprovado se:
			- Média parcial nas unidades avaliativas for igual ou superior a 6,0.
			- Nenhuma nota de unidade avaliativa for inferior a 4,0.
			- Frequência mínima de 75% da carga horária for atingida.
		* Média final: A média final do aluno aprovado será a média parcial, e o aluno estará dispensado da avaliação de reposição.

	2. Critérios para Avaliação de Reposição:
		- O aluno terá direito a realizar uma avaliação de reposição se:
			- Cumprir o critério de frequência mínima de 75%.
			- Sua média parcial for igual ou superior a 3,0.
		* A nota da avaliação de reposição substituirá a menor nota entre as unidades avaliativas do aluno.
	3. Critérios para Reprovação:
		- O aluno será reprovado se:
			- Sua média parcial for inferior a 3,0.
			- Não atingir a frequência mínima de 75%.
			- Não atender aos critérios para realizar a avaliação de reposição.
	Média final: O aluno reprovado terá sua média final igual à média parcial.

* Implemente testes automatizados utilizando o JUnit 5. Seus testes devem utilizar testes
parametrizados usando o formato CSV como entrada

* Implemente testes de exceções. Os métodos que registram notas e frequência devem ser alterados
para lançar exceção IllegalArgumentException caso o valor recebido como parâmetro esteja
fora do intervalo válido. As notas estão compreendidas entre 0 e 10 e a frequência entre 0 e 100. Em
seguida, você deve implementar testes executáveis para verificar se os métodos de fato lança esta
exceção.

* Implemente o método calcularMediaParcial() empregando o método TDD, seguindo o
processo de criar um test cobrindo um trecho da especificação, implementar a funcionalidade
correspondente, refatorar e seguir para a próxima parte da especificação. Lembre-se de rodar os
testes continuamente para garantir que sua implementação está correta. Lembre-se também de
realizar em passos pequenos.

O detalhamento do projeto encontra-se __*[aqui](src/main/resources/automatizacao_tdd.pdf)*__.


## Estrutura do projeto:

```
meu-projeto/
├── pom.xml
└── src
	├── main
	│ 	└── java/br/ufrn/imd
	│	|	└── App.java
	│	|	└── Discente.java
	│	|	└── Disciplina.java
	│	|	└── Docente.java
	│	|	└── Matricula.java
	│	|	└── StatusAprovacao.java
	│	|	└── Turma.java
	│ 	└── resources
	│		└── automatizacao_tdd.pdf
	|── test
	| 	└── java/br/ufrn/imd
	│		└── TestConsolidacaoParcial.java
```

## Como executar: 

Os testes podem ser executados tanto pela IDE, quanto pelos comandos:
```
mvn compile
mvn test
```

## Discente:
Mariana Raquel de Morais (20230033690)
