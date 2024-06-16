Alunos: Carlos Eduardo do Amaral - BP3030091.
        Lucas de Morais Pereira - BP3029905.

Projeto Final QSW - Orientações para utilização.

O projeto foi feito utilizando Linguagem Java com WindowBuilder na IDE Eclipse, caso seja de desejo ter a vizualização das telas implementadas, cobertura da codificação e realização dos testes unitarios, deveram ser instaladas Bibliotecas responsáveis.

1 - Utilizando a IDE Eclipse, acesse a aba Help no menu e depois clique em Eclipse MarketPlace. Pesquisa pelas bibliotecas WindowBuilder Current e EclEmma Java Code Coverage 3.1.9, aceite os termos e instale as bibliotecas.

2 - Para banco de dados utilizamos a ferramenta MySql Workbench, juntamente com os servidores Xampp, para utilização da porta 3306, você pode baixa-los nos sites https://dev.mysql.com/downloads/workbench/ e https://www.apachefriends.org/pt_br/download.html

3 - Após as instalações inicie o Xampp e clique em Start nos serviõs do Módulo MySql, dentro do workbench utilize a conexão com usuário root na porta 3306, abra o arquivo SistemaAcademico na pasta do Projet e inicie a construção das tabelas e inserts.

4 - Depois disso o projeto podera ser testado normalmente.

Importante: Para a utilização de inserts e selects diretamente da codificação, precisamos utilizar a biblioteca mysql-connector-j-8.4.0, o arquivo JAR já está presente nas pasta do Projeto, dentro do Folder Biblioteca_SQL Java, como padrão a biblioteca referencia o diretório do computador que foi colocado pelo ultima vez, para fazer a atualização do diretório, clique com o botão direito do Mouse em cima do projeto já importado para o eclipse, encontre a opção Build Path e clique em Configure Build Path, após isso selecione a janela Libraries, em ModulePath estara referenciado a biblioteca com outro diretório, cliquem com o botão esquerdo em cima dele e depois em edit, então procre pelo arquivo Jar no diretório em que o projeto foi baixado, por ultimo clique em Apply and Close.
