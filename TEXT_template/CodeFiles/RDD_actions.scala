def count(): Long = {
val res = sc.runJob(this, Utils.getIteratorSize _).sum
sc.resultComputed(res)
res
}

def collect(): Array[T] = withScope {
val results = sc.runJob(this, (iter:Iterator[T]) => iter.toArray)
val res = Array.concat(results: _*)
sc.resultComputed(res)
res
}

def reduce(f: (T, T) => T): T = withScope {
val cleanF = sc.clean(f)
val reducePartition: Iterator[T] => Option[T] = iter => {
  if (iter.hasNext) {
    Some(iter.reduceLeft(cleanF))
  } else {
    None
  }
}
var jobResult: Option[T] = None
val mergeResult = (index: Int, taskResult: Option[T]) => {
  if (taskResult.isDefined) {
    jobResult = jobResult match {
      case Some(value) => Some(f(value, taskResult.get))
      case None => taskResult
    }
  }
}
sc.runJob(this, reducePartition, mergeResult)
jobResult.getOrElse(throw new UnsupportedOperationException("empty collection"))
val res = jobResult.getOrElse(throw new UnsupportedOperationException("empty collection"))
sc.resultComputed(res)
res
}  