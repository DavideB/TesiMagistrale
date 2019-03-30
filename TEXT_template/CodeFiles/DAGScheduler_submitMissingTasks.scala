private[spark] class DAGScheduler(
    ...
  private def submitMissingTasks(stage: Stage, jobId: Int) {
    ...
    if (tasks.nonEmpty) {
      ...
      if (appJson != null) {
        val stageJson = appJson.asJsObject.fields(stage.id.toString)
        val submittedStageId = stage.id
        var stageId = stage.id
        // Adding resilience in case of profile does not match no. stages
        val highestStageIdInProfile = appJson.asJsObject.fields.keys.size - 1
        if (stage.id > highestStageIdInProfile) {
            stageId = highestStageIdInProfile
            logInfo(s"Submitted Stage ID not contained in appJSON profile. Submitted Stage ID: $submittedStageId, " +
              s"Highest Stage ID in appJSON profile: $highestStageIdInProfile" )
          }
        val stageJson = appJson.asJsObject.fields(stageId.toString)
        val totalduration = appJson.asJsObject.fields("0").asJsObject.fields("totalduration").convertTo[Long]
        val duration = stageJson.asJsObject.fields("duration").convertTo[Long]
        val weight = stageJson.asJsObject.fields("weight").convertTo[Long]
        val stageJsonIds = appJson.asJsObject.fields.keys.toList.filter(id =>
          appJson.asJsObject.fields(id).asJsObject.fields("nominalrate").convertTo[Double] != 0.0)
        val executedstagesduration = appJson.asJsObject.fields.filter(stage =>
                                          stage._1.toInt < stageId)
                                    .foldLeft(0L){ (acc, elem) => acc + elem._2.asJsObject.fields("duration").convertTo[Long] }          
        listenerBus.post(SparkStageWeightSubmitted(stage.latestInfo, properties,
          weight,
          duration,
          totalduration,
          stageJson.asJsObject.fields("parentsIds").convertTo[List[Int]],
          stageJson.asJsObject.fields("nominalrate").convertTo[Double],
          stageJson.asJsObject.fields("genstage").convertTo[Boolean],
          stageJsonIds))
          stageJsonIds,
          executedstagesduration))
      }
      else {
        logError("NO JSON FOR APP: " + jsonFile)
        ...