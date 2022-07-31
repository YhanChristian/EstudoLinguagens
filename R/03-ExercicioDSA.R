#Exercicio DSA

#Exercício 1: Apresente um resumo de tipos de dados e estatísticasdo dataset.
#Exercício 2: Qual a média de cada turma?
#Exercício 3: Qual turma apresentou maior variabilidade de notas?Justifique sua resposta.
#Exercício 4 -Calcule o coeficiente de variação das 2 turmas.
#Exercício 5 -Qual nota apareceu mais vezes em cada turma?

#Aponta Caminho e Carrega Dataset

setwd("D:/ProjetosPessoais/ProjetosPowerBI/Cap12")
getwd()

notaAlunos <- read.csv("Notas.csv", fileEncoding = "windows-1252")

#Exercicio 1 - Resumo dos dados

summary(notaAlunos[c('TurmaA', 'TurmaB')])

#Exercício 2 - Média Turma

mean(notaAlunos$TurmaA)
mean(notaAlunos$TurmaB)

#Exercicio 3 - Variabilidade (Variância) (TURMA A)

var(notaAlunos$TurmaA)
var(notaAlunos$TurmaB)

#Exercicio 4 - Coefiente de Variação - Turma A maior CV

cv_A <- sd(notaAlunos$TurmaA) / mean(notaAlunos$TurmaA)
print(cv_A)
cv_B <- sd(notaAlunos$TurmaB) / mean(notaAlunos$TurmaB)
print(cv_B)

#Exercicio 5 - Moda Turma A e B

#Função para calculo da moda com o parâmetro (v)

moda <- function(v) {
  valor_unico <- unique(v)
  valor_unico[which.max(tabulate(match(v, valor_unico)))]
}

moda(notaAlunos$TurmaA)
moda(notaAlunos$TurmaB)
