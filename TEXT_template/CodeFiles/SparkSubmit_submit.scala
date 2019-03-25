private def submit(args: SparkSubmitArguments): Unit = {
    val (childArgs, childClasspath, sysProps, childMainClass) = prepareSubmitEnvironment(args)
    val argsFile = sys.env.getOrElse("SPARK_HOME", ".") + "/conf/args.txt"  
    val bw = new BufferedWriter(new FileWriter(argsFile)) 
    if (childArgs.size > 0) {
      bw.write(childArgs(0) + "\n")  
      bw.write(args.primaryResource + "\n")  
      for ( i <- 1 to childArgs.size - 1 ) {                                      
        bw.write(childArgs(i)+"\n")
      }
    }
    bw.close()
    ...
     .
     .
    .