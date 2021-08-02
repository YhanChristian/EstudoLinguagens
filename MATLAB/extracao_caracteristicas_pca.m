% limpeza de vari�veis e tela
% PCA em resumo aponta a dire��o onde os pontos mais se espalham em uma dada amostra


close all
clear all

%Vari�veis vetor x com correspond�ncia a classe c

X = [2 3;3 4;4 5;5 6;5 7;2 1;3 2;4 2;4 3;6 4;7 6];
c = [  1;  1;  1;  1;  1;  2;  2;  2;  2;  2;  2]; 

%Encontrar indices {1, 2} de cada clase criada 
c1 = X(find(c==1),:)
c2 = X(find(c==2),:)

%Plotagem dos gr�ficos dos daods separados por classes

figure;

p1 = plot(c1(:,1), c1(:,2), "ro", "markersize",10, "linewidth", 3); hold on;
p2 = plot(c2(:,1), c2(:,2), "go", "markersize",10, "linewidth", 3)

xlim([0 8])
ylim([0 8])

%Aplicando a An�lise de componentes principais (PCA) e reduz qtde de eixo para 1

mu = mean(X)
Xm = bsxfun(@minus, X, mu)
C = cov(Xm)
[V,D] = eig(C)

% Ordenando vetor de forma decrescente
[D, i] = sort(diag(D), 'descend');
V = V(:,i);

%Plotando vetores 
%Ele produz um novo sistema de coordenadas com a m�dia como 
%origem e os componentes principais ortogonais como eixos:

scale = 5
pc1 = line([mu(1) - scale * V(1,1) mu(1) + scale * V(1,1)], [mu(2) - scale * V(2,1) mu(2) + scale * V(2,1)]);
pc2 = line([mu(1) - scale * V(1,2) mu(1) + scale * V(1,2)], [mu(2) - scale * V(2,2) mu(2) + scale * V(2,2)]);

set(pc1, 'color', [1 0 0], "linestyle", "--")
set(pc2, 'color', [0 1 0], "linestyle", "--")

% Projetando em PC1

z = Xm*V(:,1)

% Reconstruindo

p = z*V(:,1)'
p = bsxfun(@plus, p, mu)


%Deletando p1 e p2 (plots antigos)
delete(p1);
delete(p2);

y1 = p(find(c==1),:)
y2 = p(find(c==2),:)

%plotando P1 e P2
p1 = plot(y1(:,1),y1(:,2),"ro", "markersize", 10, "linewidth", 3);
p2 = plot(y2(:,1), y2(:,2),"go", "markersize", 10, "linewidth", 3); 

