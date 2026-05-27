# Post-Mortem Técnico

## Resumo executivo

Foi realizada uma análise comparativa entre duas estruturas de dados balanceadas, AVL e Red-Black, para uso em um cenário de roteamento de borda com regras de firewall.

O objetivo foi identificar qual estrutura oferece melhor comportamento para inserção, busca e remoção de regras em grande escala.

Com base nos testes realizados, a árvore AVL apresentou melhor desempenho de busca e menor altura final nos maiores volumes avaliados. A Red-Black apresentou quantidade ligeiramente menor de rotações, mas manteve uma altura maior, o que impactou o tempo de busca.

## Contexto do problema

O sistema analisado simula um Load Balancer com regras de roteamento/firewall. Em um ambiente real, essas regras precisam ser inseridas, consultadas e removidas constantemente.

Uma árvore binária de busca comum poderia se desbalancear em inserções sequenciais, gerando perda de desempenho. Por isso, foram comparadas duas estruturas auto-balanceadas:

- AVL;
- Red-Black.

## O que foi testado

Foram executadas três operações principais:

- inserção de regras;
- busca por regras;
- remoção de 20% das regras.

Os testes foram realizados com os seguintes volumes:

- 1.000 entradas;
- 10.000 entradas;
- 50.000 entradas;
- 100.000 entradas.

As duas estruturas receberam os mesmos dados, gerados com a mesma seed, para manter a comparação justa.

## Evidências coletadas

Os resultados foram salvos em results/benchmark_results.csv.

Além dos tempos em nanossegundos, também foram coletadas:

- altura final de cada árvore;
- quantidade total de rotações;
- tamanho final após remoção de 20%.

Nos testes com 100.000 entradas, a AVL terminou com altura 17, enquanto a Red-Black terminou com altura 30. Essa diferença favoreceu a AVL nas buscas.

## Impacto técnico

A escolha da estrutura afeta diretamente a latência do sistema.

Em um sistema de roteamento, a busca por regras costuma ser uma operação crítica. Se a árvore tiver altura menor, a quantidade de comparações tende a ser menor. Por isso, a AVL se mostrou mais adequada para um cenário em que a consulta de regras é prioridade.

A Red-Black continua sendo uma alternativa válida para cenários mais voláteis, com grande quantidade de alterações. Porém, neste teste específico, a AVL apresentou melhor equilíbrio entre altura e tempo de busca.

## Validação e controle de qualidade

Foram adicionados testes para verificar se as árvores continuam válidas depois das operações.

A AVL foi validada em relação ao fator de balanceamento e à altura dos nós.

A Red-Black foi validada em relação à cor da raiz, altura negra, relação entre nós vermelhos e ponteiros de pai.

Esses testes ajudam a garantir que os resultados de desempenho foram coletados sobre estruturas corretas, e não sobre árvores corrompidas.

## Decisão recomendada

A estrutura recomendada para o cenário testado é a AVL.

Motivos principais:

- menor altura final;
- melhor desempenho de busca nos maiores volumes;
- invariantes mantidas após inserção e remoção;
- comportamento adequado para sistemas com foco em consulta rápida.

## Ações futuras

Para melhorar a análise, seria recomendado:

- executar cada benchmark mais de uma vez e calcular média;
- separar tempo de inserção, busca e deleção em múltiplas rodadas;
- gerar gráficos automáticos a partir do CSV;
- testar também entradas aleatórias, além de entradas ordenadas;
- comparar o comportamento usando diferentes seeds.