package example

class MovieRecommenderSpec extends munit.FunSuite:
  
  test("normaliser les catégories en français") {
    assertEquals(MainInteractiveV9Friendly.normalizeCategory("drame"), "Drama")
    assertEquals(MainInteractiveV9Friendly.normalizeCategory("science fiction"), "Science Fiction")
  }

  test("la liste de films n'est pas vide") {
    assert(MainInteractiveV9Friendly.films.nonEmpty)
  }

  test("tous les films ont des catégories valides") {
    val validCategories = Set("Science Fiction", "Action", "Horreur", "Comédie", "Drama", "Crime")
    MainInteractiveV9Friendly.films.foreach { film =>
      assert(validCategories.contains(film.category))
    }
  }
