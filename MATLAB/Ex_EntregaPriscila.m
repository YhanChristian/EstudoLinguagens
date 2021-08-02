%Ex.: Crie um sinal senoidal, com frequ�ncia de 5 Hz, amplitude de 2 V por 3
%segundos. Considere uma amostragem a uma taxa de 60 Hz. 
%Calcule a transformada de Fourier desse sinal e sua inversa e esboce o 
%seu gr�fico. Use a fun��o plot ou stem para sobrescrever a inversa no 
%sinal original.

% Defini��o de frequencia, tempo de amostragem 

fs = 60;
t = [0:1/fs:3];
N = length(t);
f = 5;

% Gera��o do sinal e soma de sen�ides e gr�fico no tempo

sinal = (2*sin(2*pi*f*t));
subplot(2,2,1);
plot(t, sinal);
title('Sinal senoidal de 5Hz (dominio do tempo)');
xlabel('Tempo (s)');
ylabel('Amplitude (V)');

% C�lculo da transformada de Fourier e gr�fico do espectro

fftx = fft(sinal);
freq = (0:N-1) * fs / N;
subplot(2,2,2);
plot(freq, abs(fftx));
title('Espectro do sinal (dominio da frequencia)');
xlabel('Frequ�ncia em (Hz)');
ylabel('M�dulo da FFT');


% C�lculo da anti transformada de Fourier e gr�fico no tempo

recsinal = ifft(fftx);
subplot(2,2,3);
plot(t, recsinal);
title('Anti transformada de Fourier (dominio do tempo)');
xlabel('Tempo (s)');
ylabel('Amplitude (V)');

%Gr�fico do sinal de entrada e sa�da sobrepostos

subplot(2,2,4);
plot(t, sinal, 'r', 'linew', 2);
hold on;
subplot(2,2,4);
plot(t, recsinal, 'bo', 'markersize', 3);
title('Sinal de entrada e sinal da anti transformada sobreposto');
xlabel('Tempo (s)');
ylabel('Amplitude (V)');

