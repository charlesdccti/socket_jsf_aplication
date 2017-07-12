# socket_jsf_aplication
Trabalho da disciplina MATA59 -	REDES DE COMPUTADORES I do semestre 2013.2, desenvolvido pela dupla Charles Ferreira e Nemuel Pereira

Resumo do trabalho e da apresentação:


Nosso Primeiro Sistema de Arquivos Interativo do Brasil é um sistema na qual existe um servidor WEB e vários clientes, em que o cliente requisita qualquer tipo de arquivo ao servidor Web de arquivos ( utilizando sockets TCP - entrega confiável de Dados) do repositório do servidor, podendo ser de qualquer formato, todavia na apresentação apresentamos os listados abaixo:

arquivo de texto: txt, docx, pdf. 
Arquivo de Imagem: jpg. png, gif
Arquivo de Video: mp4, rmvb

Utilizando dois notebooks e uma rede wifi para um teste em tempo real, foi pedido por Italo um printScreen da tela para ser  enviado para o cliente. Fizemos o solicitado colocando a imagem do printScreen no Repositório do Servidor Web e através de uma solicitação cliente em que como é servidor web ele pode acessar o servidor e escolher o arquivo desejado  e logo após arquivo é enviado para a máquina cliente mostra na tela cliente em um JFrame contendo a imagem transferida via Socket. Funcionou com sucesso!.

Tecnologia Utilizada: 

Linguagem de Implementação: Java para Web
Framework: JSF e Primefaces 
Swing: JFrame, JPanel.
Outros: Css, xhtml, javascript.
Socket: TCP
