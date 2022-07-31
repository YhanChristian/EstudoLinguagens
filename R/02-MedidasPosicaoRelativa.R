#Medidas de Posição Relativa 


setwd("D:/ProjetosPessoais/ProjetosPowerBI/Cap12")
getwd()


#Carrega dataset

vendas <- read.csv("Vendas.csv", fileEncoding = "windows-1252")


#Resumo do dataset

View(vendas)
str(vendas)

#Medidas de Tendência Central
summary(vendas$Valor)
summary(vendas[c('Valor', 'Custo')])

#Explorando variáveis númericas

mean(vendas$Valor)
median(vendas$Valor)
quantile(vendas$Valor) #Cálcula quartis Q1 (25), Q2 (50), Q3 (75), Q4 (100 )
quantile(vendas$Valor, probs = c(0.01, 0.99))
quantile(vendas$Valor, seq(from = 0, to = 1, by= 0.20))
IQR(vendas$Valor) #Diferença entre Q3 e Q1
range(vendas$Valor)
diff(range(vendas$Valor))
