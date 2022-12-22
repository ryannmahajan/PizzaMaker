package com.example.pizzamaker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pizza(
    val toppings: Map<Topping, ToppingPlacement> = emptyMap(),
    val size: PizzaSize = PizzaSize.Large
) : Parcelable {
    val price: Double
        get() = calculatePrice()

    private fun calculatePrice(): Double {
        return (9.99 + toppings.asSequence()
            .sumOf { (_, toppingPlacement) ->
                when (toppingPlacement) {
                    ToppingPlacement.Left, ToppingPlacement.Right -> 0.5
                    ToppingPlacement.All -> 1.0
                }
            } ).times(
                when (size) {
                    PizzaSize.Small -> 0.5
                    PizzaSize.Medium -> 1.0
                    PizzaSize.Large -> 1.5
                    PizzaSize.ExtraLarge -> 2.0
                }
            )
    }

    fun withTopping(topping: Topping, placement: ToppingPlacement?): Pizza {
        return copy(
            toppings = if (placement == null) {
                toppings - topping
            } else {
                toppings + (topping to placement)
            }
        )
    }

    fun withSize(pizzaSize: PizzaSize): Pizza {
        return copy(
            size = pizzaSize
        )
    }

}