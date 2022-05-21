<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

//Incorporo um controller para acesso a minha rota.
Route::get('/', 'PrincipalController@principal');
Route::get('/sobre-nos','SobreNosController@sobreNos');
Route::get('/contato', 'ContatoController@contato');
Route::get('contato/{nome}/{categoria}/{assunto?}/{msg?}',
    function(
        $nome,
        $categoria,
        $assunto="default",
        $msg="sem msg") {
    //nome, categoria, assunto, msg

    echo 'Olá: '.$nome.' '.$categoria.' '.$assunto.' '.$msg;

});

//Tratanto parâmetros de rotas com expressões regulares

Route::get('/new_route/{x}/{y}', function(String $x, int $y) {
    echo 'Olá: '.$x.' '.$y;
})->where('y','[0-9]+') ->where('x', '[A-Za-z]+');

//  Route::get($uri, $callback);

/* verbo http
    get
    post
    put
    patch
    delete
    options
*/
