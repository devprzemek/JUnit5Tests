import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WeightConverterTest {

    /**
     * Test after refactoring.
     */
    @Test
    void createWeightKilogram(){
        final Weight weightKilogram = new Weight(WeightUnit.KILOGRAM, BigDecimal.TEN);
        Assertions.assertEquals(WeightUnit.KILOGRAM, weightKilogram.getUnit());
        Assertions.assertEquals(BigDecimal.TEN.setScale(4, RoundingMode.CEILING), weightKilogram.getValue());

    }

    @Test
    void createNegativeKilogram(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Weight(WeightUnit.KILOGRAM, new BigDecimal(-10));
        });
    }

    @Test
    void createWeightPound(){
        final Weight weightPound = new Weight(WeightUnit.POUND, new BigDecimal(10));
        Assertions.assertEquals(WeightUnit.POUND, weightPound.getUnit());
        Assertions.assertEquals(BigDecimal.TEN.setScale(4, RoundingMode.CEILING), weightPound.getValue());
    }
    @Test
    void createNegativePound(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Weight(WeightUnit.POUND, new BigDecimal(-10));
        });
    }

    @Test
    void createWeightWithNull(){
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class,  () -> new Weight(WeightUnit.KILOGRAM, null)),
                () -> Assertions.assertThrows(NullPointerException.class,  () -> new Weight(WeightUnit.POUND, null))
        );
    }

    @Test()
    void kiloToPound(){
        final Weight weightKilogram = new Weight(WeightUnit.KILOGRAM, BigDecimal.ONE);
        final Weight weightPound = weightKilogram.convert(WeightUnit.POUND);
        Assertions.assertEquals(WeightUnit.POUND, weightPound.getUnit());
        Assertions.assertEquals(new BigDecimal("2.2046"), weightPound.getValue());
    }

    @Test()
    void poundToKilo(){
        final Weight weightPound = new Weight(WeightUnit.POUND, BigDecimal.ONE);
        final Weight weightKilogram = weightPound.convert(WeightUnit.KILOGRAM);
        Assertions.assertEquals(WeightUnit.KILOGRAM, weightKilogram.getUnit());
        Assertions.assertEquals(new BigDecimal("0.4536"), weightKilogram.getValue());
    }
}
