<h3>Fornecedor</h3>

{{-- Comentário Blade PHP --}}
@php
    //Comentários em PHP puro
    echo 'PHP puro';
    /*
     * if() {
     * }
     * else if (){
     * }
     */
@endphp

{{--Impressão Variável Array--}}
{{--
@dd($fornecedores)
--}}

{{-- Estrutura IF/ELSE--}}

@if(count($fornecedores) > 0 && count($fornecedores) < 10)
    <h3>Existem alguns fornecedores cadastrados</h3>
@elseif(count($fornecedores) > 10)
    <h3> Existem vários fornecedores cadastrados</h3>
@else
    <h3>Ainda não existem fornecedores cadastrados</h3>
@endif
