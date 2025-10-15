# Système de Recommandation de Films en Scala

Petit projet de recommandation de films fait en Scala 3.

## Description

Application en ligne de commande qui recommande des films selon tes goûts.

## Fonctionnalités

- Interface interactive dans le terminal
- Filtrage par catégorie (Science Fiction, Action, Horreur, Comédie, Drama, Crime, etc.)
- Système de notation de 1 à 5
- Recommandations personnalisées

## Installation

Scala 3 et SBT

```bash
git clone https://github.com/LefevreGregoire/scala-film-recommender
cd scala-recommender
sbt compile
```

## Utilisation

```bash
sbt run
```

ou

```bash
./run.sh
```

L'application te demande ton nom, tu peux choisir une catégorie de films, tu notes quelques films et tu reçois des recommandations.

## Exemple

```
Bienvenue ! Quel est ton nom ?
> Greg

Si tu veux, choisis une catégorie (...) ou laisse vide :
> Science Fiction

Combien de films veux-tu noter ? (défaut 5) :
> 3

Note quelques films que tu as vus (1 à 5). Laisse vide pour passer :
Inception : 5
Avatar : 4
Matrix : 5

🎬 Recommandations pour Greg :
- Interstellar                           [■■■■] (4.00)
- Blade Runner 2049                      [■■■■] (4.00)
...
```

## Tests

```bash
sbt test
```

## Licence

MIT
