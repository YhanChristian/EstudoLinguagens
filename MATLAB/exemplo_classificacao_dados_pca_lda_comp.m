
%limpeza de dados

close all 
clear all

function Z = zscore(X)
  Z = bsxfun(@rdivide, bsxfun(@minus, X, mean(X)), std(X));
end

function [D, W_pca] = pca(X) 
  mu = mean(X);
  Xm = bsxfun(@minus, X ,mu);
  C = cov(Xm);
  [W_pca,D] = eig(C);
  [D, i] = sort(diag(D), 'descend');
  W_pca = W_pca(:,i);
end

function [D, W_lda] = lda(X,y) 
  dimension = columns(X);
  labels = unique(y);
  C = length(labels);
  Sw = zeros(dimension,dimension);
  Sb = zeros(dimension,dimension);
  mu = mean(X);

  for i = 1:C
    Xi = X(find(y == labels(i)),:);
    n = rows(Xi);
    mu_i = mean(Xi);
    XMi = bsxfun(@minus, Xi, mu_i);
    Sw = Sw + (XMi' * XMi );
    MiM =  mu_i - mu;
    Sb = Sb + n * MiM' * MiM; 
  endfor

  [W_lda, D] = eig(Sw\Sb);
  [D, i] = sort(diag(D), 'descend');
  W_lda = W_lda(:,i);
end

function X_proj = project(X, W)
  X_proj = X*W;
end

function X = reconstruct(X_proj, W)
  X = X_proj * W';
end

% http://archive.ics.uci.edu/ml/datasets/Wine
data = dlmread("wine.data",",");
y = data(:,1);
X = data(:,2:end);

%Aplicando PCA através da chamada de funções

[DPca, Wpca] = pca(X);
Xm = bsxfun(@minus, X, mean(X));
Xproj = project(Xm, Wpca(:,1:2));

%Plotagem de dados PCA - resultado não muito bom na separação

wine1 = Xproj(find(y==1),:);
wine2 = Xproj(find(y==2),:);
wine3 = Xproj(find(y==3),:);

figure;
plot(wine1(:,1), wine1(:,2),"ro", "markersize", 10, "linewidth", 3); hold on;
plot(wine2(:,1), wine2(:,2),"go", "markersize", 10, "linewidth", 3);
plot(wine3(:,1), wine3(:,2),"bo", "markersize", 10, "linewidth", 3);
title("PCA (original data)")

%Aplicando LDA através da chamada de funções e projetando dados

[D, W_lda] = lda(X,y);
Xproj = project(Xm, W_lda(:,1:2));

%Normalização de dados
Xm = zscore(X);

%Plotagem de dados LDA- resultado melhor

wine1 = Xproj(find(y==1),:);
wine2 = Xproj(find(y==2),:);
wine3 = Xproj(find(y==3),:);

figure;
plot(wine1(:,1), wine1(:,2),"ro", "markersize", 10, "linewidth", 3); hold on;
plot(wine2(:,1), wine2(:,2),"go", "markersize", 10, "linewidth", 3);
plot(wine3(:,1), wine3(:,2),"bo", "markersize", 10, "linewidth", 3);
title("LDA (original data)")

