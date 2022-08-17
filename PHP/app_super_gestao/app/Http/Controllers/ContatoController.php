<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\SiteContato;

class ContatoController extends Controller
{
    public function contato(Request $request)
    {
        //echo 'Olá, seja bem vindo rota contato';
        //var_dump($_POST);
        //dd($request);
        //print_r($request->all());
        
        //  Instância conato para receber dados request e salvar no BD
        $contato = new SiteContato();

        // Forma 1 com os atributos
       /* $contato->nome = $request->input('nome');
        $contato->telefone = $request->input('telefone');
        $contato->email = $request->input('email');
        $contato->motivo_contato = $request->input('motivo_contato');
        $contato->mensagem = $request->input('mensagem');
        */

        //Forma 2 com o método fill/create (Classe Model - deve ter fillable.)
        $contato->fill($request->all());
        $contato->save();

       // print_r($contato->getAttributes());

        return view('site.contato');
    }
}
