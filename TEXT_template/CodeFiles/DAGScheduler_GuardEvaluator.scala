var guardEvalObj:Any = null
var guardEvalMethod: java.lang.reflect.Method = null
if (heuristicType > 2) {
  /*
   * DB - DagSymb enhancements
   * The following variables are needed to load the GuardEvaluator class from the application jar
   */
  val jarfile = new File(appJar)
  val classLoader = new URLClassLoader(Array(jarfile.toURI.toURL))
  val guardEvalClass = classLoader.loadClass(guardEvalClassname)
  val guardEvalConstructor = guardEvalClass.getConstructor()
  guardEvalObj = guardEvalConstructor.newInstance()
  val methods = guardEvalClass.getDeclaredMethods
  for (m <- methods) { 
    m.getName match {
      case "evaluateGuards" => guardEvalMethod = m
      case _ => 
    }
  }
} else {
  guardEvalObj = new core.src.main.scala.org.apache.spark.scheduler.GuardEvaluator
}