#Estatística Básica com Linguagem R
#medidas de posição

#Definindo a pasta de trabalho

setwd("D:/ProjetosPessoais/ProjetosPowerBI/Cap12") 
getwd()


#Carrega dataset Vendas.csv

vendas <- read.csv("Vendas.csv", fileEncoding = "windows-1252")

#Resumo do Dataset 

View(vendas)
str(vendas)
summary(vendas$Valor) 
summary(vendas$Custo)

# Calculo de Média e Mediana com Linguagem R

mean(vendas$Valor)
mean(vendas$Custo)

weighted.mean(vendas$Valor, w = vendas$Custo)

median(vendas$Valor)
median(vendas$Custo)

#Função para calculo da moda com o parâmetro (v)

moda <- function(v) {
  valor_unico <- unique(v)
  valor_unico[which.max(tabulate(match(v, valor_unico)))]
}

#Obtendo a Moda

resultado <- moda(vendas$Valor)
print(resultado)

resultado2 <- moda(vendas$Custo)
print(resultado2)

#Criação de Gráficos em linguagem R - uso do pacote ggplot2


install.packages("ggplot2", dependencies = TRUE, repos = "http://cran.us.r-project.org")

library(ggplot2)


#Criação de grafico
ggplot(vendas) +
  stat_summary(aes(x = Estado,
                   y = Valor),
               fun = mean,
               geom = "bar",
               fill = "blue",
               col = "grey50") +
  labs(title = "Média de Valor por Estado")


