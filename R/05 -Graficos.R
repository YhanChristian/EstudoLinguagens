#Define pasta de trabalho

setwd("C:/Users/yhan/Documents/ProjetosPowerBI/Cap12")
getwd()


#Dados 

vetor_total_resultados =c(3,12,5,18,45)
names(vetor_total_resultados) = c("A", "B", "C", "D", "E")
vetor_total_resultados


### Barplot ###

?barplot
barplot(vetor_total_resultados)
barplot(vetor_total_resultados, col= C(1,2,3,4,5))

#Salvando o Gráfico em disco
png("barplog.png", width = 480, height = 480)
barplot(vetor_total_resultados,
        col = rgb(0.5,0.1,0.6,0.6),
        xlab = "Categorias",
        ylab = "valores",
        main = "Barplot em R",
        ylim =c(0,60))
dev.off()

#Criação de Gráficos em linguagem R - uso do pacote ggplot2


install.packages("ggplot2", dependencies = TRUE, repos = "http://cran.us.r-project.org")

library(ggplot2)

View(mtcars)

#Barplot
ggplot(mtcars, aes(x = as.factor(cyl))) +
  geom_bar()

ggplot(mtcars, aes(x = as.factor(cyl), fill = as.factor(cyl))) +
  geom_bar() + 
  scale_fill_manual(values = c("red", "green", "blue"))

#Criando dados ficticios

dados = data.frame(group = c("A", "B", "C", "D"), values = c(33, 62, 56, 67))

#BarPlot

ggplot(dados, aes(x = group, y = values, fill = group)) +
  geom_bar(width = 0.85, stat = "identity")


### Pie Chart ###

fatias <- c(4,12,14,16,8)
paises <- c("Brasil", "Estados Unidos", "Alemanha", "Argentina", "Espanha")
pie(fatias, labels = paises, main = "Leitura de Livros por Pessoa/Ano")

install.packages("plotrix")

library(plotrix)

#Pie Chart 3D

fatias <- c(4,12,14,16,8)
paises <- c("Brasil", "Estados Unidos", "Alemanha", "Argentina", "Espanha")
pie3D(fatias, labels = paises, explode = 0.05,main = "Leitura de Livros por Pessoa/Ano")

### Line Chart ###

#Dados 

a <- c(0,1,5,3,4)
b <- c(3,5,6,1,3)

#Plot
plot(a, type="o", col = "blue", ylim = (c(1,8)))
lines(b, type="o", col ="red", pch = 22, lty =2)
title(main = "Exemplo Line Graph", col.main = "blaCK", font.main = 3)

### Boxplot ###

library(ggplot2)
View(mpg)

#Plot

ggplot(mpg, aes(x = reorder(class, hwy), y = hwy, fill = class)) +
  geom_boxplot() +
  xlab("class") +
  theme(legend.position = "none")


### Scatter plot ###

data = data.frame(cond = rep(c("condition_1", "condition_2"), each=10),
                  my_x = 1:100 + rnorm(100, sd=9), my_y = 1:00 + rnorm(100, sd=16))

ggplot(data, aes(x = my_x, y = my_y)) +
  geom_point(shape=1)

#Adiciona a linha de regressão
ggplot(data, aes(x = my_x, y = my_y)) +
  geom_point(shape = 1) +
  geom_smooth(method = lm, color ="green", se=FALSE)

#Adiciona a linha smooth - standard error = true
ggplot(data, aes(x = my_x, y = my_y)) +
  geom_point(shape = 1) +
  geom_smooth(method = lm, color ="green", se=TRUE)


### Treemap ###

install.packages("treemap")

library(treemap)

#Dados

grupo = c(rep("grupo-1", 4), rep("grupo-2", 2), rep("grupo-3", 3))
subgrupo = paste("subgroup", c(1,2,3,4,1,2,1,2,3), sep ="-")
valor = c(12,22,33,4,1,2,1,2,3)
dados = data.frame(grupo, subgrupo, valor)
View(dados)

#Labels

treemap(dados,
        index = c("grupo", "subgrupo"),
        vSize = "valor",
        type = "index",
        fontesize.labels = c(14, 10),
        fontcolor.labels = c("white", "black"),
        bg.labels = 18,
        align.labels = list(c("center", "center"), c("right", "bottom")),
        overlap.labels = 0.5,
        inflate.labels = F)

#Customizando o gráfico

treemap(dados,
        index = c("grupo", "subgrupo"),
        vSize = "valor",
        type = "index",
        border.col = c("black", "white"),
        border.lwds = c(7,2))

### Histograma ###

#Gerando valores para x

x <- mtcars$mpg

#Criando o histograma

h <- hist(x,
          breaks = 10,
          col = "blue",
          xlab = "Milhas por Galão",
          main = "Histograma com Curva de Distribuição")

#Customizando o histograma - curva de distribuição normal

xfit <- seq(min(x), max(x), length = 40)
yfit <- dnorm(xfit, mean = mean(x), sd = sd(x))
yfit <- yfit * diff(h$mids[1:2]) * length(x)
lines(xfit, yfit, col = "green", lwd = 2)

#Usando Ggplot 2

library(ggplot2)

#dataset 

dados = data.frame(value = rnorm(10000))

#Tamanho das colunas 

ggplot(dados, aes(x=value))+
  geom_histogram(binwidth = 0.05)

# Cor uniforme

ggplot(dados, aes(x=value))+
  geom_histogram(binwidth = 0.2, color="white", fill=rgb(0.2,0.6,0.1,0.3))

#Cor proporcional

ggplot(dados, aes(x=value))+
  geom_histogram(binwidth = 0.2, aes(fill= ..count..))

