#Medidas de Dispersão


setwd("D:/ProjetosPessoais/ProjetosPowerBI/Cap12")
getwd()


#Carrega dataset

vendas <- read.csv("Vendas.csv", fileEncoding = "windows-1252")


#Resumo do dataset

View(vendas)
str(vendas)
summary(vendas$Valor)

#Variancia
var(vendas$Valor)

#Desvio Padrão
sd(vendas$Valor)