package task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart<T extends Food> {

    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;
    public Cart(Class<T> clazz, UMarket market){
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public Collection<T> getFoodstuffs(){
        return foodstuffs;
    }

    public void printFoodstuffs(){
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> {
            System.out.printf("[%d] %s (Белки: %s, Жиры: %s, Углеводы: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Да":"Нет",
                    food.getFats() ? "Да": "Нет",
                    food.getCarbohydrates() ? "Да": "Нет");
        });
    }

    public void cartBalancing(){
        AtomicBoolean balanced = new AtomicBoolean(foodstuffs.stream().anyMatch(Food::getProteins) &&
                foodstuffs.stream().anyMatch(Food::getFats) &&
                foodstuffs.stream().anyMatch(Food::getCarbohydrates));

        if (balanced.get()) {
            System.out.println("Корзина уже сбалансирована по БЖУ");
            return;
        }

        market.getThings(Food.class).stream()
                .filter(thing -> !balanced.get())
                .forEach(thing -> {
                    Food food = (Food) thing;
                    if (!foodstuffs.stream().anyMatch(Food::getProteins) && food.getProteins()) {
                        foodstuffs.add((T) thing);
                    } else if (!foodstuffs.stream().anyMatch(Food::getFats) && food.getFats()) {
                        foodstuffs.add((T) thing);
                    } else if (!foodstuffs.stream().anyMatch(Food::getCarbohydrates) && food.getCarbohydrates()) {
                        foodstuffs.add((T) thing);
                    }
                    balanced.set(foodstuffs.stream().anyMatch(Food::getProteins) &&
                            foodstuffs.stream().anyMatch(Food::getFats) &&
                            foodstuffs.stream().anyMatch(Food::getCarbohydrates));
                });

        if (balanced.get()) {
            System.out.println("Корзина уже сбалансирована по БЖУ");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ");
        }

    }
}
