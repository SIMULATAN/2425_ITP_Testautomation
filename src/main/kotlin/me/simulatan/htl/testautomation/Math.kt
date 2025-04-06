package me.simulatan.htl.testautomation

fun add(a: Int, b: Int): Int {
	return a + b
}

fun div(a: Int, b: Int): Int {
	if (b == 0) {
		throw IllegalArgumentException("Division by zero is not allowed")
	}
	return a / b
}
