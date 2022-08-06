<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Fornecedor extends Model
{
    //Incorpora softdeletes a classe fornecedor.
    use SoftDeletes;
    //Sobrepõe a nomeação do Eloquent da tabela
    // por padrão o eloquent usa o nome minusculo da classe e acrescenta o 's' como plural
    // Para sobrepor dentro da classe extendida no model utilizamos o atributo protected table e renomeamos a tabela.

    protected $table = 'fornecedores';
    //Permite a inserção dos atributos nome, site, uf e email sem necessidade de instanciar o objeto
    protected $fillable = ['nome', 'site', 'uf', 'email'];
}
