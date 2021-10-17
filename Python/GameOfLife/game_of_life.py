# Implementação simplificada do Game of Life - Conway com Python 3
#Necessário a instalação do pacote pygame e numpy 

#Regras

#1. Qualquer célula viva com menos de dois vizinhos vivos morre de solidão.
#2. Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.
#3. Qualquer célula morta com exatamente três vizinhos vivos se torna uma célula viva.
#4. Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.

#Imports necessários

import pygame
import numpy as np

#Variáveis  game 

#col_about_to_die = (100, 100, 112)
#col_alive = (128, 128, 112)
#col_background = (5, 5, 20)
#col_grid = (15, 15, 30)

col_about_to_die = (200, 200, 225)
col_alive = (255, 255, 215)
col_background = (10, 10, 40)
col_grid = (30, 30, 60)
# Função de atualização gerações de acordo com as regras 

def update(surface, cur, sz):
    nxt_gen = np.zeros((cur.shape[0], cur.shape[1]))

    for r, c in np.ndindex(cur.shape):
        num_alive = np.sum(cur[r-1:r+2, c-1:c+2]) - cur[r, c]
        
        if cur[r, c] == 1 and num_alive < 2 or num_alive > 3:
            col = col_about_to_die
        elif (cur[r, c] == 1 and 2 <= num_alive <= 3) or (cur[r, c] == 0 and num_alive == 3):
            nxt_gen[r, c] = 1
            col = col_alive

        col = col if cur[r, c] == 1 else col_background
        pygame.draw.rect(surface, col, (c*sz, r*sz, sz-1, sz-1))
    
    return nxt_gen

# Função que inicia população de células

def init_game(x, y):
    cells = np.zeros((x, y))
    init_pattern = np.array([[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0],
                        [1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                        [1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                        [0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]]);
    pos = (3,3)
    cells[pos[0]:pos[0] + init_pattern.shape[0], pos[1]:pos[1] + init_pattern.shape[1]] = init_pattern

    return cells

# Função principal com passagem de parâmetros das dimensões do tabuleiro e das células

def main(x, y, size):
    pygame.init()
    surface = pygame.display.set_mode((x * size, y * size))
    pygame.display.set_caption("Jogo da Vida - Conway")
    cells = init_game(x, y)

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                return
        
        surface.fill(col_grid)
        cells = update(surface, cells, size)
        pygame.display.update()


if __name__ == "__main__":
    main(80, 80, 8)