package br.com.ffrantz.factory;

public class App {
    public static void main(String[] args) {
        Customer customer = new Customer("A", true);
        Factory factory = getFactory(customer);
        Car car = factory.create(customer.getGradeRequest());
        car.startEngine();
    }

    private static Factory getFactory(Customer customer) {
        if (customer.HasCompanyContract()) {
            return new ContratosFactory();
        }
        else {
            return new SemContratosFactory();
        }
    }
}
