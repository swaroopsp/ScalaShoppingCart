package com.shoppingcart.service

import java.util.concurrent.atomic.AtomicInteger

class ShoppingCartServiceImpl extends ShoppingCartService {
    val totalNumberOfApples = new AtomicInteger(0)
    val totalNumberOfOranges = new AtomicInteger(0)

    def getTotalBill(inventory: List[String], appleAmount: BigDecimal, orangeAmount: BigDecimal): BigDecimal =
        inventory.foldRight(0.0: BigDecimal) {
            if (inventory.isEmpty)
                throw new NoSuchElementException("empty list")
            (fruit, amount) =>
                fruit match {
                    case "apple" | "Apple" =>
                        totalNumberOfApples.incrementAndGet()
                        amount + appleAmount
                    case "orange" | "Orange" =>
                        totalNumberOfOranges.incrementAndGet()
                        amount + orangeAmount
                    case _ => amount
                }
        }

    def getFinalBill(totalBill: BigDecimal, appleAmount: BigDecimal, orangeAmount: BigDecimal): BigDecimal =
        totalBill -
            ((totalNumberOfApples.get() / 2) * appleAmount) -
            ((totalNumberOfOranges.get() / 3) * orangeAmount)
}
