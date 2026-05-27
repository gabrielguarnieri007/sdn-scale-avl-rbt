# Análise Técnica Comparativa

## Objetivo

Esta análise compara o comportamento das árvores AVL e Red-Black no gerenciamento de regras de roteamento representadas por objetos PacketRule.

As operações avaliadas foram:

- inserção de regras;
- busca de regras;
- remoção de 20% das regras;
- altura final das árvores;
- quantidade total de rotações.

Os testes utilizaram a mesma seed de geração de dados para as duas estruturas, garantindo que AVL e Red-Black recebessem a mesma sequência de regras.

## Ambiente e metodologia

A implementação foi feita em Java, utilizando System.nanoTime() para medir os tempos das operações em nanossegundos.

A geração de dados foi feita com seed fixa:

```text
20240520