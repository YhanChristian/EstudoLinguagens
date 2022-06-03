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
Route::get('/', 'PrincipalController@principal')->name('site.index');
Route::get('/sobre-nos','SobreNosController@sobreNos')->name('site.sobre-nos');
Route::get('/contato', 'ContatoController@contato')->name('site.contato');
Route::get('/login', function(){return 'Login';})->name('site.login');

//Rotas de Teste para redirecionamento
Route::get('/route-1', function(){return 'Route 01';})->name('site.route-1');

// Route 2 redireciona para Route 1
Route::get('/route-2', function(){
    return redirect()->route('site.route-1');
})->name('site.route-2');

//Implementação de Rota de Fallback (Contigência) caso o user acesse rota inexistente
Route::fallback(function(){
   echo 'A rota acessada não existe, <a href="'.route('site.index').'">clique aqui</a> para ir para index';
});


//Agrupamento de Rotas
Route::prefix('/app')->group(function (){

    Route::get('/clientes', function(){return 'Clientes';})->name('app.clientes');
    Route::get('/fornecedores', function(){return 'Fornecedores';})->name('app.fornecedores');
    Route::get('/produtos', function(){return 'Produtos';})->name('app.produtos');
});



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
