/**
  ******************************************************************************
  * @Company    : Yhan Christian Souza Silva
  * @file       : Cellmap.cpp
  * @author     : Yhan Christian Souza Silva
  * @date       : 19/10/2021
  * @brief      : Arquivo source implementação classe CellMap
  ******************************************************************************
*/

/* Includes ------------------------------------------------------------------*/

#include "CellMap.h"

/* Private variables ---------------------------------------------------------*/

// Randomisation seed
unsigned int seed;

/* Methods -------------------------------------------------------------------*/

CellMap::CellMap(unsigned int w, unsigned int h)
{

}

CellMap::~CellMap()
{
	delete[] cells;
	delete[] temp_cells;
}

void CellMap::SetCell(unsigned int x, unsigned int y)
{
	int w = width, h = height;
	int xoleft, xoright, yoabove, yobelow;
	unsigned char *cell_ptr = cells + (y * w) + x;

	//Calcula o offset para as 8 células vizinhas
	//Contabilizando o envolvendo nas bordas do CellMap

	xoleft = (x == 0) ? w - 1 : -1;
	xoright = (x == (w - 1)) ? -(w - 1) : 1;
	yoabove = (y == 0) ? length_in_bytes - w : -w;
	yobelow = (y == (h - 1)) ? -(length_in_bytes - w) : w;

	//Seta primeiro bit
	*(cell_ptr) |= 0x01;

	//Altera bits sucessivos para contagens de vizinhos

	*(cell_ptr + yoabove + xoleft) += 0x02;
	*(cell_ptr + yoabove) += 0x02;
	*(cell_ptr + yoabove + xoright) += 0x02;
	*(cell_ptr + xoleft) += 0x02;

	*(cell_ptr + xoright) += 0x02;
	*(cell_ptr + yobelow + xoleft) += 0x02;
	*(cell_ptr + yobelow) += 0x02;
	*(cell_ptr + yobelow + xoright) += 0x02;

}

void CellMap::ClearCell(unsigned int x, unsigned int y)
{
	int w = width, h = height;
	int xoleft, xoright, yoabove, yobelow;
	unsigned char *cell_ptr = cells + (y * w) + x;

	//Calcula o offset para as 8 células vizinhas
	//Contabilizando o envolvendo nas bordas do CellMap

	xoleft = (x == 0) ? w - 1 : -1;
	xoright = (x == (w - 1)) ? -(w - 1) : 1;
	yoabove = (y == 0) ? length_in_bytes - w : -w;
	yobelow = (y == (h - 1)) ? -(length_in_bytes - w) : w;

	//Limpa primeiro Bit 
	*(cell_ptr) &= ~0x01;

	//Altera bits sucessivos para contagens de vizinhos

	*(cell_ptr + yoabove + xoleft) -= 0x02;
	*(cell_ptr + yoabove) -= 0x02;
	*(cell_ptr + yoabove + xoright) -= 0x02;
	*(cell_ptr + xoleft) -= 0x02;

	*(cell_ptr + xoright) -= 0x02;
	*(cell_ptr + yobelow + xoleft) -= 0x02;
	*(cell_ptr + yobelow) -= 0x02;
	*(cell_ptr + yobelow + xoright) -= 0x02;
}

int CellMap::CellState(int x, int y)
{
	unsigned char *cell_ptr = cells + (y * width) + x;
	//Retorna o primeiro bit (LSB: estado célula armazenado)
	return *cell_ptr & 0x01;
}

void CellMap::NextGen()
{
	unsigned int x, y, count;
	unsigned int w = width, h = height;
	unsigned char *cell_ptr;

	//Copia temp map para manter uma cópia inalterada
	memcpy(temp_cells, cells, length_in_bytes);

	//Processa as células de acordo com CellMap

	cell_ptr = temp_cells;

	for (y = 0; y < h; y++)
	{
		x = 0;
		do
		{
			//Bytes zerados não possui vizinhos, pular 
			while (*cell_ptr == 0)
			{
				cell_ptr++;
				if (++x >= w)
				{
					goto RowDone; //Se acabar a varredura de células encerra loop
				}
			}
			
			//Células restantes estão ativadas ou possuem vizinhos
			count = *cell_ptr >> 1;

			if (*cell_ptr & 0x01)
			{
				//Célula morre se n tiver 2 ou 3 vizinhos
				if ((count != 2) && (count != 3))
				{
					ClearCell(x, y);
					DrawCell(x, y, OFF_COLOUR);
				}
			}
			else
			{
				//Célula deve "ligar" com 3 vizinhos
				if (count == 3)
				{
					SetCell(x, y);
					DrawCell(x, y, ON_COLOUR);
				}
			}
			//Avança para a próxima célula
			cell_ptr++;

		} while (++x < w);
	RowDone:; // Finaliza loop
	}
}

void CellMap::Init()
{
	unsigned int x, y, init_length;

	//Random if 0
	seed = (unsigned)time(NULL);

	//Inicializa de forma randomica cellmap com 50% de pixels
	std::cout << "Inicializando" << std::endl;

	srand(seed);

	init_length = (width * height) / 2;

	do {
		x = rand() % (width - 1);
		y = rand() % (height - 1);
		if (CellState(x, y) == 0)
		{
			SetCell(x, y);
		}
	} while (--init_length);

}