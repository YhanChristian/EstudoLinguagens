# Hangman Game (Jogo da Forca) 
# Programação Orientada a Objetos

# Import
import random

# Board (tabuleiro)
board = ['''

>>>>>>>>>>Hangman<<<<<<<<<<

+---+
|   |
    |
    |
    |
    |
=========''', '''

+---+
|   |
O   |
    |
    |
    |
=========''', '''

+---+
|   |
O   |
|   |
    |
    |
=========''', '''

 +---+
 |   |
 O   |
/|   |
     |
     |
=========''', '''

 +---+
 |   |
 O   |
/|\  |
     |
     |
=========''', '''

 +---+
 |   |
 O   |
/|\  |
/    |
     |
=========''', '''

 +---+
 |   |
 O   |
/|\  |
/ \  |
     |
=========''']


# Classe
class Hangman:

	# Método Construtor
	def __init__(self, word):
		self.failed_attemps = 0
		self.word = word
		self.game_progress = list('_' * len(self.word))
		
		
	# Método para adivinhar a letra
	def guess(self, letter):
		return [i for i, char in enumerate(self.word) if letter == char]

	
	#Método para verificar se entrada é válida (letra)
	#:param input_: String, user input to be validated
	def is_invalid_letter(self, input_):
		return input_.isdigit() or (input_.isalpha() and len(input_) > 1)

	#Método para atualizar game com letras 
	def update_progress(self, letter, indexes):
		for index in indexes:
			self.game_progress[index] = letter
		
	#Método para obter entrada digitada pelo usuário	
	def get_user_input(self):
		user_input = input('\nDigite uma letra: ')
		return user_input
	
	#Método para exibir palavras em branco
	def print_game_status(self):
		print('\n')
		print('\n'.join(board[:self.failed_attemps]))
		print('\n')
		print(' '.join(self.game_progress))


	#Método para exibir mensagem de vencedor
	def hangman_won_game(self):
		print("Parabéns, você ganhou!")
		print ('A palavra eh: {0}'.format(self.word))
		print("       ___________      ")
		print("      '._==_==_=_.'     ")
		print("      .-\\:      /-.    ")
		print("     | (|:.     |) |    ")
		print("      '-|:.     |-'     ")
		print("        \\::.    /      ")
		print("         '::. .'        ")
		print("           ) (          ")
		print("         _.' '._        ")
		print("        '-------'       ")

	#Método para exibir mensagem de derrota 

	def hangman_lose_game(self):
		print("Puxa, você foi enforcado!")
		print('A palavra eh: {0}'.format(self.word))
		print("    _______________         ")
		print("   /               \       ")
		print("  /                 \      ")
		print("//                   \/\  ")
		print("\|   XXXX     XXXX   | /   ")
		print(" |   XXXX     XXXX   |/     ")
		print(" |   XXX       XXX   |      ")
		print(" |                   |      ")
		print(" \__      XXX      __/     ")
		print("   |\     XXX     /|       ")
		print("   | |           | |        ")
		print("   | I I I I I I I |        ")
		print("   |  I I I I I I  |        ")
		print("   \_             _/       ")
		print("     \_         _/         ")
		print("       \_______/           ")


	
	#Método com mecânica de jogo da forca
	def play_game(self):

		while self.failed_attemps < len(board):
			self.print_game_status()
			user_input = self.get_user_input()

			#Valida a entrada do usuário com método is_invalid_letter
			if self.is_invalid_letter(user_input):
				print('\n A entrada nao eh uma letra valida!')
				continue	
			#Verifica se letra já não foi digitada anteriormente
			if user_input in self.game_progress:
				print('\n Voce ja digitou essa letra anteriormente!')
				continue
			#Verifica se usuario venceu o game 
			if user_input in self.word:
				indexes = self.guess(user_input)
				self.update_progress(user_input, indexes)

				#Verifica se não há letras para procurar na palavra
				if self.game_progress.count('_') == 0:
					self.hangman_won_game()
					quit()
			else:
				self.failed_attemps += 1
				
		self.hangman_lose_game()
		

# Função para ler uma palavra de forma aleatória do banco de palavras
def rand_word():
        with open("palavras.txt", "rt") as f:
                bank = f.readlines()
        return bank[random.randint(0,len(bank))].strip()


# Função Main - Execução do Programa
def main():

	# Objeto
	game = Hangman(rand_word())

	game.play_game()	

# Executa o programa		
if __name__ == "__main__":
	main()
