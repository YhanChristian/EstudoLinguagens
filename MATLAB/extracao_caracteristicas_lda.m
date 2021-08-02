% limpeza de variáveis e tela

close all
clear all


%Variáveis vetor x com correspondência a classe c

X = [2 3;3 4;4 5;5 6;5 7;2 1;3 2;4 2;4 3;6 4;7 6];
c = [  1;  1;  1;  1;  1;  2;  2;  2;  2;  2;  2]; 

%Encontrar indices {1, 2} de cada clase criada 
c1 = X(find(c==1),:)
c2 = X(find(c==2),:)

%Plotagem dos gráficos dos daods separados por classes (até aqqui só separei os dados)
% por 2 classes. 

figure;

p1 = plot(c1(:,1), c1(:,2), "ro", "markersize",10, "linewidth", 3); hold on;
p2 = plot(c2(:,1), c2(:,2), "go", "markersize",10, "linewidth", 3)

xlim([0 8])
ylim([0 8])

%Aplicando a  Analise do Discriminante Linear (LDA)
%Em comparação a PCA esta tecnica é mais eficiente em alguns casos, pois leva
%em consideração das classes dos dados.

%Encontrar as matrizes de dispersão entre e dentro das classes é 
%fácil para o nosso exemplo de duas classes:

classes = max(c)
mu_total = mean(X)
mu = [ mean(c1); mean(c2) ]
Sw = (X - mu(c,:))'*(X - mu(c,:))
Sb = (ones(classes,1) * mu_total - mu)' * (ones(classes,1) * mu_total - mu)

[V, D] = eig(Sw\Sb)

% Ordenando vetor de forma decrescente

[D, i] = sort(diag(D), 'descend');
V = V(:,i);

%Plota resultado do gráfico - traça reta 

scale = 5
pc1 = line([mu_total(1) - scale * V(1,1) mu_total(1) + scale * V(1,1)], [mu_total(2) - scale * V(2,1) mu_total(2) + scale * V(2,1)]);

set(pc1, 'color', [1 0 0], "linestyle", "--")

%Projeção e reconstrução

Xm = bsxfun(@minus, X, mu_total)

%Calcula projeção e reconstrução
z = Xm*V(:,1)
p = z*V(:,1)'
p = bsxfun(@plus, p, mu_total)

%Deletando p1 e p2 (plots antigos)
delete(p1);
delete(p2);

y1 = p(find(c==1),:)
y2 = p(find(c==2),:)

%plotando P1 e P2
p1 = plot(y1(:,1),y1(:,2),"ro", "markersize", 10, "linewidth", 3);
p2 = plot(y2(:,1), y2(:,2),"go", "markersize", 10, "linewidth", 3);

