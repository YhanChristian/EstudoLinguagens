<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class SobreNosController extends Controller
{
    public function sobreNos()
    {
        //echo 'Olá, seja bem vindo rota sobre nós';
        return view('site.sobre-nos');
    }
}
