class HeuristicSymExControlUnlimited(conf: SparkConf) 
                      extends HeuristicControlUnlimited(conf) {
  override def nextProfile(appJJ: JsValue, 
      valExFlows: java.util.ArrayList[Integer] = null, 
      jobId: Int = 0): JsValue = {
    var setP = appJJ.asJsObject.fields
    val stageId = if (valExFlows != null) 
        setP(valExFlows.get(0).toString()).asJsObject.fields("0")
        .asJsObject.fields("jobs")
        .asJsObject.fields(jobId.toString())
        .asJsObject.fields("stages")
        .convertTo[List[Int]].sortWith((x, y) => x < y).apply(0)
    if (valExFlows != null) 
      setP = setP.filter(
        {case (k,v) => valExFlows.exists(x => x == k.toInt)})
      var wCaseProfId = setP.toList.map({ case (k, profile) => {
        val numStage = profile.asJsObject.fields.filter(
              {case (k, stage) => {
              !stage.asJsObject.fields("skipped")
              .convertTo[Boolean]}})
        .size()
        (k, numStage)}}).reduce({ (x, y) => {
          if (x._2 > y._2)  x else y
         }})._1;
    setP(wCaseProfId)
  }
}