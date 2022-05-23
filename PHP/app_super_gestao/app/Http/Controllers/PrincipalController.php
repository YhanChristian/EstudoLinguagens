<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class PrincipalController extends Controller
{
    //metódo dentro do controlador - (action)
    public function principal() {
        return view('site.principal');
    }
}
