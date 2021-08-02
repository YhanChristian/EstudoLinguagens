% Exercicio 60 - Material Apoio

n = [0:99];               %gera uma sequencia 
omega = input("omega=");  %omega = entrada (w / 2) na simula��o
x = cos(omega * n);       %gera uma cossenoide 
X = fft(x);               %calcula a transformada de Fourier
eixox = 2 * pi / 100 * n; %gera as frequ�ncias de X
figure(1);                
stem(n, abs(X));          %plota grafico m�dulo FFT eixo X
figure(2);
stem(eixox, abs(X));      %gera gr�fico m�dulo FFT eixo freq.
