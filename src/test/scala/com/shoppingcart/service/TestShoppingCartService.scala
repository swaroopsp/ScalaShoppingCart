package com.shoppingcart.service

import com.shoppingcart.service.ShoppingCartServiceImpl
import org.scalatest.{BeforeAndAfter, FlatSpec}

class TestShoppingCartService extends FlatSpec with BeforeAndAfter {

    val applePrice: BigDecimal = 0.6
    val orangePrice: BigDecimal = 0.25

    var service = new ShoppingCartServiceImpl()

    before {
        service.totalNumberOfApples.set(0)
        service.totalNumberOfOranges.set(0)
    }

    "A empty collection of oranges and apples" should "throw NoSuchElementException." in {
        val fruits = List()
        intercept[NoSuchElementException] {
            val totalBill = service.getTotalBill(fruits, applePrice, orangePrice)
        }
    }

    "Any fruit other than apple and orange" should "amount to 0 Pound." in {
        val fruits = List("pineapple", "grapes", "kiwi", "strawberry", "banana", "peer")
        val totalBill = service.getTotalBill(fruits, applePrice, orangePrice)
        val finalPrice = service.getFinalBill(totalBill = totalBill, applePrice, orangePrice)

        assert(finalPrice === 0.00)
    }

    "A collection of apples" should "return total amount after discount 3.0 for 10 apples." in {
        val apples = List("apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple")
        val totalBill = service.getTotalBill(apples, applePrice, 0)
        val finalPrice = service.getFinalBill(totalBill, applePrice, 0)

        assert(finalPrice === 3.0)
    }

    "A collection of oranges" should "return total amount after discount 1.75 for 10 oranges." in {
        service.totalNumberOfOranges.set(0)
        val apples = List("orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange")
        val totalBill = service.getTotalBill(apples, 0, orangePrice)
        val finalPrice = service.getFinalBill(totalBill, 0, orangePrice)

        assert(finalPrice === 1.75)
    }

    "A collection of oranges and apples" should "return total amount after discount 4.75 for 10 apples and 10 oranges." in {
        val fruits = List("apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple",
            "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange")
        val totalBill = service.getTotalBill(fruits, applePrice, orangePrice)
        val finalPrice = service.getFinalBill(totalBill = totalBill, applePrice, orangePrice)

        assert(finalPrice === 4.75)
    }
}
