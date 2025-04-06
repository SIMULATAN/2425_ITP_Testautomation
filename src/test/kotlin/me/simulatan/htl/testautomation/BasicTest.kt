package me.simulatan.htl.testautomation

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BasicTest : FunSpec({
	test("2 + 2 is 4") {
        val sum = add(2, 2)

        sum shouldBe 4
    }
})
