package example

import scala.io.StdIn.readLine
import scala.collection.mutable
import scala.util.Random

// Application principale de recommandation de films
object MainInteractiveV9Friendly:

  // Classe pour représenter un film avec son titre et sa catégorie
  case class Film(title: String, category: String)

  val films = List(
    Film("Avatar", "Science Fiction"),
    Film("Inception", "Science Fiction"),
    Film("Matrix", "Science Fiction"),
    Film("Interstellar", "Science Fiction"),
    Film("Jurassic Park", "Science Fiction"),
    Film("Star Wars: A New Hope", "Science Fiction"),
    Film("The Martian", "Science Fiction"),
    Film("Blade Runner 2049", "Science Fiction"),
    Film("Arrival", "Science Fiction"),
    Film("Minority Report", "Science Fiction"),
    Film("Gladiator", "Action"),
    Film("The Dark Knight", "Action"),
    Film("Avengers: Endgame", "Action"),
    Film("Mad Max: Fury Road", "Action"),
    Film("Die Hard", "Action"),
    Film("John Wick", "Action"),
    Film("Casino Royale", "Action"),
    Film("The Bourne Identity", "Action"),
    Film("Terminator 2: Judgment Day", "Action"),
    Film("Mission Impossible: Fallout", "Action"),
    Film("Get Out", "Horreur"),
    Film("It", "Horreur"),
    Film("A Quiet Place", "Horreur"),
    Film("The Conjuring", "Horreur"),
    Film("Halloween", "Horreur"),
    Film("Superbad", "Comédie"),
    Film("Step Brothers", "Comédie"),
    Film("The Hangover", "Comédie"),
    Film("Mean Girls", "Comédie"),
    Film("Forrest Gump", "Drama"),
    Film("Good Will Hunting", "Drama"),
    Film("The Green Mile", "Drama"),
    Film("Pulp Fiction", "Crime"),
    Film("The Godfather", "Crime"),
    Film("Se7en", "Crime"),
    Film("Goodfellas", "Crime")
  )

  // Map pour stocker les notes de chaque utilisateur
  // Structure: Map[nomUtilisateur, Map[titreFilm, note]]
  val ratings = mutable.Map[String, mutable.Map[String, Double]]()

  // --- Normalisation catégorie ---
  // Convertit les noms de catégories en français vers l'anglais standardisé
  def normalizeCategory(input: String): String =
    input.trim.toLowerCase match
      case "drame" => "Drama"
      case "science fiction" => "Science Fiction"
      case "comédie" => "Comédie"
      case "horreur" => "Horreur"
      case "action" => "Action"
      case "romance" => "Romance"
      case "fantasy" => "Fantasy"
      case "crime" => "Crime"
      case "animation" => "Animation"
      case other => other.capitalize

  // --- Recommandations ---
  // Recommande des films basés sur ce que d'autres utilisateurs avec des goûts similaires ont aimé
  def recommendFor(user: String, categoryFilter: Option[String]): Seq[(String, Double)] =
    val userRatings = ratings.getOrElseUpdate(user, mutable.Map())
    val otherUsers = ratings.keySet.diff(Set(user))

    // Filtrer les films selon la catégorie choisie et ceux pas encore notés
    val candidateFilms = categoryFilter match
      case Some(cat) => films.filter(f => f.category == cat && !userRatings.contains(f.title)).map(_.title)
      case None => films.map(_.title).filterNot(userRatings.contains)

    // Pour chaque film candidat, calculer un score basé sur les notes des autres users
    candidateFilms.map { movie =>
      val category = films.find(_.title == movie).map(_.category).getOrElse("")
      val scores = otherUsers.flatMap { other =>
        ratings(other).filter { case (m, _) =>
          films.find(_.title == m).exists(_.category == category)
        }.values
      }
      val predicted = if scores.nonEmpty then scores.sum / scores.size else 3.0
      movie -> predicted
    }.toList.sortBy(-_._2)  // Trier par score décroissant

  // --- Affichage ---
  def displayRecs(user: String, recs: Seq[(String, Double)], max: Int): Unit =
    println(s"\n🎬 Recommandations pour $user :\n")
    recs.take(max).foreach { case (movie, score) =>
      val bars = "■" * (math.round(score).toInt)
      println(f"- $movie%-40s [$bars] (${score}%.2f)")
    }

  // --- Interaction ---
  def main(args: Array[String]): Unit =
    println("Bienvenue ! Quel est ton nom ?")
    val user = readLine().trim
    if !ratings.contains(user) then ratings(user) = mutable.Map()

    println("\nSi tu veux, choisis une catégorie (Horreur, Science Fiction, Comédie, Action, Romance, Fantasy, Drama, Crime, Animation) ou laisse vide :")
    val categoryInput = readLine()
    val normalized = normalizeCategory(categoryInput)
    val categoryFilter = if normalized.nonEmpty then Some(normalized) else None

    println("\nCombien de films veux-tu noter ? (défaut 5) :")
    val numToRate = readLine().trim match
      case s if s.nonEmpty => try s.toInt catch case _: Exception => 5
      case _ => 5

    println("\nCombien de recommandations afficher ? (défaut 10) :")
    val numRecs = readLine().trim match
      case s if s.nonEmpty => try s.toInt catch case _: Exception => 10
      case _ => 10

    val moviesPool = categoryFilter match
      case Some(cat) => films.filter(_.category == cat).map(_.title)
      case None => films.map(_.title)

    val moviesToRate = Random.shuffle(moviesPool).take(numToRate)

    println("\nNote quelques films que tu as vus (1 à 5). Laisse vide pour passer :")
    for movie <- moviesToRate do
      val input = readLine(s"$movie : ").trim
      if input.nonEmpty then
        try
          val note = input.toDouble
          if note >= 1 && note <= 5 then ratings(user)(movie) = note
          else println("Note invalide, elle doit être entre 1 et 5. Ignorée.")
        catch case _: Exception => println("Note invalide, ignorée.")

    val recs = recommendFor(user, categoryFilter)
    displayRecs(user, recs, numRecs)
