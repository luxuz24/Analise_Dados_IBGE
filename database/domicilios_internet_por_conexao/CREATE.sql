
DROP TABLE IF EXISTS domicilios_internet_por_conexao;

-- Cria a nova tabela 
CREATE TABLE domicilios_internet_por_conexao AS
SELECT
    cod AS "Cod.",
    regiao AS "Grandes Regiões e Unidades da Federação",
    ano AS "Ano",
	
    MAX(CASE WHEN tipo_conexao = 'Total' THEN valor END) AS "Total",
    MAX(CASE WHEN tipo_conexao = 'Discada' THEN valor END) AS "Discada",
    MAX(CASE WHEN tipo_conexao = 'Banda larga' THEN valor END) AS "Banda larga",
    MAX(CASE WHEN tipo_conexao = 'Somente banda larga' THEN valor END) AS "Somente banda larga",
    MAX(CASE WHEN tipo_conexao = 'Banda larga fixa' THEN valor END) AS "Banda larga fixa",
    MAX(CASE WHEN tipo_conexao = 'Somente banda larga fixa' THEN valor END) AS "Somente banda larga fixa",
    MAX(CASE WHEN tipo_conexao = 'Banda larga móvel' THEN valor END) AS "Banda larga móvel",
    MAX(CASE WHEN tipo_conexao = 'Somente banda larga móvel' THEN valor END) AS "Somente banda larga móvel",
    MAX(CASE WHEN tipo_conexao = 'Banda larga fixa e móvel' THEN valor END) AS "Banda larga fixa e móvel",
    MAX(CASE WHEN tipo_conexao = 'Somente banda larga fixa e móvel' THEN valor END) AS "Somente banda larga fixa e móvel"

FROM
    tabela_7313
WHERE
    -- Ignora as linhas de rodapé
    nivel NOT LIKE 'Fonte:%'
GROUP BY
    -- Agrupa por região E por ano, criando uma linha para cada
    cod, regiao, ano
ORDER BY
    -- Ordena primeiro pela região, depois pelo ano
    cod, ano;
