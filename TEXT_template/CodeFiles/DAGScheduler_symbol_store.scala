var symbolsMap = new java.util.HashMap[String, Any]()
var symbolName: String = ""            
var guardEvalClassname: String = ""            
var appJar: String = ""            
val argsFile = sys.env.getOrElse("SPARK_HOME", ".") + 
		"/conf/args.txt"  
var iter: Int = -2   
if (Files.exists(Paths.get(argsFile))) {
for (line <- Source.fromFile(argsFile).getLines) { 
    iter match { 
      case -2 => guardEvalClassname = line
      case -1 => appJar = line.split(":")(1)
      case _  => symbolsMap.put("arg" + iter, line)  
    }
    iter += 1
}
...