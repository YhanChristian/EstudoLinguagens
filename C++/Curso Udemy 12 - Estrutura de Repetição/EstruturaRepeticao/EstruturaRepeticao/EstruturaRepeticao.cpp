#include <iostream>
int main()
{
	int Numero;
	Numero = 0;
	//loop infitivo!
	/* neste caso Numero nunca deixar� de ser 0 e a
	condi��o Numero <= 50 ser� sempre verdadeira!
	Logo ficar� sempre no While!!!*/
	while (Numero <= 50)
	{

		std::cout << Numero << " ";
		//De alguma forma voc� precisa incrementar a vari�vel Numero para que ela chegue at� 50
		//Qual condi��o de Parada do loop?
		Numero++;
	}
	system("PAUSE");
	return 0;
}