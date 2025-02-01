📅 Agenda App

Bem-vindo ao Agenda App, um sistema de gerenciamento de compromissos desenvolvido com Angular 17 e Spring Boot 3. O projeto permite que os usuários cadastrem, editem e removam eventos de uma agenda de maneira eficiente e intuitiva.

🚀 Tecnologias Utilizadas

Frontend (Angular 17)

Angular Material: Para componentes visuais avançados

Angular Router: Para navegação entre páginas

Backend (Spring Boot 3)

Spring Web: Para construção da API REST

Spring Data JPA: Para comunicação com o banco de dados

Hibernate: ORM para mapeamento objeto-relacional

H2 / PostgreSQL: Banco de dados relacional (dependendo do ambiente)


📦 Como Rodar o Projeto

Pré-requisitos

Node.js (>= 18)

Angular CLI (>= 17)

Java (>= 17)

Maven

Banco de dados PostgreSQL ou H2

🖥️ Backend

# Clone o repositório
git clone https://github.com/seu-usuario/agenda-app.git
cd agenda-app/backend

# Configure o banco de dados no application.properties

# Compile e execute a aplicação
mvn spring-boot:run

O backend rodará em http://localhost:8080.

🌐 Frontend

cd agenda-app/frontend

# Instale as dependências
npm install

# Execute a aplicação
ng serve

O frontend estará disponível em http://localhost:4200.

🛠️ Funcionalidades

📅 CRUD de eventos na agenda

📍 Seleção de datas com Angular Material Datepicker

🎨 Interface moderna com Material Design

🤝 Contribuindo

Sinta-se à vontade para contribuir com o projeto! Basta:

Fazer um fork do repositório

Criar uma branch (feature/minha-feature)

Fazer um commit das suas alterações

Criar um Pull Request
