private[scheduler] def numTotalJobs: Int = nextJobId.get()

def resultComputed(result: Any ): Unit = {
  if (heuristicType > 2) {
    symbolsMap(symbolName) = result
    val resultType = ClassTag(result.getClass)
    var new_validExecFlows = guardEvalMethod.invoke(guardEvalObj,
        symbolsMap).asInstanceOf[java.util.ArrayList[Integer]]
    if (new_validExecFlows.size() > 0)
      validExecFlows = new_validExecFlows
    else
      println("Warning! GuardEvaluator returned an empty set of profile ids")
    val highestJobId: Int = 
      if (validExecFlows != null) {
        appJumboJson.asJsObject.fields(validExecFlows.get(0).toString())
        .asJsObject.fields("0").asJsObject.fields("jobs")
        .asJsObject.fields.keys.max.toInt}
      else 0
    appJson = if (numTotalJobs <= highestJobId) {
                heuristic.nextProfile(appJumboJson, validExecFlows, nextJobId.get())}
              else appJson
  }
}