# Calculadora em Python

# Desenvolva uma calculadora em Python com tudo que você aprendeu nos capítulos 2 e 3. 
# A solução será apresentada no próximo capítulo!
# Assista o vídeo com a execução do programa!


print("\n******************* Python Calculator *******************")

def soma( num_1, num_2):
    print("Primeiro número: " + str(num_1))
    print("Segundo número: " + str(num_2))
    print("Soma: ", int(num_1) + int(num_2))
    

def sub(num_1, num_2):
    print("Primeiro número: " + str(num_1))
    print("Segundo número: " + str(num_2))
    print("Sub: ", int(num_1) - int(num_2))


def mult(num_1, num_2):
    print("Primeiro número: " + str(num_1))
    print("Segundo número: " + str(num_2))
    print("Mult: ", int(num_1) * int(num_2))

def div(num_1, num_2):
    print("Primeiro número: " + str(num_1))
    print("Segundo número: " + str(num_2))
    print("Div: ",int(num_1) / int(num_2))

operacao = input('Digite a operação desejada (1 - soma 2 - sub 3 - mult 4 - div): ')
valor_1 = input('Digite numero 1: ' )
valor_2 = input('Digite numero 2: ')

if(operacao == '1'):
    soma(valor_1, valor_2)
    
elif(operacao == '2'):
    sub(valor_1, valor_2)

elif(operacao == '3'):
    mult(valor_1, valor_2)    

elif(operacao == '4'):
    div(valor_1, valor_2) 

else:
    print ('Operação inválida!')

