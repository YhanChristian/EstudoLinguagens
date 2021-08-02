%Sistema RCRC com comparação em resposta ao degrau unitário
%malha aberta e malha fechada sem controlador e com controlador PI

%crio função de transferência

s = tf('s');

%Variáveis do sistema

R1 = 1*(10^3);
C1 = 100*(10^(-9));
R2 = 10*(10^3);
C2 = 10*(10^(-6));
R3 = 22*(10^3);

%Função de transferência 

G = R3/(C1*C2*R1*R2*R3*s^2+(C1*R1*R2+C1*R1*R3+C2*R1*R3+C2*R2*R3)*s+R1+R2+R3)

%Resposta em degrau para sistema em malha aberta
figure(1)
step(G)
grid

%Diagrama de Bode do sistema em malha aberta

figure(2)
bode(G)
grid

%Diagrama de Nyquist do sistema em malha aberta

figure(3)
nyquist(G)
grid

%Função de transferência em malha fechada

Gmf = feedback(G, 1)
figure(4)
step(Gmf)
grid 

%Função de transferência em malha fechada controlador PI

%Controlador PI
%KP = ganho proporcional
%KI = ganho integral
Kp = 2.7;
Ki= 3.0; 
Gc=Kp+Ki/s;

Gmfc = feedback(G * Gc, 1)

figure(5)
step(Gmfc) 
grid

% Comparativo resposta ao degrau. Sistema malha aberta
% malha fechada sem controlador e com controlador

figure(6)
step (G, Gmf, Gmfc)
grid

%Diagrama de Bode do sistema em malha fechada

figure(7)
bode(Gmf)
grid

%Diagrama de Bode do sistema em malha fechada com controlador

figure(8)
bode(Gmfc)
grid

%Diagrama de Bode do sistema em malha aberta com controlador

figure(9)
bode(G*Gc)
grid

% Localização dos polos sistema do sistema em malha aberta

figure(10)
pzmap(G)
grid
