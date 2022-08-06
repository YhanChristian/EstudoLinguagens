<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class SiteContato extends Model
{
    //
    protected $table = 'site_contatos';
    //Permite a inserção dos atributos nome, site, uf e email sem necessidade de instanciar o objeto
    protected $fillable = ['nome', 'email', 'telefone'];
}
