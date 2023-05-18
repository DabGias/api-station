<h1 align="center">API Station</h1>

<h2 align="center">🔧 Diagrama de Blocos 🔨</h2>
<div align="center">
    <img height src="https://cdn.discordapp.com/attachments/277100245643689994/1108726859191046164/image.png"/>
</div>

<h2 align="center">Endpoints 📖</h2>

### Produto **`/station/produto`**:

#### GET

**Retorna 👇**
```js
{
    "id": 1,
    "nome": "Camiseta do Baby Yoda",
    "preco": 70.00,
    "descricao": "Camiseta com uma estampa do personagem Baby Yoda",
    "categorias": [
        {
            "id": 1,
            "nome": "Roupas",
            "descricao": "Roupas de todos os estilos e modelos"
        }
    ]
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados do produto foram retornados com sucesso.
| `404` | Não há produtos cadastrados até o momento.

#### GET `{id_produto}`

**Retorna 👇**
```js
{
    "id": 1,
    "nome": "Camiseta do Baby Yoda",
    "preco": 70.00,
    "descricao": "Camiseta com uma estampa do personagem Baby Yoda",
    "categorias": [
        {
            "id": 1,
            "nome": "Roupas",
            "descricao": "Roupas de todos os estilos e modelos"
        }
    ]
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados do produto foram retornados com sucesso.
| `404` | Não há produto cadastrado com esse identificador até o momento.

#### POST 

**Requer 👇**
```js
{
    "nome": "Camiseta do Mandalorian",
    "preco": 80.00,
    "descricao": "Camiseta com uma estampa do personagem Mandalorian",
    "categorias": [
        {
            "id": 1,
            "nome": "Roupas",
            "descricao": "Roupas de todos os estilos e modelos"
        }
    ]
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `201` | Dados do produto foram cadastrados com sucesso.
| `400` | Houve uma falha no cadastro dos dados.

#### PUT `{id_produto}`

**Requer 👇**
 ```js
{
    "nome": "Camiseta do Jar Jar Binks",
    "preco": 50.00,
    "descricao": "Camiseta com uma estampa do personagem Jar Jar Binks",
    "categorias": [
        {
            "id": 1,
            "nome": "Roupas",
            "descricao": "Roupas de todos os estilos e modelos"
        }
    ]
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados do produto foram atualizados com sucesso.
| `400` | Houve uma falha na atualização dos dados.

#### DELETE `{id_produto}`

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `204` | Dados do produto foram deletados com sucesso.
| `404` | Não há um produto com esse identificador até o momento.

<hr>

### Categoria **`/station/categoria`**:

#### GET

**Retorna 👇**
```js
{
    "id": 1,
    "nome": "Roupas",
    "descricao": "Roupas de todos os estilos e modelos"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados da categoria foram retornados com sucesso.
| `404` | Não há categorias cadastradas até o momento.

#### GET `{id_categoria}`

**Retorna 👇**
```js
{
    "id": 1,
    "nome": "Roupas",
    "descricao": "Roupas de todos os estilos e modelos"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados da categoria foram retornados com sucesso.
| `404` | Não há categoria cadastrada com esse identificador até o momento.

#### POST 

**Requer 👇**
```js
{
    "nome": "Periféricos",
    "descricao": "Periféricos em geral"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `201` | Dados da categoria foram cadastrados com sucesso.
| `400` | Houve uma falha no cadastro dos dados.

#### PUT `{id_categoria}`

**Requer 👇**
 ```js
{
    "nome": "Periféricos",
    "descricao": "Periféricos em geral de todas as marcas"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados da categoria foram atualizados com sucesso.
| `400` | Houve uma falha na atualização dos dados.

#### DELETE `{id_categoria}`

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `204` | Dados da categoria foram deletados com sucesso.
| `404` | Não há um produto com esse identificador até o momento.

<hr>

### Usuário **`/station/usuario`**: 

#### GET

**Retorna 👇**
```js
{
    "id": 1,
    "email": "pedro@email.com"
    "nome": "Pedro Borges",
    "cpf": "111.222.333-10",
    "senha": "senha123",
    "tel": "(11) 99999-9999"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados do usuário foram retornados com sucesso.
| `404` | Não há usuários cadastrados até o momento.

#### GET `{id_usuario}`

**Retorna 👇**
```js
{
    "id": 1,
    "email": "pedro@email.com"
    "nome": "Pedro Borges",
    "cpf": "111.222.333-10",
    "senha": "senha123",
    "tel": "(11) 99999-9999"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados do usuário foram retornados com sucesso.
| `404` | Não há usuário cadastrado com esse identificador até o momento.

#### POST 

**Requer 👇**
```js
{
    "email": "pedro@email.com"
    "nome": "Pedro Borges",
    "cpf": "111.222.333-10",
    "senha": "senha123",
    "tel": "(11) 99999-9999"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `201` | Dados do usuário foram cadastrados com sucesso.
| `400` | Houve uma falha no cadastro dos dados.

#### PUT `{id_usuario}`

**Requer 👇**
 ```js
{
    "email": "pedro@email.com"
    "nome": "Pedro Bó",
    "cpf": "111.222.333-10",
    "senha": "senha123",
    "tel": "(11) 99999-9999"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados do usuário foram atualizados com sucesso.
| `400` | Houve uma falha na atualização dos dados.

#### DELETE `{id_usuario}`

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `204` | Dados do usuário foram deletados com sucesso.
| `404` | Não há um usuário com esse identificador até o momento.

<hr>

### Pedido **`/station/pedido`**:

#### GET

**Retorna 👇**
```js
{
    "id": 1,
    "dt_pedido": "2023-04-05",
    "forma_entrega": "SEDEX",
    "produtos": [
        {
            "id": 1,
            "nome": "Camiseta do Baby Yoda",
            "preco": 70.00,
            "descricao": "Camiseta com uma estampa do personagem Baby Yoda",
            "categorias": [
                {
                    "id": 1,
                    "nome": "Roupas",
                    "descricao": "Roupas de todos os estilos e modelos"
                }
            ]
        }
    ],
    "usuario": {
        "id": 1,
        "email": "gabriel@email.com"
        "nome": "Gabriel Dias",
        "cpf": "123.456.789-10",
        "senha": "senha123",
        "tel": "(11) 99999-9999"
    }
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados do pedido foram retornados com sucesso.
| `404` | Não há pedidos cadastrados até o momento.

#### GET `{id_pedido}`

**Retorna 👇**
```js
{
    "id": 1,
    "dt_pedido": "2023-04-05",
    "forma_entrega": "SEDEX",
    "produtos": [
        {
            "id": 1,
            "nome": "Camiseta do Baby Yoda",
            "preco": 70.00,
            "descricao": "Camiseta com uma estampa do personagem Baby Yoda",
            "categorias": [
                {
                    "id": 1,
                    "nome": "Roupas",
                    "descricao": "Roupas de todos os estilos e modelos"
                }
            ]
        }
    ],
    "usuario": {
        "id": 1,
        "email": "gabriel@email.com"
        "nome": "Gabriel Dias",
        "cpf": "123.456.789-10",
        "senha": "senha123",
        "tel": "(11) 99999-9999"
    }
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados do pedido foram retornados com sucesso.
| `404` | Não há pedido cadastrado com esse identificador até o momento.

#### POST 

**Requer 👇**
```js
{
    "dt_pedido": "2023-04-05",
    "forma_entrega": "FedEx",
    "produtos": [
        {
            "id": 1,
            "nome": "Camiseta do Baby Yoda",
            "preco": 70.00,
            "descricao": "Camiseta com uma estampa do personagem Baby Yoda",
            "categorias": [
                {
                    "id": 1,
                    "nome": "Roupas",
                    "descricao": "Roupas de todos os estilos e modelos"
                }
            ]
        }
    ],
    "usuario": {
        "id": 1,
        "email": "gabriel@email.com"
        "nome": "Gabriel Dias",
        "cpf": "123.456.789-10",
        "senha": "senha123",
        "tel": "(11) 99999-9999"
    }
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `201` | Dados do pedido foram cadastrados com sucesso.
| `400` | Houve uma falha no cadastro dos dados.

#### PUT `{id_pedido}`

**Requer 👇**
 ```js
{
    "dt_pedido": "2023-04-05",
    "forma_entrega": "Serviço Aéreo Doméstico da FedEx",
    "produtos": [
        {
            "id": 1,
            "nome": "Camiseta do Baby Yoda",
            "preco": 70.00,
            "descricao": "Camiseta com uma estampa do personagem Baby Yoda",
            "categorias": [
                {
                    "id": 1,
                    "nome": "Roupas",
                    "descricao": "Roupas de todos os estilos e modelos"
                }
            ]
        }
    ],
    "usuario": {
        "id": 1,
        "email": "gabriel@email.com"
        "nome": "Gabriel Dias",
        "cpf": "123.456.789-10",
        "senha": "senha123",
        "tel": "(11) 99999-9999"
    }
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Dados do pedido foram atualizados com sucesso.
| `400` | Houve uma falha na atualização dos dados.

#### DELETE `{id_pedido}`

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `204` | Dados do pedido foram deletados com sucesso.
| `404` | Não há um pedido com esse identificador até o momento.
