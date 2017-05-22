package com.shoppingcart.service

trait ShoppingCartService {
    def getTotalBill(inventory: List[String], appleAmount: BigDecimal, orangeAmount: BigDecimal): BigDecimal
    def getFinalBill(totalBill: BigDecimal, appleAmount: BigDecimal, orangeAmount: BigDecimal): BigDecimal
}
