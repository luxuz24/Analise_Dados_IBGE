-- Remove a tabela se ela já existir, para evitar erros
DROP TABLE IF EXISTS distribuicao_percentual_moradores;

-- Cria a nova tabela para a Distribuição Percentual dos "Moradores"
CREATE TABLE distribuicao_percentual_moradores (
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


