<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class FornecedorController extends Controller
{
    public function index(){
        $fornecedores =['Fornecedor 1'];
        $new_fornecedores = [
            0 =>['nome' => 'Fornecedor 1', 'status' => 'N', 'cnpj'=> '00.000.000/0000-00', 'ddd' =>'11', 'tel'=> '0000-0000'],
            1 =>['nome' => 'Fornecedor 2', 'status' => 'S','ddd' =>'13', 'tel'=> '1111-1111'],
            2 =>['nome' => 'Fornecedor 3', 'status' => 'N', 'cnpj'=> '99.000.000/0001-00','ddd' =>'21', 'tel'=> '9999-9999'],
            3 =>['nome' => 'Fornecedor 4', 'status' => 'S','ddd' =>'19', 'tel'=> '8888-8888']
        ];
        //Operador Ternário aplicação
        echo isset($new_fornecedores[0]['cnpj']) ? 'CNPJ Informado': 'CNPJ Não Informado';


        return view('app.fornecedor.index', compact('fornecedores'), compact('new_fornecedores'));
    }

}
