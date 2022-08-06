<?php

/** @var \Illuminate\Database\Eloquent\Factory $factory */

use App\SiteContato;
use Faker\Generator as Faker;
 
//Gera dados em massa aleatórios para popular o BD inialment
$factory->define(SiteContato::class, function (Faker $faker) {
    return [
        'nome' => $faker->name,
        'telefone' =>$faker->tollFreePhoneNumber,
        'email' => $faker->unique()->email,
        'motivo_contato'=> $faker->numberBetween(1,3),
        'mensagem' => $faker->text(200)
    ];
});
