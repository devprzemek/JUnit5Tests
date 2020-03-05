import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

class UnitConverterTest {
    @DisplayName("createNegative")
    @Test()
    void createNegativePound(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Pound(new BigDecimal(-10));
        });
    }

    @Test()
    void createNegativeKilogram(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new Kilogram(new BigDecimal(-10));
        });
    }

    @Test()
    void kiloToPound(){
        final Kilogram kilogram = new Kilogram(BigDecimal.ONE);
        Assertions.assertEquals(new BigDecimal("2.2046"), kilogram.toPounds().value);
    }

    @Test()
    void poundToKilo(){
        final Pound pound = new Pound(BigDecimal.ONE);
        Assertions.assertEquals(new BigDecimal("0.4536"), pound.toKilograms().value);
    }

    @Test
    void convertZeroKiloValue(){
        final Pound pound = new Kilogram(BigDecimal.ZERO).toPounds();
        Assertions.assertEquals(BigDecimal.ZERO.setScale(4), pound.value);
    }

    @Test
    void convertZeroPoundValue(){
        final Kilogram kilogram = new Pound(BigDecimal.ZERO).toKilograms();
        Assertions.assertEquals(BigDecimal.ZERO.setScale(4), kilogram.value);
    }

    @Test
    void convertUnitInstantly(){
        Assertions.assertTimeout(Duration.ofMillis(5), () -> {
            new Kilogram(BigDecimal.TEN).toPounds();
        });
    }

    @Test
    void createNull(){
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> new Kilogram(null)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> new Pound(null))
        );
    }
}
