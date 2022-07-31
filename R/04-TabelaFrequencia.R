#Define pasta de trabalho

setwd("C:/Users/yhan/Documents/ProjetosPowerBI/Cap12")

#Carregando os dados

dados <- read.table("Usuarios.csv", 
                    dec = ".", 
                    sep = ",", 
                    h = T,
                    fileEncoding = "windows-1252")


#Visualizando e sumarizando os dados

View(dados)
names(dados)
str(dados)
summary(dados$salario)
summary(dados$grau_instrucao)
mean(dados$salario)
mean(dados$grau_instrucao)

#Tabela de Frequências Absolutas 

freq <- table(dados$grau_instrucao)
View(freq)

#Tabela de Frequências Relativas

freq_rel <- prop.table(freq)
View(freq_rel)

#Adiciona Linhas de Total
freq <- c(freq, sum(freq))
names(freq)[4] <- "Total"
View(freq)

#Tabela Final com todos os valores

freq_rel <- c(freq_rel, sum(freq_rel))

#Tabela final com todos os vetores

tabela_final <- cbind(freq, freq_rel = round(freq_rel,digits = 2))
View(tabela_final)


