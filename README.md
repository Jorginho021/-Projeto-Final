🏢 PlaceHolders – Sistema de Reserva de Ambientes

O PlaceHolders é um sistema desenvolvido em Java com integração ao MySQL, voltado para o gerenciamento e reserva de salas, espaços e ambientes utilizados por empresas, clubes ou instituições. Seu objetivo é facilitar a organização de reservas, permitindo que colaboradores ou clientes possam agendar locais de forma rápida, segura e centralizada.

🚀 Funcionalidades Principais

📋 Cadastro de ambientes Informações detalhadas como tipo de sala, capacidade, recursos disponíveis e localização.

🕒 Sistema de reservas Registro de reservas com data, horário, responsável e finalidade do uso.

🔄 Controle de disponibilidade em tempo real Exibição dinâmica dos espaços livres ou ocupados.

🏢 Suporte a múltiplos locais Gestão de reservas em diferentes andares, prédios ou filiais.

👥 Controle de usuários e permissões Perfis distintos para administradores, colaboradores e clientes.

🧾 Histórico completo de reservas Registro detalhado para auditoria e consultas futuras.

🛠️ Tecnologias Utilizadas

Java SE 17 → Linguagem principal de desenvolvimento

Swing → Interface gráfica desktop intuitiva

MySQL → Banco de dados relacional para persistência das informações

JDBC → API para conexão com o banco de dados

DAO/DTO Pattern → Organização e separação entre regras de negócio e acesso a dados

Maven / Ant (NetBeans) → Gerenciamento de dependências e build do projeto

📂 Estrutura do Projeto src/ ├── dao/ # Classes de acesso ao banco de dados ├── dto/ # Objetos de transferência de dados ├── img/ # Ícones e imagens do sistema └── view/ # Telas e formulários (interface gráfica Swing)

Além disso, o projeto contém o arquivo build.xml, responsável pelo processo de compilação e empacotamento (build) do sistema através do Apache Ant, utilizado pelo NetBeans.

🧩 Exemplo de trecho do arquivo build.xml: Builds, tests, and runs the project CrudProjeto.

Esse script define o nome do projeto, as tarefas de compilação, e importa as configurações automáticas do NetBeans, permitindo executar os comandos:

Clean → Limpa arquivos compilados antigos

Build → Compila e gera o JAR do projeto

Run → Executa o sistema diretamente pelo NetBeans

📅 Histórico de Atualizações

v1.0.0 – (22/09/2025)

Versão estável com cadastro de ambientes e sistema de reservas.

v0.3.0 – (15/09/2025)

Adição de relatórios e melhorias na interface.

v0.2.0 – (08/09/2025)

Implementação do módulo de notificações e controle de usuários.

v0.1.0 – (01/09/2025)

Estrutura inicial criada (DAO, DTO, VIEW).

👨‍💻 Desenvolvedores

Projeto idealizado e desenvolvido por:

Integrante: Isaac Ramos Integrante: Jorge Cardoso de Jesus

🗄️ Informações sobre o Banco de Dados

O sistema utiliza MySQL como banco de dados principal, contendo tabelas para:

Usuários – controle de credenciais e permissões

Ambientes – dados das salas e espaços disponíveis

Reservas – histórico e agendamentos

Avaliações – feedback dos usuários

Logs – registro de atividades do sistema

📜 Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
