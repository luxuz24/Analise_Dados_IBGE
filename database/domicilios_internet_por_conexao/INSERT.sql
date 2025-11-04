
INSERT INTO domicilios_internet_por_conexao (
    "Cod.",
    "Grandes Regiões e Unidades da Federação",
    "Ano",
    "Total",
    "Discada",
    "Banda larga",
    "Somente banda larga",
    "Banda larga fixa",
    "Somente banda larga fixa",
    "Banda larga móvel",
    "Somente banda larga móvel",
    "Banda larga fixa e móvel",
    "Somente banda larga fixa e móvel"
)
VALUES (
    5,
    'Região Centro-Oeste',
    2019,
    4503000, -- Novo valor
    22000,   -- Novo valor corrigido
    4800000, -- Novo valor
    4726000, -- Novo valor
    3731000, -- Novo valor
    595000,  -- Novo valor
    4181000, -- Novo valor
    1037000, -- Novo valor
    3112000, -- Novo valor
    3086000  -- Novo valor
);

INSERT INTO domicilios_internet_por_conexao (
    "Cod.",
    "Grandes Regiões e Unidades da Federação",
    "Ano",
    "Total",
    "Discada",
    "Banda larga",
    "Somente banda larga",
    "Banda larga fixa",
    "Somente banda larga fixa",
    "Banda larga móvel",
    "Somente banda larga móvel",
    "Banda larga fixa e móvel",
    "Somente banda larga fixa e móvel"
)
VALUES (
    4,
    'Região Sul',
    2019,
    9870000, -- (Valor original: 9.870 mil)
    16000,   -- (Valor original: 16 mil)
    9862000, -- (Valor original: 9.862 mil)
    9684000, -- (Valor original: 9.684 mil)
    8169000, -- (Valor original: 8.169 mil)
    2158000, -- (Valor original: 2.158 mil)
    7639000, -- (Valor original: 7.639 mil)
    1405000, -- (Valor original: 1.405 mil)
    6012000, -- (Valor original: 6.012 mil)
    5942000  -- (Valor original: 5.942 mil)
);

-- Comando para a Região Brasil (Cod. 1)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    1, 'Brasil', 2019, 59763000, 101000, 59715000, 58716000, 46639000, 10806000, 48549000, 12694000, 35473000, 35078000
);

-- Comando para a Região Norte (Cod. 2)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    2, 'Região Norte', 2019, 3864000, 8000, 3857000, 3791000, 2222000, 570000, 3253000, 1547000, 1621000, 1602000
);

-- Comando para a Região Nordeste (Cod. 3)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    3, 'Região Nordeste', 2019, 13045000, 49000, 13007000, 12756000, 8441000, 2825000, 10053000, 4195000, 5492000, 5419000
);


INSERT INTO domicilios_internet_por_conexao (
    "Cod.",
    "Grandes Regiões e Unidades da Federação",
    "Ano",
    "Total",
    "Discada",
    "Banda larga",
    "Somente banda larga",
    "Banda larga fixa",
    "Somente banda larga fixa",
    "Banda larga móvel",
    "Somente banda larga móvel",
    "Banda larga fixa e móvel",
    "Somente banda larga fixa e móvel"
) VALUES (
             3,          -- Cód. (Sudeste)
             ' Região Sudeste',  -- Grandes Regiões...
             2019,       -- Ano
             27486000,   -- Total (27.486 * 1000)
             49000,      -- Discada (49 * 1000)
             27476000,   -- Banda larga (27.476 * 1000)
             27143000,   -- Somente banda larga (27.143 * 1000)
             21755000,   -- Banda larga fixa (21.755 * 1000)
             3316000,    -- Somente banda larga fixa (3.316 * 1000)
             24064000,   -- Banda larga móvel (24.064 * 1000)
             5578000,    -- Somente banda larga móvel (5.578 * 1000)
             18343000,   -- Banda larga fixa e móvel (18.343 * 1000)
             18177000    -- Somente banda larga fixa e móvel (18.177 * 1000)
         );


-- Inserindo dados de 2020 para BRASIL (Cod. 1)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    1, 'Brasil', 2020, 65620000, 92000, 65432000, 64234000, 52822000, 11218000, 53898000, 12294000, 41180000, 40539000
);

-- Inserindo dados de 2020 para a REGIÃO NORTE (Cod. 2)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    2, 'Região Norte', 2020, 4166000, 10000, 4156000, 4084000, 2697000, 622000, 3505000, 1423000, 2045000, 2004000
);

-- Inserindo dados de 2020 para a REGIÃO NORDESTE (Cod. 3)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    3, 'Região Nordeste', 2020, 14188000, 41000, 14147000, 13917000, 9660000, 2871000, 11135000, 4376000, 6682000, 6554000
);

-- Inserindo dados de 2020 para a REGIÃO SUDESTE (Cod. 4)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    4, 'Região Sudeste', 2020, 28469000, 26000, 28443000, 27953000, 24151000, 4807000, 23018000, 3674000, 19128000, 18889000
);

-- Inserindo dados de 2020 para a REGIÃO SUL (Cod. 5)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    5, 'Região Sul', 2020, 10439000, 10000, 10429000, 10141000, 8913000, 2032000, 8225000, 1344000, 6799000, 6686000
);

-- Inserindo dados de 2020 para a REGIÃO CENTRO-OESTE (Cod. 6)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    6, 'Região Centro-Oeste', 2020, 4920000, 4000, 4916000, 4799000, 4004000, 887000, 4124000, 1004000, 3088000, 3058000
);

-- Inserindo dados de 2021 para BRASIL (Cod. 1)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    1, 'Brasil', 2021, 65620000, 92000, 65432000, 64234000, 52822000, 11218000, 53898000, 12294000, 41180000, 40539000
);

-- Inserindo dados de 2021 para a REGIÃO NORTE (Cod. 2)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    2, 'Região Norte', 2021, 4166000, 10000, 4156000, 4084000, 2697000, 622000, 3505000, 1423000, 2045000, 2004000
);

-- Inserindo dados de 2021 para a REGIÃO NORDESTE (Cod. 3)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    3, 'Região Nordeste', 2021, 14188000, 41000, 14147000, 13917000, 9660000, 2871000, 11135000, 4376000, 6682000, 6554000
);

-- Inserindo dados de 2021 para a REGIÃO SUDESTE (Cod. 4)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    4, 'Região Sudeste', 2021, 28469000, 26000, 28443000, 27953000, 24151000, 4807000, 23018000, 3674000, 19128000, 18889000
);

-- Inserindo dados de 2021 para a REGIÃO SUL (Cod. 5)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    5, 'Região Sul', 2021, 10439000, 10000, 10429000, 10141000, 8913000, 2032000, 8225000, 1344000, 6799000, 6686000
);

-- Inserindo dados de 2021 para a REGIÃO CENTRO-OESTE (Cod. 6)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    6, 'Região Centro-Oeste', 2021, 4920000, 4000, 4916000, 4799000, 4004000, 887000, 4124000, 1004000, 3088000, 3058000
);

-- Inserindo dados de 2022 para BRASIL (Cod. 1)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    1, 'Brasil', 2022, 68914000, 100000, 68790000, 67433000, 58077000, 11233000, 57062000, 10218000, 46402000, 45982000
);

-- Inserindo dados de 2022 para a REGIÃO NORTE (Cod. 2)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    2, 'Região Norte', 2022, 4417000, 12000, 4405000, 4316000, 3090000, 704000, 3684000, 1595000, 2359000, 2305000
);

-- Inserindo dados de 2022 para a REGIÃO NORDESTE (Cod. 3)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    3, 'Região Nordeste', 2022, 14887000, 43000, 14844000, 14571000, 10957000, 2978000, 11758000, 3736000, 7868000, 7749000
);

-- Inserindo dados de 2022 para a REGIÃO SUDESTE (Cod. 4)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    4, 'Região Sudeste', 2022, 30134000, 31000, 30103000, 29540000, 26658000, 5178000, 24810000, 2805000, 21311000, 20985000
);

-- Inserindo dados de 2022 para a REGIÃO SUL (Cod. 5)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    5, 'Região Sul', 2022, 10983000, 9000, 10974000, 10669000, 9691000, 1838000, 8950000, 1130000, 7733000, 7625000
);

-- Inserindo dados de 2022 para a REGIÃO CENTRO-OESTE (Cod. 6)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    6, 'Região Centro-Oeste', 2022, 5234000, 4000, 5230000, 5105000, 4383000, 927000, 4296000, 887000, 3425000, 3393000
);

-- Inserindo dados de 2023 para BRASIL (Cod. 1)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    1, 'Brasil', 2023, 71533000, 104000, 71429000, 70080000, 61649000, 11835000, 59571000, 9757000, 49628000, 49191000
);

-- Inserindo dados de 2023 para a REGIÃO NORTE (Cod. 2)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    2, 'Região Norte', 2023, 4675000, 14000, 4661000, 4542000, 3428000, 757000, 3874000, 1785000, 2641000, 2586000
);

-- Inserindo dados de 2023 para a REGIÃO NORDESTE (Cod. 3)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    3, 'Região Nordeste', 2023, 15688000, 43000, 15645000, 15343000, 11956000, 3165000, 12352000, 3791000, 8663000, 8520000
);

-- Inserindo dados de 2023 para a REGIÃO SUDESTE (Cod. 4)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    4, 'Região Sudeste', 2023, 31332000, 32000, 31300000, 30686000, 28225000, 5614000, 25558000, 2753000, 22483000, 22215000
);

-- Inserindo dados de 2023 para a REGIÃO SUL (Cod. 5)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    5, 'Região Sul', 2023, 11232000, 9000, 11223000, 10932000, 10160000, 1637000, 9390000, 1141000, 8414000, 8277000
);

-- Inserindo dados de 2023 para a REGIÃO CENTRO-OESTE (Cod. 6)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    6, 'Região Centro-Oeste', 2023, 5467000, 5000, 5462000, 5346000, 4708000, 923000, 4524000, 878000, 3748000, 3714000
);

-- Inserindo dados de 2024 para BRASIL (Cod. 1)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    1, 'Brasil', 2024, 72846000, 98000, 72748000, 71415000, 63065000, 12053000, 60662000, 9650000, 50853000, 50388000
);

-- Inserindo dados de 2024 para a REGIÃO NORTE (Cod. 2)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    2, 'Região Norte', 2024, 4821000, 14000, 4807000, 4692000, 3600000, 778000, 3991000, 1814000, 2782000, 2719000
);

-- Inserindo dados de 2024 para a REGIÃO NORDESTE (Cod. 3)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    3, 'Região Nordeste', 2024, 16140000, 42000, 16098000, 15792000, 12411000, 3283000, 12695000, 3810000, 9013000, 8868000
);

-- Inserindo dados de 2024 para a REGIÃO SUDESTE (Cod. 4)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    4, 'Região Sudeste', 2024, 32062000, 28000, 32034000, 31435000, 28943000, 5747000, 26174000, 2858000, 23078000, 22716000
);

-- Inserindo dados de 2024 para a REGIÃO SUL (Cod. 5)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    5, 'Região Sul', 2024, 11472000, 9000, 11463000, 11171000, 10408000, 1664000, 9592000, 1121000, 8626000, 8472000
);

-- Inserindo dados de 2024 para a REGIÃO CENTRO-OESTE (Cod. 6)
INSERT INTO domicilios_internet_por_conexao (
    "Cod.", "Grandes Regiões e Unidades da Federação", "Ano", "Total", "Discada", "Banda larga", "Somente banda larga", "Banda larga fixa", "Somente banda larga fixa", "Banda larga móvel", "Somente banda larga móvel", "Banda larga fixa e móvel", "Somente banda larga fixa e móvel"
) VALUES (
    6, 'Região Centro-Oeste', 2024, 5617000, 5000, 5612000, 5495000, 4875000, 941000, 4647000, 856000, 3894000, 3855000
);
