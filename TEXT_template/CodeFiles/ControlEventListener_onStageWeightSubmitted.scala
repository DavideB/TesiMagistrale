class ControlEventListener(conf: SparkConf) extends JobProgressListener(conf) with Logging {
  var totaldurationremaining = -1L
  var totalStageRemaining = -1L
  var previous_profile_totalStages = 0L
  var previous_profile_totalduration = 0L
...
  override def onStageWeightSubmitted
  (stageSubmitted: SparkStageWeightSubmitted): Unit = synchronized {
    val stage = stageSubmitted.stageInfo
    val genstage = if (firstStageId != -1) 1 else 0
    stageIdToParentsIds(stage.stageId) = stageSubmitted.parentsIds

    if (totaldurationremaining == -1L) {
      totaldurationremaining = stageSubmitted.totalduration
      previous_profile_totalduration = totaldurationremaining 
    } else {
      totaldurationremaining = stageSubmitted.totalduration - stageSubmitted.executedstagesduration
    }
    if (totalStageRemaining == -1L) {
      totalStageRemaining = stageSubmitted.stageIds.size - 1 + genstage
      previous_profile_totalStages = totalStageRemaining
    } else {
      totalStageRemaining += stageSubmitted.stageIds.size - 1 + genstage - previous_profile_totalStages
    }
    stageIdToDuration(stage.stageId) = stageSubmitted.duration
...
    previous_profile_totalduration = stageSubmitted.totalduration
    previous_profile_totalStages = stageSubmitted.stageIds.size - 1 + genstage
  }
...
}