package mapReduce

//Import using "import MapReduceImplicits._" to allows usage of .mapReduce for KeyValue lists, e.g. List.empty[(Int,String)].mapReduce(...)
object MapReduceImplicits {

  implicit class ListWithMapReduce[KeyIn, ValueIn](data: List[(KeyIn, ValueIn)]) {
    def mapReduce[KeyMOut, ValueMOut, KeyROut, ValueROut](mapFun: ((KeyIn, ValueIn)) => List[(KeyMOut, ValueMOut)])(
      redFun: ((KeyMOut, List[ValueMOut])) => List[(KeyROut, ValueROut)]): List[(KeyROut, ValueROut)] = {
      BasicOperations.mapReduce(data)(mapFun)(redFun)
    }
  }

}
