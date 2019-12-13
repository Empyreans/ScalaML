import wordcount.{Processing, Sentiments}

val sentiAnalyse = new Sentiments("AFINN-111.txt")

val data = sentiAnalyse.getDocumentGroupedByCounts("MobyDickShort.txt", 10)

data.map(x => x._2.map(y => sentiAnalyse.sentiments.get(y) match {
  case Some(e) => e
  case None => 0
}))

