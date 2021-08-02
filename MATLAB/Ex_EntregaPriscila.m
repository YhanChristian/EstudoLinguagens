%Ex.: Crie um sinal senoidal, com frequência de 5 Hz, amplitude de 2 V por 3
%segundos. Considere uma amostragem a uma taxa de 60 Hz. 
%Calcule a transformada de Fourier desse sinal e sua inversa e esboce o 
%seu gráfico. Use a função plot ou stem para sobrescrever a inversa no 
%sinal original.

% Definição de frequencia, tempo de amostragem 

fs = 60;
t = [0:1/fs:3];
N = length(t);
f = 5;

% Geração do sinal e soma de senóides e gráfico no tempo

sinal = (2*sin(2*pi*f*t));
subplot(2,2,1);
plot(t, sinal);
title('Sinal senoidal de 5Hz (dominio do tempo)');
xlabel('Tempo (s)');
ylabel('Amplitude (V)');

% Cálculo da transformada de Fourier e gráfico do espectro

fftx = fft(sinal);
freq = (0:N-1) * fs / N;
subplot(2,2,2);
plot(freq, abs(fftx));
title('Espectro do sinal (dominio da frequencia)');
xlabel('Frequência em (Hz)');
ylabel('Módulo da FFT');


% Cálculo da anti transformada de Fourier e gráfico no tempo

recsinal = ifft(fftx);
subplot(2,2,3);
plot(t, recsinal);
title('Anti transformada de Fourier (dominio do tempo)');
xlabel('Tempo (s)');
ylabel('Amplitude (V)');

%Gráfico do sinal de entrada e saída sobrepostos

subplot(2,2,4);
plot(t, sinal, 'r', 'linew', 2);
hold on;
subplot(2,2,4);
plot(t, recsinal, 'bo', 'markersize', 3);
title('Sinal de entrada e sinal da anti transformada sobreposto');
xlabel('Tempo (s)');
ylabel('Amplitude (V)');

