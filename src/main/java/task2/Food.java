package task2;

public interface Food extends Thing {
    boolean getProteins();

    boolean getFats();

    boolean getCarbohydrates();

    @Override
    String getName();
}
