%Inicio 

clc;
clear all;
close all;
s = tf('s');

% Definicao de componentes

R1 = 1*(10^3);
C1 = 100*(10^(-9));
R2 = 10*(10^3);
C2 = 10*(10^(-6));
R3 = 22*(10^3);

% Impedancias

Zr1=R1;
Zc1=1/(s*C1);
Zr2=R2;
Zc2=1/(s*C2);
Zr3=R3;

% Associacoes de Impedancias
Z1=Zr1;
Z2=Zr2;
Z3=Zc1;
Z4=((Zr3*Zc2)/(Zr3+Zc2));

% l é a largura do PWM
l = 90;
PWM= 5 * [ones(1,l) zeros(1,100-l)];
Vi=PWM;
for i  = 1:100;
 Vi = cat(2,Vi,PWM);
end
fpwm= 1 /(100*0.001);
t=0:0.001:(length(Vi)-1)*0.001;

F1=(Z3/(Z1+Z3))*(Z4/(((Z1*Z3)/(Z1+Z3))+Z2+Z4));
figure(1)
lsim(F1,Vi,t)
