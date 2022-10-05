import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @DisplayName("get proper exception when name == null")
    @Test
    public void constructorTest1() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(null, 2.5, 0);
                });
    }

    @DisplayName("get proper exception message when name == null")
    @Test
    public void constructorTest2() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(null, 2.5, 0);
                });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @DisplayName("get proper exception when name consist of blank chars")
    @ParameterizedTest
    @ValueSource(strings = {"", "\t\t", "\n\n\n", "       "})
    public void constructorTest3(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(name, 2.5, 0);
                });
    }

    @DisplayName("get proper exception message when name consist of blank chars")
    @ParameterizedTest
    @ValueSource(strings = {"", "\t\t", "\n\n\n", "       "})
    public void constructorTest4(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(name, 2.5, 0);
                });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @DisplayName("get proper exception when speed < 0")
    @Test
    public void constructorTest5() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("Pegasus", -1.0, 0);
                });
    }

    @DisplayName("get proper exception message when speed < 0")
    @Test
    public void constructorTest6() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("Pegasus", -1.0, 0);
                });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @DisplayName("get proper exception when distance < 0")
    @Test
    public void constructorTest7() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("Pegasus", 2.5, -1.0);
                });
    }

    @DisplayName("get proper exception message when distance < 0")
    @Test
    public void constructorTest8() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("Pegasus", 2.5, -1.0);
                });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Pegasus", 2.5, 0);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals(nameValue, horse.getName());
    }

    @Test
    public void getSpeed() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Pegasus", 2.5, 0);
        Field speed = Horse.class.getDeclaredField("speed");
        speed.setAccessible(true);
        double speedValue = (double) speed.get(horse);
        assertEquals(speedValue, horse.getSpeed());
    }

    @Test
    public void getDistance1() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Pegasus", 2.5, 5.0);
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        double distanceValue = (double) distance.get(horse);
        assertEquals(distanceValue, horse.getDistance());
    }

    @Test
    public void getDistance2() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Pegasus", 2.5);
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        double distanceValue = (double) distance.get(horse);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void move1() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Pegasus", 2.5, 0);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.8})
    public void move2(double value) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Pegasus", 2.5, 0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);

            horse.move();

            assertEquals(0 + 2.5*Horse.getRandomDouble(0.2, 0.9), horse.getDistance());
        }
    }
}
