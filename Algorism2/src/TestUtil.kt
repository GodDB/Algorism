fun <T> Collection<Collection<T>>.print(printName : String = "") {
    println("start $printName-------------------")
    this.forEach {
        println(it)
    }
    println("end $printName------------------")
}