package br.com.ffrantz.factory;

public class ContratosFactory extends Factory {
    @Override
    Car retrieveCar(String requestedGrade) {
        if ("A".equals(requestedGrade)) {
            return new Tesla(100, "gasoline","blue");
        } else
            return new Audi(100, "ethanol", "white");
    }
}
