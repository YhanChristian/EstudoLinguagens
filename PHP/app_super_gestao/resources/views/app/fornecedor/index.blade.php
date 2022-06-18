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

{{-- Operador unless (funciona basicamente como uma negação --}}

{{-- Lógica inversa seria como if(!(condicao))--}}
@unless($new_fornecedores[0]['status'] == 'S')
    <h3>Fornecedor Inativo</h3>
@endunless

{{-- Operador isset retorna true se a variável estiver definida --}}

@isset($new_fornecedores)
    Fornecedor: {{$new_fornecedores[0]['nome']}}
    <br>
    Status: {{$new_fornecedores[0]['status']}}
    <br>
    CNPJ: {{$new_fornecedores[0]['cnpj']}}
    <br>
@endisset
<br>
<br>
@isset($new_fornecedores)
    Fornecedor: {{$new_fornecedores[1]['nome']}}
    <br>
    Status: {{$new_fornecedores[1]['status']}}
    <br>
    {{-- Testa se existe antes imprimir, como n foi definido não entra neste bloco --}}
    @isset($new_fornecedores[1]['cnpj'])
        CNPJ: {{$new_fornecedores[1]['cnpj']}}
    <br>
    @endisset
@endisset
