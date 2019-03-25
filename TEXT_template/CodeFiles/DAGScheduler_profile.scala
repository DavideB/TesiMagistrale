  val jsonFile = sys.env.getOrElse("SPARK_HOME", ".") + 
    "/conf/" + sc.appName + ".json"

  val appJumboJson = if (Files.exists(Paths.get(jsonFile))) {
    io.Source.fromFile(jsonFile).mkString.parseJson
  } else null

  var appJson = if (appJumboJson != null && heuristicType > 2)
    heuristic.nextProfile(appJumboJson)
    else appJumboJson