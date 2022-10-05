import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    @DisplayName("get proper exception when argument == null")
    @Test
    public void constructorTest1(){
        assertThrows(IllegalArgumentException.class,
                () -> {Hippodrome hippodrome = new Hippodrome(null);});

    }

    @DisplayName("get proper exception message when argument == null")
    @Test
    public void constructorTest2(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {Hippodrome hippodrome = new Hippodrome(null);});
        assertEquals(exception.getMessage(), "Horses cannot be null.");
    }

    @DisplayName("get proper exception when argument list is empty")
    @Test
    public void constructorTest3(){
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class,
                () -> {Hippodrome hippodrome = new Hippodrome(horses);});
    }

    @DisplayName("get proper exception message when argument list is empty")
    @Test
    public void constructorTest4(){
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {Hippodrome hippodrome = new Hippodrome(horses);});
        assertEquals(exception.getMessage(), "Horses cannot be empty.");
    }

    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            String name = "horse_" + i;
            double speed = 1.5 + i;
            horses.add(new Horse(name, speed));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse: horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinner(){
        Horse horse1 = new Horse("name1", 2.6, 2.5);
        Horse horse2 = new Horse("name2", 2.5, 5.5);
        Horse horse3 = new Horse("name3", 2.4, 7.5);
        Horse horse4 = new Horse("name4", 2.3, 9.5);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));
        assertSame(horse4, hippodrome.getWinner());
    }
}
