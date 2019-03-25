var symbolMap = HashMap[String, Int]()
var symbolName: String = ""
def runJob[T, U](
    rdd: RDD[T],
    func: (TaskContext, Iterator[T]) => U,
    partitions: Seq[Int],
    callSite: CallSite,
    resultHandler: (Int, U) => Unit,
    properties: Properties): Unit = {
  val actionCallSite = callSite.shortForm.replace(" at ", "_")
  symbolName = actionCallSite + "_" + symbolMap.getOrElseUpdate(actionCallSite, 0).toString()
  symbolsMap.put(symbolName, null) 
  symbolMap(actionCallSite) += 1 
  ...