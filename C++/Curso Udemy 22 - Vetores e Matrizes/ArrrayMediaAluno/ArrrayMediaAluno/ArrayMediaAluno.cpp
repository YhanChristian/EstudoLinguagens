#include <iostream>
#include <string>

int main()
{

	//Voc� pode suprimir o n�mero nos colchetes.
	float Notas[]{ 0.0f, 1.0f, 2.0f, 3.0f, 4.0f }; // se quiser iniciar todos com zero pode ser assim com �nico zero 0.0
	float Media, Soma{ 0.0 };
	std::string NomeAluno;

	std::cout << "\nDigite o nome do Aluno: ";
	std::cin >> NomeAluno;

	//La�o for para preenchimento das Notas 1 � 5 preenchendo os indices 0 � 4 do array.
	for (int i = 0; i <= 4; i++)
	{
		std::cout << "\nDigite a nota" << (i + 1) << ": ";
		std::cin >> Notas[i];
		Soma = Soma + Notas[i];
		system("CLS"); //Limpa a tela
	}

	Media = Soma / 5;

	std::cout << "\nA Media das notas do aluno foi: " << Media << "\n";

	system("PAUSE");
	return 0;
}