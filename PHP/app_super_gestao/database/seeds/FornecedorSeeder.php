<?php

use Illuminate\Database\Seeder;
use App\Fornecedor;

class FornecedorSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // Instância do objeto
        $fornecedor = new Fornecedor();
        $fornecedor->nome = 'FornecedorA';
        $fornecedor->site = 'fornecedora.com.br';
        $fornecedor->uf = 'CE';
        $fornecedor->email = 'fornecedora@gmail.com';
        $fornecedor->save();
        
        //Mètodo create
        Fornecedor::create([
            'nome' => 'FornecedorB',
            'site' => 'fornecedorb.com.br',
            'uf' => 'DF',
            'email' => 'fornecedorb@gmail.com.br'
        ]);

        //Método insert -> BD direto.
        DB::table('fornecedores')->insert([
            'nome' => 'FornecedorC',
            'site' => 'fornecedorb.com.br',
            'uf' => 'MG',
            'email' => 'fornecedorc@gmail.com.br'
        ]);
    }
}
