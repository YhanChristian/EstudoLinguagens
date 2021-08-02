%Gera amostras e verifica arquivo som.wav
[ amostras, fAmostr ] = audioread( "som.wav" );
numAmostr = size( amostras, 1 );

%Le amostras e demonstra a frequencia de amostagem

printf( "Leu %d amostras de arquivo de som.\n", numAmostr )
printf( "Frequência de amostragem de %d Hz.\n", fAmostr )

%Realiza cálculo da FFT e do módulo FFT e exibe em gráfico

x = amostras';
X = fft( x );
n = [ 0:(numAmostr-1) ];
deltaW = 2 * pi / numAmostr;
w = n * deltaW;
moduloFFTX = abs( X );
stem( w, moduloFFTX, "linewidth", 2 )
[ valMax, indMax ] = max( moduloFFTX )
wDoMax = w( indMax )