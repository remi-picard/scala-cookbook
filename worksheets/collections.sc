/**
 * Worksheet about Collections in Scala.
 *
 * Which Collection to Use ? Sequence / Map / Set ?
 * @see https://alvinalexander.com/scala/how-to-choose-collection-class-scala-cookbook/
 *
 * Which collection methods to use ?
 * @see https://alvinalexander.com/scala/how-to-choose-scala-collection-method-solve-problem-cookbook/
 */
val c = List(1, 2, 3, 42, -42, 0)
val c2 = List(1, 2, 3, 4, 5, 6)
case class People(firstName: String, lastName: String, age: Int)
val people = List(People("Remi", "Picard", 32), People("John", "Doe", 28), People("Buffalo", "Bill", 28), People("?", "Toutankhamon", 3_365))


/**
 * Filtering methods
 * Collection => filtered Collection
 * Collection => Object
 */
c.mkString // ~ c.mkString("")
c.mkString("START [", " | ", "] END")

c.head
List().headOption
c.last
List().lastOption
c.init
c.tail

c filter(_ >= 0)
c filterNot(_ >= 0)

c.take(2)
c.takeWhile(_ < 10)
c.drop(2)
c.dropWhile(_ < 10)

/**
 * `slice` returns all elements beginning at index `from` and afterwards,
 * up until index `until` (excluding index `until`.)
 */
c.slice(1, 3)
c.slice(1, 10)

c find(_ >= 0)
c collect(_ >= 0)

c.distinct

// ~ c.sum
c.foldLeft(0)((acc, cur) => acc + cur)
c.foldLeft(0)(_ + _)
c.foldRight(0)(_ + _)
// ~ c.foldLeft(0)(_ + _)
c.reduceLeft(_ + _)
// ~ c.foldRight(0)(_ + _)
c.reduceRight(_ + _)


/**
 * Transformer methods
 * Collection => transformed Collection
 * <cite>
 *   A transformer method is a method that constructs a new collection from an existing collection,
 *   typically by applying an algorithm to the input collection.
 * </cite>
 */
c.sorted
c sortWith(_.compareTo(_) < 0)
people.sortWith((a, b) => a.age.compareTo(b.age) < 0)
people.sortBy(_.age)
c.reverse

c.map(_ + 1)
c.flatMap(f => List(f + 1))
// FOR comprehension
for (p <- people) yield p.firstName
val c3 = for {
  i <- c2
  j <- List(1)
} yield i * j
val c4 = for {
  i <- c2
  j <- List(0, 1, 2)
} yield i * j


//IN c but NOT IN c2
c diff c2
//IN c2 but NOT IN c
c2 diff c
c intersect c2
// ~
c2 intersect c
c union c2
// ~
c ++ c2

c zip c2
c.zipWithIndex
// zip takes the shortest list
List(1,2).zip(List(1,2,3))
// ~
List(1,2,3).zip(List(1,2))


/**
 * Grouping methods
 */
c.groupBy(_ >= 0)
people.groupBy(_.age)
people.partition(_.age >= 40)

c.sliding(2).toList
c.sliding(3).toList

c.splitAt(2)
// ~ takeWhile + dropWhile
c.span(_ < 10)
people unzip(p => (p.firstName, p.age))
c unzip(x => (x, x + 1))


/**
 * Informational & Mathematical methods
 */
c.foreach(println)

c.size
c.min
c.max
c.sum
c.product
c2.product
c count(_ >= 0)
c.segmentLength(_ >= 0)

c.isEmpty
c.nonEmpty

c.contains(42)
c.containsSlice(List(1, 2, 3))
c.canEqual(42)
c exists(_ >= 0)
c.forall(_ > -43)
c.startsWith(List(1, 2))
c.endsWith(List(0))
c.indexOf(42)
c.indexOfSlice(List(42, -42))
c.indexWhere(_ < 0)


/**
 * Others
 */
List(List(1,2), List(3,4), List(), List(5)).flatten

// Lazy view (~ Stream)
val view = c.view
view.map(_ + 1)
view.toString
//Only compute at this stage
view.mkString