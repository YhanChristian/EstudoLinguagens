%limpa tela
close all
clear all
% média e mediana
n = [ 1 2 2 2 2 3 10 ]
media_de_n = mean( n ) %Extrai média da amostra n
mediana_de_n = median( n ) %Extrai mediana amostra n

% extração de características
numElem = 32
x = rand( 1, numElem ) - 0.2;
figure( 1 )
stem( x, 'b', "linewidth", 2 )
hold on
plot( x, 'g' )
title( "sinal" )
media = mean( x )
minimo = min( x )
maximo = max( x )
desvioPadrao = std( x )
variancia = var( x )
assimetria = skewness( x )
curtose = kurtosis( x )
numCruzZero = 0;
for indElem = 2:numElem
  if( x( indElem ) * x( indElem - 1 ) < 0 )
    numCruzZero = numCruzZero + 1;
  endif
endfor
numCruzZero
potencia = x.^2;
figure( 2 )
stem( potencia, "linewidth", 2 )
title( "potencia instantanea" )
energia = sum( potencia )

% exemplo de vetor de características
vetor = [ media minimo maximo desvioPadrao assimetria numCruzZero energia ]