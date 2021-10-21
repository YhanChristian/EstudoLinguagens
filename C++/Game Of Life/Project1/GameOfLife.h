/**
  ******************************************************************************
  * @Company    : Yhan Christian Souza Silva
  * @file       : GameOfLife.h
  * @author     : Yhan Christian Souza Silva
  * @date       : 19/10/2021
  * @brief      : Arquivo header com defines e bibliotecas utilizadas
  ******************************************************************************
*/

/* Define --------------------------------------------------------------------*/

#define SDL_MAIN_HANDLED
#define OFF_COLOUR 0x00
#define ON_COLOUR 0xFF

// Limit loop rate 
#define LIMIT_RATE 1
// Tick-rate in milliseconds (se LIMIT_RATE == 1)
#define TICK_RATE 50

/* Includes ------------------------------------------------------------------*/

#include <SDL.h>
#include <Windows.h>
#include <locale.h>
#include "CellMap.h"

/* Private function prototypes -----------------------------------------------*/

void DrawCell(unsigned int x, unsigned int y, unsigned int colour);



/*****************************END OF FILE**************************************/
