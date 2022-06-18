<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class TesteController extends Controller
{
    public function teste(int $x, int $y){
        //echo "A soma de  $x  + $y é: ".($x + $y);
        /*Via array associativo */ //return view('site.teste',['x' =>$x, 'y'=>$y]);
        /*Via Compact*/ return view('site.teste', compact('x', 'y'));
        /*Via With*/ //return view('site.teste')->with('x', $x)->with('y',$y);

        //Via compact é mais prático
    }
}
