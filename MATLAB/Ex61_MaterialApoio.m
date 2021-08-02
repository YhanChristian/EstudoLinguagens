% Ex 61 material de apoio: Verifique o espectro de uma sequência x que 
% consista numa soma de duas senoídes, com diferentes frequências e 
% diferentes amplitudes. 


% Geração do sinal e soma de senóides e gráfico

fs = 1E3;     
t= [0:1/fs:1] 
sinal = (0.5 * sin(2*pi*1E2*t)) + (1.3* sin(2*pi*5E3*t)); 
figure(1);
plot(t, sinal);

% Transformada discreta de Fourier FFT e gráfico

X = fft(sinal);
figure(2);
plot(abs(X));