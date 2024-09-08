# Projeto de Sistema de Gerenciamento de Hospedagem e Serviços

Este projeto é uma aplicação Java para gerenciar clientes, hospedagens, serviços e a relação entre hospedagens e serviços em um sistema de gerenciamento de hospedagem. O projeto utiliza JDBC para interação com o banco de dados e Swing/JavaFX para a interface gráfica.

## Estrutura do Projeto

### Pacotes

- **`model`**: Contém as classes de modelo para representar as entidades do sistema.
- **`persistencia`**: Contém as classes de acesso aos dados (DAO) para realizar operações CRUD no banco de dados.
- **`view`**: Contém a classe Main.
- **`form`**: Contém as classes de interface gráfica.

## Configuração

1. **Banco de Dados**: Certifique-se de que o banco de dados está configurado corretamente e que todas as tabelas estão criadas.
2. **Dependências**: Verifique se você possui as bibliotecas necessárias para JDBC e qualquer outra dependência de terceiros que o projeto possa usar.
3. **Configuração de Conexão**: Ajuste as configurações de conexão no arquivo `ConnectionFactory` conforme necessário para o seu ambiente.

## Uso
 Execute a classe `Main` para verificar a conexão com o banco de dados e iniciar a interface gráfica.

## Autores 

Feito por Igor de Oliveira Moura e Hugo de Paula Souza, alunos do Instituto Federal de Brasília.
Professor: José Gonçalo dos Santos
Programação de Computadores II
