package com.example.pizzamaker.model

import androidx.annotation.StringRes
import com.example.pizzamaker.R

enum class PizzaSize(
    @StringRes val label: Int
) {
    Small(R.string.small),
    Medium(R.string.medium),
    Large(R.string.large),
    ExtraLarge(R.string.extra_large)
}