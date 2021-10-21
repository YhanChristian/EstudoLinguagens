/**
  ******************************************************************************
  * @Company    : Yhan Christian Souza Silva
  * @file       : Cellmap.h
  * @author     : Yhan Christian Souza Silva
  * @date       : 19/10/2021
  * @brief      : Arquivo header implementa��o classe CellMap
  ******************************************************************************
*/

#pragma once

/* Includes ------------------------------------------------------------------*/

#include <ctime>
#include <iostream>
#include "GameOfLife.h"

/* Define --------------------------------------------------------------------*/

#define OFF_COLOUR 0x00
#define ON_COLOUR 0xFF


// Estrutura da C�lulas

/*As c�lulas s�o armazenadas em caracteres de 8 bits, onde o 0� bit representa
o estado da c�lula e o 1� ao 4� bit representam o n�mero
de vizinhos(at� 8).O 5� ao 7� bits n�o s�o usados.
Refer�ncia: http://www.jagregory.com/abrash-black-book/images/17-03.jpg
*/

//CellMap: armazena um array de c�lulas com seus respectivos estados

class CellMap
{
public:
	CellMap(unsigned int w, unsigned int h);
	~CellMap();
	void SetCell(unsigned int x, unsigned int y);
	void ClearCell(unsigned int x, unsigned int y);
	int CellState(int x, int y);
	void NextGen();
	void Init();
private:
	unsigned char* cells;
	unsigned char* temp_cells;
	unsigned int width;
	unsigned int height;
	int length_in_bytes;
};

/*****************************END OF FILE**************************************/
