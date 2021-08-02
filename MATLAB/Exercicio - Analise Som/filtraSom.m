#! /usr/bin/octave -qf
close all
angZrs = pi / 8

% primeira filtragem (filtro rejeita faixa):

z1 = exp( +j * angZrs )
z2 = exp( -j * angZrs )
p1 = 0
p2 = 0
zrs = [ z1 z2 ]
pls = [ p1 p2 ]
w = [ 0:0.01:2*pi ];
circunferencia = exp( j*w );
figure( 1 )
plot( circunferencia, 'k' )
hold on
plot( zrs, 'ob', "linewidth", 2 )
plot( 0, 0, 'xb', "linewidth", 2 )
grid
axis( "square" )
title( "zeros e polos, primeiro filtro" )
print( "filtraSom.png", "-color" )
numerador = poly( zrs )
denominador = poly( pls )
[ H, w ] = freqz( numerador, denominador );
figure( 2 )
plot( w, abs( H ) )
title( "resposta em frequencia, primeiro filtro" )
xlabel( "omega (rad)" )
ylabel( "|H|" )
print( "filtraSomRespFreq.png", "-color" )

% Como o módulo máximo de |H| se aproxima de 4,
% para se evitar saturação em arquivo de som,
% será usado K = 1/4.
           
numerador = 1/4 * numerador
[ H, w ] = freqz( numerador, denominador );
figure( 3 )
plot( w, abs( H ) )
title( "resposta em frequencia, K = 1/4" )
xlabel( "omega (rad)" )
ylabel( "|H|" )
print( "filtraSomRespFreqK.png", "-color" )
[ amostras, fAmostr ] = audioread( "som.wav" );
numAmostr = size( amostras, 1 );
printf( "Leu %d amostras de arquivo de som.\n", numAmostr )
printf( "Frequência de amostragem de %d Hz.\n", fAmostr )
x = amostras';
y = filter( numerador, denominador, x );
audiowrite( "somFiltradoZerosPi8.wav", y', fAmostr );

% segunda filtragem:

p1 = 0.8 * exp( +j * angZrs )
p2 = 0.8 * exp( -j * angZrs )
p3 = 0
z1 = +j
z2 = -j
z3 = -1
zrs = [ z1 z2 z3 ]
pls = [ p1 p2 p3 ]
figure( 4 )
plot( circunferencia, 'k' )
hold on
plot( zrs, 'ob', "linewidth", 2 )
plot( pls, 'xb', "linewidth", 2 )
grid
axis( "square" )
title( "zeros e polos, segundo filtro" )
print( "filtraSom_2.png", "-color" )
numerador = poly( zrs )
denominador = poly( pls )
[ H, w ] = freqz( numerador, denominador );
figure( 5 )
plot( w, abs( H ) )
title( "resposta em frequencia, segundo filtro" )
xlabel( "omega (rad)" )
ylabel( "|H|" )
print( "filtraSomRespFreq_2.png", "-color" )

% Como o módulo máximo de |H| se aproxima de 30,
% para se evitar saturação em arquivo de som,
% será usado K = 1/30.

numerador = 1/30 * numerador
[ H, w ] = freqz( numerador, denominador );
figure( 6 )
plot( w, abs( H ) )
title( "resposta em frequencia, segundo filtro, K = 1/30" )
xlabel( "omega (rad)" )
ylabel( "|H|" )
print( "filtraSomRespFreqK_2.png", "-color" )
y = filter( numerador, denominador, x );
audiowrite( "somFiltradoPolosPi8.wav", y', fAmostr );