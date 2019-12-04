package com.companic.view;

import com.companic.model.entity.Car;
import com.companic.model.entity.PassengerCar;
import com.companic.model.entity.Truck;
import com.companic.util.ResourceManager;

import java.util.List;

public class View {

    private ResourceManager resourceManager;

    public View() {
        this.resourceManager = ResourceManager.INSTANCE;
    }

    public void printAllCars(List<Car> cars) {
        for (Car car : cars) {
            printCar(car);
        }
    }

    public void printTotalValue(int value) {
        print(getFromResources(LocaleConstants.TOTAL_VALUE));
        print("" + value);
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void printWelcome() {
        print(getFromResources(LocaleConstants.WELCOME));
    }

    public void printSortedByFuelConsumptionAsc(List<Car> cars) {
        print(getFromResources(LocaleConstants.SORT_BY_FUEL_CONSUMPTION_ASC));
        printAllCars(cars);
    }

    public void printCarsWithinSpeedRange(int min, int max, List<Car> cars) {
        String message = getFromResources(LocaleConstants.WITHIN_MAX_SPEED_RANGE);
        System.out.printf(message, min, max);
        printAllCars(cars);
    }

    public void printCar(Car car) {
        print(generateInfoOutputFor(car));
    }

    private String generateInfoOutputFor(Car car) {
        StringBuilder builder = new StringBuilder(200);

        String key_value_divider = getFromResources(LocaleConstants.KEY_VALUE_DIVIDER);
        String divider = getFromResources(LocaleConstants.DIVIDER);

        boolean isPassenger = appendCorrectCarType(car, builder);

        builder.append(" [ ").append(getFromResources(LocaleConstants.LICENSE_PLATE)).append(key_value_divider)
                .append(car.getLicensePlate()).append(divider).append(getFromResources(LocaleConstants.VENDOR))
                .append(key_value_divider).append(car.getVendor()).append(divider).append(getFromResources(LocaleConstants.MODEL))
                .append(key_value_divider).append(car.getModel()).append(divider);

        if (isPassenger) {
            PassengerCar passengerCar = (PassengerCar) car;
            builder.append(getFromResources(LocaleConstants.BODY)).append(key_value_divider).append(passengerCar.getBody())
                    .append(divider).append(getFromResources(LocaleConstants.PASSENGERS_AMOUNT)).append(key_value_divider)
                    .append(passengerCar.getPassengersAmount()).append(divider);
        } else{
            Truck truck = (Truck) car;
            builder.append(getFromResources(LocaleConstants.DUTY)).append(key_value_divider).append(truck.getDuty()).append(divider)
                    .append(getFromResources(LocaleConstants.PAYLOAD)).append(key_value_divider).append(truck.getPayload())
                    .append(divider);
        }

        builder.append(getFromResources(LocaleConstants.YEAR)).append(key_value_divider).append(car.getYear()).append(divider)
                .append(getFromResources(LocaleConstants.COLOR)).append(key_value_divider).append(car.getColor()).append(divider)
                .append(getFromResources(LocaleConstants.MAX_SPEED)).append(key_value_divider).append(car.getMaxSpeed())
                .append(divider).append(getFromResources(LocaleConstants.FUEL)).append(key_value_divider)
                .append(car.getFuelConsumption()).append(divider).append(getFromResources(LocaleConstants.VALUE))
                .append(key_value_divider).append(car.getValue()).append(" ]");

        return builder.toString();
    }

    private boolean appendCorrectCarType(Car car, StringBuilder builder) {
        boolean isPassenger = car instanceof PassengerCar;

        if (isPassenger) {
            builder.append(getFromResources(LocaleConstants.PASSENGER_CAR));
        } else{
            builder.append(getFromResources(LocaleConstants.TRUCK));
        }
        return isPassenger;
    }

    private String getFromResources(String prop) {
        return resourceManager.getString(prop);
    }
}
