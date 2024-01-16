package br.com.ffrantz.factory;

public class SemContratosFactory extends Factory {
    @Override
    Car retrieveCar(String requestedGrade) {
        if ("A".equals(requestedGrade)) {
            return new Toyota(100, "gasoline","blue");
        } else
            return new Volkswagen(100, "ethanol", "white");
    }
}
