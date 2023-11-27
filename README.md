# wktechnology

# Projeto Spring Boot

## Descrição
Este é um projeto Spring Boot que demonstra uma API RESTful.

## Executando o Projeto
Para executar o projeto, você pode utilizar o seguinte comando:

  mvn spring-boot:run (entro da pasta do projeto)

  java 17 deve estar configurado na máquina.

end points:

Listar Pessoas

Retorna todas as pessoas cadastradas.

    Método: GET
    Endpoint: /pessoas
    Resposta: Lista de objetos Pessoa

Contar Estados

Conta a quantidade de pessoas por estado.

    Método: GET
    Endpoint: /pessoas/countEstados
    Resposta: Mapa contendo o estado brasileiro como chave e a quantidade de pessoas como valor.

IMC Médio por Faixa Etária

Retorna o IMC médio agrupado por faixa etária.

    Método: GET
    Endpoint: /pessoas/imcMedioPorFaixaEtaria
    Resposta: Mapa contendo a faixa etária (10 anos) como chave e o IMC médio como valor.

IMC Maior Que Por Sexo

Calcula o número de pessoas com IMC maior que um limite, agrupado por sexo.

    Método: POST
    Endpoint: /pessoas/imcMaiorQuePorSexo
    Corpo da Requisição: Limite de IMC (LimiteDTO)

    Exemplo: 

    {"limite":30.0}
    
    Resposta: Mapa contendo o sexo como chave e o percentual de pessoas como valor acima do limite enviado no corpo da requisição.

Média de Idade por Sexo

Calcula a média de idade agrupada por sexo.

    Método: GET
    Endpoint: /pessoas/mediaDeIdadePorSexo
    Resposta: Mapa contendo o tipo sanguíneo como chave e a média de idade como valor.

Quantidade de Doadores por Receptor

Calcula a quantidade de doadores para cada tipo sanguíneo receptor.

    Método: GET
    Endpoint: /pessoas/quantidadeDeDoadoresPorRecptor
    Resposta: Mapa contendo o tipo sanguíneo como chave e a quantidade de doadores como valor.

Recuperar Pessoa por ID

Recupera uma pessoa específica pelo ID.

    Método: GET
    Endpoint: /pessoas/{id}
    Parâmetro: ID da pessoa
    Resposta: Objeto Pessoa ou mensagem indicando que a pessoa não foi encontrada.

Criar Pessoa

Cria uma nova pessoa.

    Método: POST
    Endpoint: /api/pessoas
    Corpo da Requisição: Objeto PessoaDTO
    Exemplo:

    {    
    "nome": "Calebe Roberto Caldeira",
    "cpf": "022.332.331-59",
    "dataNasc": "18/09/1951",
    "rg": "16.519.545-9",
    "sexo": "Masculino",
    "mae": "Letícia Allana",
    "pai": "Augusto Pedro Henrique Caldeira",
    "email": "caleberobertocaldeira__caleberobertocaldeira@hotelruby.com.br",
    "cep": "79106-020",
    "endereco": "Rua Dolcinópolis",
    "numero": 769,
    "bairro": "Jardim Aeroporto",
    "cidade": "Campo Grande",
    "estado": "MS",
    "telefoneFixo": "(67) 3964-7912",
    "celular": "(67) 98598-3073",
    "altura": 1.60,
    "peso": 107,
    "tipoSanguineo": "B-"
    }
    
    Resposta: Objeto Pessoa recém-criado ou mensagem de erro em caso de falha.

Deletar Pessoa por ID

Exclui uma pessoa pelo ID.

    Método: DELETE
    Endpoint: /pessoas/{id}
    Parâmetro: ID da pessoa
    Resposta: Mensagem de sucesso ou indicando que a pessoa não foi encontrada.

Atualizar Pessoa por ID

Atualiza uma pessoa existente pelo ID.

    Método: PUT
    Endpoint: /pessoas/{id}
    Parâmetro: ID da pessoa
    Corpo da Requisição: Objeto PessoaDTO
    Resposta: Objeto Pessoa atualizado ou mensagem indicando erro.
  
