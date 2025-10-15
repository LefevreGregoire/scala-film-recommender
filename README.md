# Système de Recommandation de Films en Scala

Un petit projet de recommandation de films fait en Scala 3 pour apprendre le langage.

## Description

C'est une application en ligne de commande qui recommande des films selon tes goûts. Tu notes quelques films et elle te propose d'autres films que tu pourrais aimer. J'ai utilisé une approche simple de filtrage collaboratif.

## Fonctionnalités

- Interface interactive dans le terminal
- Filtrage par catégorie (Science Fiction, Action, Horreur, Comédie, Drama, Crime, etc.)
- Système de notation de 1 à 5
- Recommandations personnalisées

## Installation

Il faut avoir Scala 3 et SBT installés.

```bash
git clone <ton-repo-url>
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

## Ce que j'ai appris

- Syntaxe de Scala 3 (indentation au lieu des accolades)
- Collections mutables et immutables
- Pattern matching
- Programmation fonctionnelle
- Tests avec munit

## Améliorations possibles

- Meilleur algorithme de recommandation (similitude cosinus)
- Sauvegarder les données utilisateur
- Plus de métadonnées sur les films (année, réalisateur, acteurs)
- Interface web

## Licence

MIT
