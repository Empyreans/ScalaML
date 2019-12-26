package mapReduce

/**
 * Basic implementation of map reduce as show in the lecture.
 */
object BasicOperations {

  /**
   *
   * @param mapFun Apply to each KeyIn-ValueIn lyrics.txt to produce a List of KeyMOut-ValueMOut output
   * @param redFun Apply to a KeyMOut and all of it's values (List of ValueMOut) to produce a tuple of KeyROut and ValueROut
   * @param data   lyrics.txt key value pairs
   * @tparam KeyIn     lyrics.txt key type
   * @tparam ValueIn   lyrics.txt value type
   * @tparam KeyMOut   intermediary key type
   * @tparam ValueMOut intermediary value type
   * @tparam KeyROut   result key type
   * @tparam ValueROut result value type
   * @return
   */
  def mapReduce[KeyIn, ValueIn, KeyMOut, ValueMOut, KeyROut, ValueROut](
                                                                         data: List[(KeyIn, ValueIn)])(
                                                                         mapFun: ((KeyIn, ValueIn)) => List[(KeyMOut, ValueMOut)])(
                                                                         redFun: (((KeyMOut, List[ValueMOut])) => List[(KeyROut, ValueROut)])): List[(KeyROut, ValueROut)] = {
    reducer(redFun, sorter(mapper(mapFun, data)))
  }

  private def mapper[KeyIn, ValueIn, KeyMOut, ValueMOut](mapFun: ((KeyIn, ValueIn)) => List[(KeyMOut, ValueMOut)],
                                                         data: List[(KeyIn, ValueIn)]): List[(KeyMOut, ValueMOut)] = {
    data.flatMap(mapFun(_))
  }

  private def sorter[KeyMOut, ValueMOut](data: List[(KeyMOut, ValueMOut)]): List[(KeyMOut, List[ValueMOut])] = {
    data.groupBy(_._1).view.mapValues(X => X.map(_._2)).toList

  }

  private def reducer[KeyMOut, ValueMOut, KeyROut, ValueROut](redFun: ((KeyMOut, List[ValueMOut])) => List[(KeyROut, ValueROut)],
                                                              data: List[(KeyMOut, List[ValueMOut])]): List[(KeyROut, ValueROut)] = {
    data.flatMap(redFun(_))
  }

}
