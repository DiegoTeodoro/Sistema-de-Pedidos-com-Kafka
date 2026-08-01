# Projeto simples: Spring Boot + Apache Kafka

Este projeto demonstra o fluxo básico:

`Postman -> Controller -> Producer -> tópico pedidos -> Consumer -> console`

## Requisitos

- Java 21
- Maven 3.9+
- Docker Desktop
- IntelliJ IDEA ou outra IDE

## 1. Subir o Kafka

Abra um terminal na pasta do projeto e execute:

```bash
docker compose up -d
```

Confira o container:

```bash
docker ps
```

## 2. Executar a aplicação

Pelo IntelliJ, execute a classe:

```text
PedidoKafkaApplication
```

Ou use o terminal:

```bash
mvn spring-boot:run
```

## 3. Testar no Postman

Método:

```text
POST
```

URL:

```text
http://localhost:8080/pedidos
```

Body -> raw -> JSON:

```json
{
  "id": 1,
  "produto": "Notebook Dell",
  "valor": 3500.00
}
```

Resposta esperada: HTTP `202 Accepted`.

No console da aplicação aparecerá a mensagem recebida pelo Consumer.

## 4. Testar com PowerShell

```powershell
Invoke-RestMethod `
  -Method Post `
  -Uri "http://localhost:8080/pedidos" `
  -ContentType "application/json" `
  -Body '{"id":1,"produto":"Notebook Dell","valor":3500.00}'
```

## 5. Parar o Kafka

```bash
docker compose down
```

Para apagar também os dados armazenados no volume:

```bash
docker compose down -v
```

## Explicação para entrevista

A API recebe um pedido pelo endpoint REST. O Controller chama o Producer, que publica o objeto em formato JSON no tópico `pedidos`. O Consumer pertence ao grupo `grupo-pedidos`, fica inscrito nesse tópico e processa a mensagem de forma assíncrona. Dessa maneira, quem produz a mensagem não precisa conhecer diretamente quem irá consumi-la.
