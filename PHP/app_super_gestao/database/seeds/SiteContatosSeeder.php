<?php

use Illuminate\Database\Seeder;
use \App\SiteContato;

class SiteContatosSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        //Chamo a factory criada no Seeder;
        factory(SiteContato::class, 100)->create();
       /* $contato = new SiteContato();
        $contato->nome = 'Yhan';
        $contato->telefone ='(13) 99172-9889';
        $contato->email = 'yhan.christian@live.com';
        $contato->motivo_contato = 1;
        $contato->mensagem = 'Seja bem-vindo';
        $contato->save();

        $contato = new SiteContato();
        $contato->nome = 'Camila';
        $contato->telefone ='(13) 99172-9889';
        $contato->email = 'camila_bras_costa@gmail.com';
        $contato->motivo_contato = 2;
        $contato->mensagem = 'Seja bem-vindo 2';
        $contato->save();

        $contato = new SiteContato();
        $contato->nome = 'Teste';
        $contato->telefone ='(11) 99172-5555';
        $contato->email = 'teste@teste.com.br';
        $contato->motivo_contato = 1;
        $contato->mensagem = 'Seja bem-vindo';
        $contato->save();
        */

    }
}
