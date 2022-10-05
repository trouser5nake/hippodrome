import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Disabled
public class MainTest {
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void mainTest(){
        try {
            Main.main(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
