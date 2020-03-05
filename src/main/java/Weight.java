import java.math.BigDecimal;

public class Weight implements UnitResolution {
    private BigDecimal value;
    private WeightUnit unit;

    public Weight(WeightUnit unit, BigDecimal value){
        if(value.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Weight cannot be negative.");
        }
        this.unit = unit;
        this.value = value.setScale(UnitResolution.SCALE, UnitResolution.ROUNDING_MODE);
    }

    Weight convert(WeightUnit convertTo){
        BigDecimal value = BigDecimal.ONE;
        if(convertTo.equals(WeightUnit.POUND)){
            value = this.value.divide(Pound.POUND_TO_KILOGRAM_RATIO, UnitResolution.SCALE, UnitResolution.ROUNDING_MODE);
        }

        if(convertTo.equals(WeightUnit.KILOGRAM)){
            value = this.value.multiply(Pound.POUND_TO_KILOGRAM_RATIO).setScale(UnitResolution.SCALE, UnitResolution.ROUNDING_MODE);
        }

        return new Weight(convertTo, value);
    }

    BigDecimal getValue(){
         return value;
    }

    WeightUnit getUnit(){
        return unit;
    }
}
