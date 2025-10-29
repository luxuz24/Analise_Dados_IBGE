-- Remove a tabela se ela já existir, para evitar erros
DROP TABLE IF EXISTS domicilios_internet_por_conexao;

-- Cria a nova tabela para os dados de "Moradores"
CREATE TABLE domicilios_internet_por_conexao (
    "Cod."                                   INTEGER,
    "Grandes Regiões e Unidades da Federação" VARCHAR(255),
    "Ano"                                    INTEGER,
    "Total"                                  NUMERIC,
    "Discada"                                NUMERIC,
    "Banda larga"                            NUMERIC,
    "Somente banda larga"                    NUMERIC,
    "Banda larga fixa"                       NUMERIC,
    "Somente banda larga fixa"               NUMERIC,
    "Banda larga móvel"                      NUMERIC,
    "Somente banda larga móvel"              NUMERIC,
    "Banda larga fixa e móvel"               NUMERIC,
    "Somente banda larga fixa e móvel"       NUMERIC
);



