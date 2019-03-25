val heuristicType = conf.getInt("spark.control.heuristic", 0)
val heuristic: HeuristicBase =
  if (heuristicType == 1 && 
      conf.contains("spark.control.stagecores") && 
      conf.contains("spark.control.stagedeadlines") && 
      conf.contains("spark.control.stage"))
    new HeuristicFixed(conf)
  else if (heuristicType == 2)
    new HeuristicControlUnlimited(conf)
  else if (heuristicType == 3)
    new HeuristicSymExControlUnlimited(conf)
  else
    new HeuristicControl(conf)