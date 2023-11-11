<h1 align="center">🌐 Realizando deploy da API Spring 🌐</h1>

<h2 align="center">📝 Requisitos 📝</h2>

> [!IMPORTANT]
> Primeiramente precisamos criar o banco de dados (SQL da Azure) e o app Spring (Aplicativos do Azure Spring). Após ambos os serviços estarem disponíveis vá até o recurso de banco de dados na Azure e procure pela URL de conexão do seu banco de dados; tendo a sua URL salva, substitua o valor da propriedade `spring.datasource.url` pela URL de conexão ( **ATENÇÃO: NÃO SE ESQUEÇA DE MODIFICAR A SENHA DO SEU USUÁRIO NA URL DE CONEXÃO**). Depois da criação destes dois serviços devemos importar o repositório para o Azure DevOps (`git clone https://github.com/DabGias/api-station`), devemos garantir que o banco de dados e o app Spring estão devidamente implantados na nuvem.

Durante a importação mostrada no vídeo usaremos a seguinte configuração YAML:
```yaml
trigger:
- main

pool:
vmImage: ubuntu-latest

steps:
- task: Maven@3
	inputs:
		mavenPomFile: 'pom.xml'
		mavenOptions: '-Xmx3072m'
		javaHomeOption: 'JDKVersion'
		jdkVersionOption: '1.17'
		jdkArchitectureOption: 'x64'
		publishJUnitResults: true
		testResultsFiles: '**/surefire-reports/TEST-*.xml'
		goals: 'package'

- task: AzureSpringCloud@0
	inputs:
		azureSubscription: '<seu plano da Azure>'
		Action: 'Deploy'
		AzureSpringCloud: <nome do seu serviço Aplicativos do Azure Spring>
		AppName: <nome do app>
		DeploymentType: 'Artifacts'
		UseStagingDeployment: false
		DeploymentName: 'default'
		Package: ./target/station-0.0.1-SNAPSHOT.jar
```

Agora basta testar a API através da URL fornecida como "Teste de ponto de extremidade" dentro do app criado no serviço de Aplicativos do Azure Spring.

No nosso código possuímos 4 entidades, sendo elas, Pedido, Produto, Categoria e Usuário, cada uma com seus *endpoints* e métodos `GET`, `POST`, `PUT` e `DELETE`. Os *endpoints* são:

<h1 align="center">PEDIDO <code>(/station/pedido)</code></h1>

`GET`

```json
{
	"_embedded": {
		"pedidoList": [
			{
				"id": 1,
				"dataPedido": "2023-09-12",
				"formaEntrega": "SEDEX",
				"produtos": [
					{
						"id": 1,
						"nome": "Camiseta do Jar Jar Binks",
						"preco": 100.00,
						"descricao": "Camiseta do personagem mais amado da franquia Start Wars (Guerra nas Estrelas)!",
						"categorias": [
							{
								"id": 1,
								"nome": "Roupas",
								"descricao": "Roupas de diversos estilos e modelos!"
							}
						]
					},
					{
						"id": 2,
						"nome": "Sofá-cama",
						"preco": 7000.00,
						"descricao": "O melhor sofá e a melhor cama!",
						"categorias": [
							{
								"id": 2,
								"nome": "Eletrônicos",
								"descricao": "Os modelos mais avançados e atualizados!"
							}
						]
					}
				],
				"usuario": {
					"id": 1,
					"email": "usuario1@email.com.br",
					"nome": "Pedro Silva Santos",
					"cpf": "111.111.111-10",
					"senha": "$2a$10$kWmCYCR7kmzEBePKiKibRuBk56jf6m5T1sobhtX36WgJLC2Fa8tM."
				}
			},
			{
				"id": 2,
				"dataPedido": "2023-09-12",
				"formaEntrega": "FedEx",
				"produtos": [
					{
						"id": 2,
						"nome": "Sofá-cama",
						"preco": 7000.00,
						"descricao": "O melhor sofá e a melhor cama!",
						"categorias": [
							{
								"id": 2,
								"nome": "Eletrônicos",
								"descricao": "Os modelos mais avançados e atualizados!"
							}
						]
					},
					{
						"id": 3,
						"nome": "Celular",
						"preco": 3000.00,
						"descricao": "O novo celular do momento!",
						"categorias": [
							{
								"id": 3,
								"nome": "Móveis",
								"descricao": "Decoração moderna e atual para sua casa!"
							}
						]
					}
				],
				"usuario": {
					"id": 2,
					"email": "usuario2@email.com.br",
					"nome": "João Da Silva Dos Santos",
					"cpf": "111.111.111-11",
					"senha": "$2a$10$4v5IPVPb6XrvGEzRePd1We69IlUmKdiLEbcpsXzP4Y..9QvokuHem"
				}
			},
			{
				"id": 3,
				"dataPedido": "2023-09-12",
				"formaEntrega": "Correios",
				"produtos": [
					{
						"id": 1,
						"nome": "Camiseta do Jar Jar Binks",
						"preco": 100.00,
						"descricao": "Camiseta do personagem mais amado da franquia Start Wars (Guerra nas Estrelas)!",
						"categorias": [
							{
								"id": 1,
								"nome": "Roupas",
								"descricao": "Roupas de diversos estilos e modelos!"
							}
						]
					},
					{
						"id": 3,
						"nome": "Celular",
						"preco": 3000.00,
						"descricao": "O novo celular do momento!",
						"categorias": [
							{
								"id": 3,
								"nome": "Móveis",
								"descricao": "Decoração moderna e atual para sua casa!"
							}
						]
					}
				],
				"usuario": {
					"id": 3,
					"email": "usuario3@email.com.br",
					"nome": "Marcelo Miklos",
					"cpf": "111.111.111-12",
					"senha": "$2a$10$G3XGP25t/Ucfwk8Z3DGCnu7VwUbFHWwEcdbvdEez6jtBDnTkDaHl."
				}
			}
		]
	},
	"_links": {
		"self": {
			"href": "https://api-station.test.azuremicroservices.io/station/pedido?page=0&size=5"
		}
	},
	"page": {
		"size": 5,
		"totalElements": 3,
		"totalPages": 1,
		"number": 0
	}
}
```

`GET (/{id})`

```json
{
	"id": 1,
	"dataPedido": "2023-09-12",
	"formaEntrega": "SEDEX",
	"produtos": [
		{
			"id": 1,
			"nome": "Camiseta do Jar Jar Binks",
			"preco": 100.00,
			"descricao": "Camiseta do personagem mais amado da franquia Start Wars (Guerra nas Estrelas)!",
			"categorias": [
				{
					"id": 1,
					"nome": "Roupas",
					"descricao": "Roupas de diversos estilos e modelos!"
				}
			]
		},
		{
			"id": 2,
			"nome": "Sofá-cama",
			"preco": 7000.00,
			"descricao": "O melhor sofá e a melhor cama!",
			"categorias": [
				{
					"id": 2,
					"nome": "Eletrônicos",
					"descricao": "Os modelos mais avançados e atualizados!"
				}
			]
		}
	],
	"usuario": {
		"id": 1,
		"email": "usuario1@email.com.br",
		"nome": "Pedro Silva Santos",
		"cpf": "111.111.111-10",
		"senha": "$2a$10$kWmCYCR7kmzEBePKiKibRuBk56jf6m5T1sobhtX36WgJLC2Fa8tM."
	},
	"_links": {
		"self": {
			"href": "https://api-station.test.azuremicroservices.io/station/pedido/1"
		},
		"delete": {
			"href": "https://api-station.test.azuremicroservices.io/station/pedido/1"
		},
		"listAll": {
			"href": "https://api-station.test.azuremicroservices.io/station/pedido"
		}
	}
}
```

`POST`

```json
{
	"id": 4,
	"dataPedido": "2023-09-12",
	"formaEntrega": "FedEx",
	"produtos": [
		{
			"id": 2,
			"nome": "Sofá-cama",
			"preco": 7000.00,
			"descricao": "O melhor sofá e a melhor cama!",
			"categorias": [
				{
					"id": 2,
					"nome": "Eletrônicos",
					"descricao": "Os modelos mais avançados e atualizados!"
				}
			]
		}
	],
	"usuario": {
		"id": 1,
		"email": "usuario1@email.com.br",
		"nome": "Pedro Silva Santos",
		"cpf": "111.111.111-10",
		"senha": "$2a$10$kWmCYCR7kmzEBePKiKibRuBk56jf6m5T1sobhtX36WgJLC2Fa8tM."
	}
}
```

`PUT (/{id})`

```json
{
	"dataPedido": "2023-09-12",
	"formaEntrega": "UPS",
	"produtos": [
		{
			"id": 2,
			"nome": "Sofá-cama",
			"preco": 7000.00,
			"descricao": "O melhor sofá e a melhor cama!",
			"categorias": [
				{
					"id": 2,
					"nome": "Eletrônicos",
					"descricao": "Os modelos mais avançados e atualizados!"
				}
			]
		}
	],
	"usuario": {
		"id": 1,
		"email": "usuario1@email.com.br",
		"nome": "Pedro Silva Santos",
		"cpf": "111.111.111-10",
		"senha": "$2a$10$kWmCYCR7kmzEBePKiKibRuBk56jf6m5T1sobhtX36WgJLC2Fa8tM."
	}
}
```

`DELETE (/{id})`

```json
204 No Content
```

<h1 align="center">PRODUTO <code>(/station/produto)</code></h1>

`GET`

```json
{
	"_embedded": {
		"produtoList": [
			{
				"id": 1,
				"nome": "Camiseta do Jar Jar Binks",
				"preco": 100.00,
				"descricao": "Camiseta do personagem mais amado da franquia Start Wars (Guerra nas Estrelas)!",
				"categorias": [
					{
						"id": 1,
						"nome": "Roupas",
						"descricao": "Roupas de diversos estilos e modelos!"
					}
				]
			},
			{
				"id": 2,
				"nome": "Sofá-cama",
				"preco": 7000.00,
				"descricao": "O melhor sofá e a melhor cama!",
				"categorias": [
					{
						"id": 2,
						"nome": "Eletrônicos",
						"descricao": "Os modelos mais avançados e atualizados!"
					}
				]
			},
			{
				"id": 3,
				"nome": "Celular",
				"preco": 3000.00,
				"descricao": "O novo celular do momento!",
				"categorias": [
					{
						"id": 3,
						"nome": "Móveis",
						"descricao": "Decoração moderna e atual para sua casa!"
					}
				]
			}
		]
	},
	"_links": {
		"self": {
			"href": "https://api-station.test.azuremicroservices.io/station/produto?page=0&size=5"
		}
	},
	"page": {
		"size": 5,
		"totalElements": 3,
		"totalPages": 1,
		"number": 0
	}
}
```

`GET (/{id})`

```json
{
	"id": 1,
	"nome": "Camiseta do Jar Jar Binks",
	"preco": 100.00,
	"descricao": "Camiseta do personagem mais amado da franquia Start Wars (Guerra nas Estrelas)!",
	"categorias": [
		{
			"id": 1,
			"nome": "Roupas",
			"descricao": "Roupas de diversos estilos e modelos!"
		}
	],
	"_links": {
		"self": {
			"href": "https://api-station.test.azuremicroservices.io/station/produto/1"
		},
		"delete": {
			"href": "https://api-station.test.azuremicroservices.io/station/produto/1"
		},
		"listAll": {
			"href": "https://api-station.test.azuremicroservices.io/station/produto{?query}",
			"templated": true
		}
	}
}
```

`POST`

```json
{
	"id": 4,
	"nome": "Camiseta do Raul Seixas",
	"preco": 46.00,
	"descricao": "Camiseta do pai do rock nacional!",
	"categorias": [
		{
			"id": 1,
			"nome": "Roupas",
			"descricao": "Roupas de diversos estilos e modelos!"
		}
	]
}
```

`PUT (/{id})`

```json
{
	"nome": "Camiseta do Elvis Presley",
	"preco": 46.00,
	"descricao": "Camiseta do rei do rock internacional!",
	"categorias": [
		{
			"id": 1,
			"nome": "Roupas",
			"descricao": "Roupas de diversos estilos e modelos!"
		}
	]
}
```

`DELETE (/{id})`

```json
204 No Content
```

<h1 align="center">CATEGORIA <code>(/station/categoria)</code></h1>

`GET`

```json
{
	"_embedded": {
		"categoriaList": [
			{
				"id": 1,
				"nome": "Roupas",
				"descricao": "Roupas de diversos estilos e modelos!"
			},
			{
				"id": 2,
				"nome": "Eletrônicos",
				"descricao": "Os modelos mais avançados e atualizados!"
			},
			{
				"id": 3,
				"nome": "Móveis",
				"descricao": "Decoração moderna e atual para sua casa!"
			}
		]
	},
	"_links": {
		"self": {
			"href": "https://api-station.test.azuremicroservices.io/station/categoria?page=0&size=5"
		}
	},
	"page": {
		"size": 5,
		"totalElements": 3,
		"totalPages": 1,
		"number": 0
	}
}
```

`GET (/{id})`

```json
{
	"id": 1,
	"nome": "Roupas",
	"descricao": "Roupas de diversos estilos e modelos!",
	"_links": {
		"self": {
			"href": "https://api-station.test.azuremicroservices.io/station/categoria/1"
		},
		"delete": {
			"href": "https://api-station.test.azuremicroservices.io/station/categoria/1"
		},
		"listAll": {
			"href": "https://api-station.test.azuremicroservices.io/station/categoria"
		}
	}
}
```

`POST`

```json
{
	"id": 4,
	"nome": "Utensílios de cozinha",
	"descricao": "Os melhores utensílios para evitar dor de cabeça na cozinha!"
}
```

`PUT (/{id})`

```json
{
	"nome": "Utensílios de cozinha de Inox",
	"descricao": "Os melhores utensílios para evitar dor de cabeça na cozinha!"
}
```

`DELETE (/{id})`

```json
204 No Content
```

<h1 align="center">USUÁRIO <code>(/station/usuario)</code></h1>

`GET`

```json
{
	"_embedded": {
		"usuarioList": [
			{
				"id": 1,
				"email": "usuario1@email.com.br",
				"nome": "Pedro Silva Santos",
				"cpf": "111.111.111-10",
				"senha": "$2a$10$kWmCYCR7kmzEBePKiKibRuBk56jf6m5T1sobhtX36WgJLC2Fa8tM."
			},
			{
				"id": 2,
				"email": "usuario2@email.com.br",
				"nome": "João Da Silva Dos Santos",
				"cpf": "111.111.111-11",
				"senha": "$2a$10$4v5IPVPb6XrvGEzRePd1We69IlUmKdiLEbcpsXzP4Y..9QvokuHem"
			},
			{
				"id": 3,
				"email": "usuario3@email.com.br",
				"nome": "Marcelo Miklos",
				"cpf": "111.111.111-12",
				"senha": "$2a$10$G3XGP25t/Ucfwk8Z3DGCnu7VwUbFHWwEcdbvdEez6jtBDnTkDaHl."
			}
		]
	},
	"_links": {
		"self": {
			"href": "https://api-station.test.azuremicroservices.io/station/usuario?page=0&size=5"
		}
	},
	"page": {
		"size": 5,
		"totalElements": 3,
		"totalPages": 1,
		"number": 0
	}
}
```

`GET (/{id})`

```json
{
	"id": 1,
	"email": "usuario1@email.com.br",
	"nome": "Pedro Silva Santos",
	"cpf": "111.111.111-10",
	"senha": "$2a$10$kWmCYCR7kmzEBePKiKibRuBk56jf6m5T1sobhtX36WgJLC2Fa8tM.",
	"_links": {
		"self": {
			"href": "https://api-station.test.azuremicroservices.io/station/usuario/1"
		},
		"delete": {
			"href": "https://api-station.test.azuremicroservices.io/station/usuario/1"
		},
		"listAll": {
			"href": "https://api-station.test.azuremicroservices.io/station/usuario{?query}",
			"templated": true
		}
	}
}
```

`POST`

```json
{
	"email": "cleber@email.com.br",
	"nome": "Cleber Tanide",
	"cpf": "123.123.123-40",
	"senha": "cleber@station"
}
```

`PUT (/{id})`

```json
{
	"email": "cleber@email.com.br",
	"nome": "Cleber Tanide Santos",
	"cpf": "123.123.123-40",
	"senha": "cleber@station"
}
```

`DELETE (/{id})`

```json
204 No Content
```
