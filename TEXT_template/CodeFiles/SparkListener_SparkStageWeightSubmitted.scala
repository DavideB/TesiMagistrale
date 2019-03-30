@DeveloperApi
case class SparkStageWeightSubmitted
(stageInfo: StageInfo, properties: Properties = null, weight: Long, duration: Long,
 totalduration: Long, parentsIds: List[Int],
 nominalrate: Double, genstage: Boolean, stageIds: List[String],
 executedstagesduration: Long = 0L)
  extends SparkListenerEvent