// -- Bibliotecas --

#include <iostream>
#include <tchar.h>

// -- Fun��o Main -- 

int main()
{
	_tsetlocale(LC_ALL, _T("portuguese"));
	int numVidas = 5;
	int score = 1350;
	std::cout << "******INICIO JOGO*******" << std::endl;
	std::cout << "Vidas jogador: " << numVidas << std::endl;
	std::cout << "Pontua��o: " << score << std::endl;
	std::cout << "Tamanho vari�vel numVidas: " << sizeof(numVidas) << "Bytes" << std::endl;
	std::cout << "Tamanho vari�vel score: " << sizeof(score) << "Bytes" << std::endl;
	std::cout << "Endere�o de mem�ria numVidas: " << &numVidas << std::endl;
	std::cout << "Endere�o de mem�ria score: " << &score << std::endl;
	std::cout << "" << std::endl;

	std::cout << "******DURANTE O JOGO*******" << std::endl;
	numVidas--;
	score += 500;
	std::cout << "Vidas jogador: " << numVidas << std::endl;
	std::cout << "Pontua��o: " << score << std::endl;
	system("PAUSE");
}