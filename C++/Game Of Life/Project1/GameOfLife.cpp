/**
  ******************************************************************************
  * @Company    : Yhan Christian Souza Silva
  * @file       : GameOfLife.cpp
  * @author     : Yhan Christian Souza Silva
  * @date       : 19/10/2021
  * @brief      : Arquivo source com defines e bibliotecas utilizadas
  ******************************************************************************
*/

/* Includes ------------------------------------------------------------------*/

#include "GameOfLife.h"

/* Extern variables ---------------------------------------------------------*/

extern unsigned int seed;

/* Private variables ---------------------------------------------------------*/

// Cell map dimensões
unsigned int cellmap_width = 500;
unsigned int cellmap_height = 500;

// Width and height (in pixels) da célula
unsigned int cell_size = 1;

// Gráficos
SDL_Window *window = NULL;
SDL_Surface* surface = NULL;
unsigned int s_width = cellmap_width * cell_size;
unsigned int s_height = cellmap_height * cell_size;

int main(int argc, char *argv[])
{
	//Permite utilizar caracteres especiais
	setlocale(LC_ALL, "portuguese");

	//SDL 
	SDL_Init(SDL_INIT_VIDEO);
	window = SDL_CreateWindow("Game of Life - Conway", SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, s_width, s_height, SDL_WINDOW_SHOWN);
	surface = SDL_GetWindowSurface(window);

	//Contagem de Gerações
	unsigned long generation = 0;

	//Inicializando CellMap
	CellMap current_map(cellmap_width, cellmap_height);
	current_map.Init();

	//Event Handler SDL
	SDL_Event e;

	//Booleana para encerra jogo 
	bool quit = false;

	while (!quit)
	{
		while (SDL_PollEvent(&e) != 0)
		{
			if (e.type == SDL_QUIT)
			{
				quit = true;
			}
		}
		generation++;
		
		//Recalcula e desenha nova geração
		current_map.NextGen();

		//Atualiza frame buffer
		SDL_UpdateWindowSurface(window);

#if LIMIT_RATE
		SDL_Delay(TICK_RATE);
#endif
	}

	//Destrói  Janela
	SDL_DestroyWindow(window);

	//Encerra SDL Subsystems
	SDL_Quit();

	std::cout << "Total Gerações: " << generation
		<< "\n Seed" << seed << std::endl;

	system("pause");
	return 0;

}

void DrawCell(unsigned int x, unsigned int y, unsigned int colour)
{
	Uint8* pixel_ptr = (Uint8*)surface->pixels + (y * cell_size * s_width + x * cell_size) * 4;

	for (unsigned int i = 0; i < cell_size; i++)
	{
		for (unsigned int j = 0; j < cell_size; j++)
		{
			*(pixel_ptr + j * 4) = colour;
			*(pixel_ptr + j * 4 + 1) = colour;
			*(pixel_ptr + j * 4 + 2) = colour;
		}
		pixel_ptr += s_width * 4;
	}
}