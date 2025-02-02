USE Trabalho_BD;

-- Adicionando nas Tabelas:

INSERT INTO Criptideo (Nome, Descricao, Tipo, Status_cr, Imagem_Caminho) VALUES
('Pé grande', 'Um primata bípede grande e alto, parecido com um gorila.', 'TERRESTRE', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Pe_grande.jpg'),
('Chupa-Cabra', 'Uma criatura que suga sangue de animais.', 'TERRESTRE', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Chupa_Cabra.jpg'),
('Dragão-de-Komodo', 'O maior lagarto do mundo.', 'TERRESTRE', 'CONFIRMADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Dragao_Komodo.jpg'),
('Lobisomem', 'Um homem que se transforma em lobo', 'TERRESTRE', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Lobisomen.jpg'),
('Yeti', 'Uma Criatura que vive no Himalaia.', 'TERRESTRE', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Yeti.jpeg'),
('Ornitorrinco', 'Um mamífero que bota ovo.', 'TERRESTRE', 'CONFIRMADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Onitorrinco.jpeg'),
('Monstro do lago Ness', 'Uma criptídeo aquático que habita o lago Ness.', 'AQUATICO', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Monstro_Lago_Ness.jpeg'),
('Homem-mariposa', 'Uma criatura voadora humaniode.', 'VOADOR', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Homem_Mariposa.jpeg'),
('Celacanto', 'Peixe pré-histórico considerado extinto até 1938.', 'AQUATICO', 'CONFIRMADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Celacanto.png'),
('Kraken', 'Monstro marinho que ataca navios.', 'AQUATICO', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Kraken.jpg'),
('Ocapi', 'Uma criatura que é uma mistura de girafa e zebra.', 'TERRESTRE', 'CONFIRMADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Ocapi.jpg'),
('Mokele-Mbembe', 'Um dinossauro aquático no Congo.', 'AQUATICO', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/mokele-mbembe.png'),
('Mapinguari', 'Um bípede parecido com macaco que viva na amazônia.', 'TERRESTRE', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Mapinguari.jpg'),
('Minhocão', 'Um verme ou cobra gigante que vive em rios e lagos.', 'TERRESTRE', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Minhocao.jpg'),
('Ropen', 'Um pterossauro vivo.', 'VOADOR', 'AVISTADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Ropen.jpeg'),
('Lula-Gigante', 'Uma espécie gigantesca de lula que vive nas profundidades.', 'AQUATICO', 'CONFIRMADO', '/home/braian/Documentos/Workspace/Java/Gedit/Trabalho_BD/database/imagens/Lula_Gigante.jpg');

INSERT INTO Avistamento (Local_Av, Pais, Data_Av) VALUES
('Floresta do Oregon.', 'Estados Unidos', '2024-01-15'), 
('Zona rural de Porto Rico.', 'Porto Rico', '2024-02-10'), 
('Ilha de Komodo.', 'Indonésia', '1912-07-15'), 
('Vilarejo no interior da França.', 'França', '2024-03-05'), 
('Montanhas do Himalaia.', 'Nepal', '2024-04-20'), 
('Rio Murray.', 'Austrália', '1799-01-01'), 
('Lago Ness.', 'Escócia', '2024-05-18'), 
('Point Pleasant, Virgínia Ocidental.', 'Estados Unidos', '1966-11-15'), 
('Costa de Chalumna.', 'África do Sul', '1938-12-23'), 
('Atlântico Norte, perto da Islândia.', 'Islândia', '2024-10-22'), 
('Florestas do Congo.', 'Congo', '1901-06-23'), 
('Rio Likouala-aux-Herbes.', 'Congo', '1983-05-15'), 
('Floresta Amazônica.', 'Brasil', '2024-07-25'), 
('Rio Araguaia.', 'Brasil', '2024-08-12'), 
('Nas proximidades de Papua Nova Guiné.', 'Papua Nova Guiné', '2024-09-09'), 
('Atlântico Norte, perto das Ilhas Faroé.', 'Ilhas Faroé', '2004-09-30'),
('Point Pleasant, Virgínia Ocidental.', 'Estados Unidos', '1966-11-15');

INSERT INTO Criptideo_Avistamento (ID_Criptideo, ID_Avistamento) VALUES
(1, 1),  -- Pé Grande
(2, 2),  -- Chupa-Cabra
(3, 3),  -- Dragão-de-Komodo
(4, 4),  -- Lobisomem
(5, 5),  -- Yeti
(6, 6),  -- Ornitorrinco
(7, 7),  -- Monstro do lago Ness
(8, 8),  -- Homem-mariposa
(9, 9),  -- Celacanto
(10, 10), -- Kraken
(11, 11), -- Ocapi
(12, 12), -- Mokele-Mbembe
(13, 13), -- Mapinguari
(14, 14), -- Minhocão
(15, 15), -- Ropen
(16, 16), -- Lula-Gigante
(8, 17); -- Homem-Mariposa 2

INSERT INTO Criptideo_Confirmado(ID_Criptideo, Nome_Cientifico, Data_Confirmacao, Fonte_Confirmacao, Observacoes) VALUES
(3, 'Varanus komodoensis', '1912-07-15', 'National Geographic', 'O maior lagarto do mundo foi descoberto na Ilha de Komodo, Indonésia.'),
(6, 'Ornithorhynchus anatinus', '1799-01-01', 'Royal Society of London', 'Um mamífero único que bota ovos, encontrado no Rio Murray, Austrália.'),
(9, 'Latimeria chalumnae', '1938-12-23', 'Journal of Natural History', 'Considerado extinto até ser encontrado na costa de Chalumna, África do Sul.'),
(11, 'Okapia johnstoni', '1901-06-23', 'Zoological Society of London', 'Uma espécie misteriosa descoberta nas florestas do Congo, mistura de girafa e zebra.'),
(16, 'Architeuthis dux', '2004-09-30', 'Smithsonian Institution', 'Fotografado pela primeira vez na história um exemplar vivo no Pacífico norte, perto das ilhas Ogasawara.');

INSERT INTO Testemunha (Nome, Sobrenome, Idade, Genero, Email, Telefone) VALUES
-- Pé Grande (Relato popularizado por Bob Gimlin e Roger Patterson)
('Bob', 'Gimlin', 84, 'M', 'bob.gimlin@gmail.com', NULL),
('Roger', 'Patterson', 39, 'M', NULL, NULL),

-- Chupa-Cabra (Relato popularizado por Madelyne Tolentino em Porto Rico)
('Madelyne', 'Tolentino', 30, 'F', 'madelyne.tolentino@hotmail.com', NULL),

-- Dragão-de-Komodo (Explorado por Peter Ouwens)
('Peter', 'Ouwens', 50, 'M', 'peter.ouwens@yahoo.com', '(+31) 123-456-789'),

-- Lobisomem (Legendas urbanas francesas)
('Jean', 'Rouge', 45, 'M', 'jean.rouge@orange.fr', NULL),

-- Yeti (Sir Edmund Hillary e relatos dos xerpas)
('Edmund', 'Hillary', 88, 'M', 'edmund.hillary@outlook.com', NULL),
('Tenzing', 'Norgay', 39, 'M', NULL, '(+977) 12-34-56-78'),

-- Ornitorrinco (Introduzido na ciência por George Shaw)
('George', 'Shaw', 60, 'M', 'george.shaw@icloud.com', NULL),

-- Monstro do Lago Ness (Relato popularizado por Robert Kenneth Wilson)
('Robert', 'Wilson', 53, 'M', 'robert.wilson@btinternet.com', NULL),

-- Homem-Mariposa (Relatos de jornalistas como Mary Hyre)
('Mary', 'Hyre', 37, 'F', 'mary.hyre@gmail.com', NULL),

-- Celacanto (Redescoberto por Marjorie Courtenay-Latimer)
('Marjorie', 'Courtenay-Latimer', 55, 'F', 'marjorie.courtenay-latimer@live.co.za', '(+27) 82-345-6789'),

-- Kraken (Lendas dos marinheiros e descrito por Erik Pontoppidan)
('Erik', 'Pontoppidan', 62, 'M', 'erik.pontoppidan@online.dk', '(+45) 123-456-789'),

-- Ocapi (Descritivo por Sir Harry Johnston)
('Harry', 'Johnston', 70, 'M', 'harry.johnston@btinternet.com', '(+243) 81-345-6789'),

-- Mokele-Mbembe (Relatos de Alfred Aloysius Smith)
('Alfred', 'Smith', 47, 'M', 'alfred.smith@gmail.com', NULL),

-- Mapinguari (Relatos indígenas no Brasil)
('Cacique', 'Tukano', 65, 'M', 'cacique.tukano@uol.com.br', NULL),

-- Minhocão (Relatos populares em áreas rurais)
('João', 'Silva', 49, 'M', 'joao.silva@hotmail.com', '(62) 9876-5432'),

-- Ropen (Explorado por Carl Baugh)
('Carl', 'Baugh', 54, 'M', 'carl.baugh@gmail.com', NULL),

-- Lula-Gigante (Relatos de marinheiros e estudos por Johan Hjort)
('Johan', 'Hjort', 63, 'M', 'johan.hjort@t-online.de', '(+298) 123-456'),

-- Homem-Mariposa (Relatos do fazendeiro)
('Newell', 'Partridge', 50, 'M', null, null);

INSERT INTO Avistamento_Testemunha (ID_Avistamento, ID_Testemunha) VALUES 
(1, 1), (1, 2), (2, 3), (3, 4), (4, 5), (5, 6), (5, 7), (6, 8),
(7, 9), (8, 10), (9, 11), (10, 12), (11, 13), (12, 14), (13, 15),
(14, 16), (15, 16), (16, 18), (17, 19);

INSERT INTO Pesquisador (ID_Testemunha, Area_Atuacao, Instituicao) VALUES
(4, 'Zoologia', 'National Geographic'), -- Peter Ouwens (Dragão-de-Komodo)
(6, 'Naturalista', 'Royal Society of London'), -- George Shaw (Ornitorrinco)
(9, 'Zoologia', 'Journal of Natural History'), -- Marjorie Courtenay-Latimer (Celacanto)
(11, 'Zoologia', 'Zoological Society of London'), -- Harry Johnston (Ocapi)
(16, 'Oceanografia', 'Smithsonian Institution'); -- Johan Hjort (Lula-Gigante)
