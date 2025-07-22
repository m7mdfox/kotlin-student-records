package org.example

import kotlin.system.exitProcess
import kotlin.system.measureNanoTime

val students = listOf("Mohamed", "Ahmed", "Nour", "Ali", "Mazen", "Zaki")
val score = mapOf("Mohamed" to 90, "Ahmed" to 50, "Nour" to 65, "Ali" to 88, "Mazen" to 47, "Zaki" to 93)
val grad = mutableSetOf("Ahmed", "Ali")
val scoreList by lazy{
    val x = mutableListOf<Int>()
    students.filter { !grad.contains(it) }.forEach { x.add(score[it] as Int)  }
    x as List<Int>
}
val mutableStudents by lazy { students.toMutableList() }
fun main() {
    studentsToUppercase()
    studentsScoreOver80()
    println(passedStudents(score))
    println("================= Menu =================\n")
    println("1. View students scores\n2. Find student\n3. Exit")
    while (true) {
        print("Please enter a valid option: ")
        val input = readLine().toString()
        when (input) {
            "1" -> {
                displayStudents()
                break
            }

            "2" -> {
                findStudent()
                break
            }


            "3" -> exitProcess(0)

            else -> {
                println("Invalid input.")
            }
        }
    }


}

fun findStudent(){
    println("============ Student Finder ============\n")
    println("Enter student name: ")
    val name = readLine().toString()
    if (students.contains(name)){
        if (grad.contains(name)){
            println("Student \"${name}\" Graduated with score: ${score[name]}")
        }else{
            println("Student \"${name}\" currently has score of: ${score[name]}")
        }
    }else {
        println("Student \"${name}\" Not found!")
    }
    main()
}

fun displayStudents() {
    println("=========== Display Students ===========\n")
    println(students.filter { !grad.contains(it) }.fold("") { acc, student -> "$acc $student: ${score[student]} |" })
    println("Total students score : ${scoreList.reduce{ a, b -> a+b}}")

    main()
}

fun passedStudents(map: Map<String, Int>):String{
    var res:String = ""
    map.filter { it.value >= 60 }.forEach {res += "${it.key}: ${it.value}\n"}
    return res
}

fun studentsToUppercase(){
    mutableStudents.map{ student -> student.toUpperCase()}.forEach{ student -> print("$student ") }
    println()
}

fun studentsScoreOver80(){
    students.filter { !grad.contains(it) && (score[it] as Int) > 80 }.forEach { print("$it ") }
    println()
}