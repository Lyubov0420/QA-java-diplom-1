package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class AddIngredientBurgerTest {
    private Burger burger;
    private final Ingredient testIngredient;

    public AddIngredientBurgerTest(Ingredient ingredient) {
        this.testIngredient = ingredient;
    }

    @Parameterized.Parameters(name = "Тестируемый ингредиент: {0}")
    public static List<Object[]> testIngredients() {
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getName()).thenReturn("Соус");
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getName()).thenReturn("Начинка");
        when(filling.getType()).thenReturn(IngredientType.FILLING);

        return Arrays.asList(
                new Object[]{sauce},
                new Object[]{filling}
        );
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void shouldAddIngredientToBurger() {
        burger.addIngredient(testIngredient);
        assertTrue("Ингредиент должен быть добавлен в бургер",
                burger.ingredients.contains(testIngredient));
    }

    @Test
    public void shouldAddIngredientToCorrectPosition() {
        burger.addIngredient(testIngredient);
        assertEquals("Ингредиент должен быть добавлен в конец списка",
                0, burger.ingredients.indexOf(testIngredient));
    }
}