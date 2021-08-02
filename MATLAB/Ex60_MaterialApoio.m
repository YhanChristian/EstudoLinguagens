% Exercicio 60 - Material Apoio

n = [0:99];               %gera uma sequencia 
omega = input("omega=");  %omega = entrada (w / 2) na simulação
x = cos(omega * n);       %gera uma cossenoide 
X = fft(x);               %calcula a transformada de Fourier
eixox = 2 * pi / 100 * n; %gera as frequências de X
figure(1);                
stem(n, abs(X));          %plota grafico módulo FFT eixo X
figure(2);
stem(eixox, abs(X));      %gera gráfico módulo FFT eixo freq.
