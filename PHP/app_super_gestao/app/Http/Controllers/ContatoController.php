<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class ContatoController extends Controller
{
    public function contato()
    {
        //echo 'Olá, seja bem vindo rota contato';
        var_dump($_POST);
        return view('site.contato');
    }
}
