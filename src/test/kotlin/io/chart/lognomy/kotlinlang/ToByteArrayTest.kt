package io.chart.lognomy.kotlinlang

import org.junit.jupiter.api.Test

class ToByteArrayTest {

    @Test
    fun testIntArray() : Unit {
        // 참고자료 : https://itandhumanities.tistory.com/2
//        var intArr1 = Array<Int>(5, {1})
        var intArr1 = arrayOf<Int>(1,2,3,4,5)
        var intArr2 = IntArray(5, {i -> i})
        intArr1.forEach {
            print(it.toString() + " ")
        }
        println()

        intArr2.forEach {
            print(it.toString() + " ")
        }
        println()
    }

    @Test
    fun testByteArray() : Unit {
        var arr1 = arrayOf<Byte>(0, 1, 2);
        arr1.forEach { print(it.toString() + " ") }
        println()

        var test = ByteArray(1)
        test.forEach { println(it) }

    }

    @Test
    fun testSizedArray(): Unit {
        val test = HashMap<String, Any>()
        test.put("a", "apple")
        test.put("b", "banana")
        test.put("c", "cherry")


        test.forEach{

        }
    }
}