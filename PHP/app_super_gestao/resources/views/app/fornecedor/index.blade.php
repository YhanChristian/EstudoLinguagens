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
    <br>
    {{-- Uso Default caso a variável n esteja definida @default --}}
    Fornecedor: {{$new_fornecedores[3]['nome']}}
    <br>
    Status: {{$new_fornecedores[3]['status']}}
    <br>
    CNPJ: {{$new_fornecedores[3]['cnpj'] ?? 'Dado não prenchido'}}
    <br>
    DDD: {{$new_fornecedores[3]['ddd'] ?? 'Dado não prenchido'}}
    <br>
    Tel: {{$new_fornecedores[3]['tel'] ?? 'Dado não prenchido'}}
    <br>
    {{-- Switch Case --}}
    @switch($new_fornecedores[3]['ddd'])
        @case('11')
            São Paulo - SP
        @break
        @case('13')
            Santos - SP
        @break
        @case('19')
        Campinas - SP
        @break
        @case('21')
        Rio de Janeiro - RJ
        @break
        @default
            Estado não identificado
        @break
    @endswitch
@endisset

<br>
<h3>uso do laço for</h3>

@isset($new_fornecedores)
    @for($i = 0; isset($new_fornecedores[$i]); $i++)
        Fornecedor: {{$new_fornecedores[$i]['nome']}}
        <br>
        Status: {{$new_fornecedores[$i]['status']}}
        <br>
        CNPJ: {{$new_fornecedores[$i]['cnpj'] ?? 'Dado não prenchido'}}
        <br>
        DDD: {{$new_fornecedores[$i]['ddd'] ?? 'Dado não prenchido'}}
        <br>
        Tel: {{$new_fornecedores[$i]['tel'] ?? 'Dado não prenchido'}}
        <br>
    @endfor
@endisset

<br>
<h3>uso do while</h3>
@isset($new_fornecedores)
    @php $i = 0 @endphp
    @while(isset($new_fornecedores[$i]))
        Fornecedor: {{$new_fornecedores[$i]['nome']}}
        <br>
        Status: {{$new_fornecedores[$i]['status']}}
        <br>
        CNPJ: {{$new_fornecedores[$i]['cnpj'] ?? 'Dado não prenchido'}}
        <br>
        DDD: {{$new_fornecedores[$i]['ddd'] ?? 'Dado não prenchido'}}
        <br>
        Tel: {{$new_fornecedores[$i]['tel'] ?? 'Dado não prenchido'}}
        <br>
        @php $i++ @endphp
    @endwhile
@endisset

<br>
<h3>uso foreach</h3>
@isset($new_fornecedores)
   @foreach($new_fornecedores as $indice => $new_fornecedores)
        Iteração Atual: {{$loop->iteration}}
        <br>
        Total Count: {{$loop->count}}
        @if($loop->first)
            <br>
            Primeira iteração do loop
        @endif
        @if($loop->last)
            <br>
            Ultima iteração do loop
        @endif
        <br>
        Fornecedor: {{$new_fornecedores['nome']}}
        <br>
        Status: {{$new_fornecedores['status']}}
        <br>
        CNPJ: {{$new_fornecedores['cnpj'] ?? 'Dado não prenchido'}}
        <br>
        DDD: {{$new_fornecedores['ddd'] ?? 'Dado não prenchido'}}
        <br>
        Tel: {{$new_fornecedores['tel'] ?? 'Dado não prenchido'}}
        <br>
    @endforeach
@endisset

{{-- Ao colocar @ na frente '{{}}' não é interpretado, simplesmente conteúdo é impresso --}}
<h5>@{{$bloco não interpretado}}</h5>

{{-- Verifica se variável esta ou não vazia --}}
@empty($new_fornecedores[2]['cnpj'])
    <h3>Vazio</h3>
@endempty




