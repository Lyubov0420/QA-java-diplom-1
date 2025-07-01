import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @InjectMocks
    private Burger burger;

    @Before
    public void setUp() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(ingredient1.getName()).thenReturn("cutlet");
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient1.getPrice()).thenReturn(50f);

        when(ingredient2.getName()).thenReturn("chili sauce");
        when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient2.getPrice()).thenReturn(30f);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceWithOnlyBun() {
        burger.setBuns(bun);
        assertEquals(200f, burger.getPrice(), 0.0f);
    }

    @Test
    public void testGetPriceWithBunAndIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(280f, burger.getPrice(), 0.0f);
    }

    @Test
    public void testGetReceiptWithOnlyBun() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        String expected = String.format("(==== %s ====)%n", bun.getName()) +
                String.format("(==== %s ====)%n", bun.getName()) +
                String.format("%nPrice: %f%n", 200.0f);

        assertEquals(expected, receipt);
    }

    @Test
    public void testGetReceiptWithBunAndIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        String receipt = burger.getReceipt();

        String expected = String.format("(==== %s ====)%n", bun.getName()) +
                String.format("= %s %s =%n", "filling", "cutlet") +
                String.format("= %s %s =%n", "sauce", "chili sauce") +
                String.format("(==== %s ====)%n", bun.getName()) +
                String.format("%nPrice: %f%n", 280.0f);

        assertEquals(expected, receipt);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveNonexistentIngredient() {
        burger.removeIngredient(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveNonexistentIngredient() {
        burger.moveIngredient(0, 1);
    }
}