% Ex 61 material de apoio: Verifique o espectro de uma sequ�ncia x que 
% consista numa soma de duas seno�des, com diferentes frequ�ncias e 
% diferentes amplitudes. 


% Gera��o do sinal e soma de sen�ides e gr�fico

fs = 1E3;     
t= [0:1/fs:1] 
sinal = (0.5 * sin(2*pi*1E2*t)) + (1.3* sin(2*pi*5E3*t)); 
figure(1);
plot(t, sinal);

% Transformada discreta de Fourier FFT e gr�fico

X = fft(sinal);
figure(2);
plot(abs(X));