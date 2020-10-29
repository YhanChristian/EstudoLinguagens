// -- Bibliotecas --

#include <iostream>
#include <tchar.h>

// -- Função Main -- 

int main()
{
	_tsetlocale(LC_ALL, _T("portuguese"));
	int numVidas = 5;
	int score = 1350;
	std::cout << "******INICIO JOGO*******" << std::endl;
	std::cout << "Vidas jogador: " << numVidas << std::endl;
	std::cout << "Pontuação: " << score << std::endl;
	std::cout << "Tamanho variável numVidas: " << sizeof(numVidas) << "Bytes" << std::endl;
	std::cout << "Tamanho variável score: " << sizeof(score) << "Bytes" << std::endl;
	std::cout << "Endereço de memória numVidas: " << &numVidas << std::endl;
	std::cout << "Endereço de memória score: " << &score << std::endl;
	std::cout << "" << std::endl;

	std::cout << "******DURANTE O JOGO*******" << std::endl;
	numVidas--;
	score += 500;
	std::cout << "Vidas jogador: " << numVidas << std::endl;
	std::cout << "Pontuação: " << score << std::endl;
	system("PAUSE");
}