package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Cars.deleteAllRows", query = "DELETE from Cars"),
    @NamedQuery(name = "cars.getAll", query = "SELECT c FROM Cars c"),
    @NamedQuery(name = "cars.make", query = "SELECT c FROM Cars c WHERE c.make LIKE :name")
})
public class Cars implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String registrationDate;
    private Long modelYear;
    private Long horsepower;
    private Long mileage;
    private Long doors;
    private Long price;

    public Cars() {
    }

    public Cars(Long id, String make, String model, String registrationDate, Long modelYear, Long horsepower, Long mileage, Long doors, Long price) {
        this.id = id;
        this.model = model;
        this.make = make;
        this.registrationDate = registrationDate;
        this.modelYear = modelYear;
        this.horsepower = horsepower;
        this.mileage = mileage;
        this.doors = doors;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getModelYear() {
        return modelYear;
    }

    public void setModelYear(Long modelYear) {
        this.modelYear = modelYear;
    }

    public Long getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Long horsepower) {
        this.horsepower = horsepower;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Long getDoors() {
        return doors;
    }

    public void setDoors(Long doors) {
        this.doors = doors;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
    // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo
//    private String dummyStr1;
//    private String dummyStr2;
//
//    public Cars(String dummyStr1, String dummyStr2) {
//        this.dummyStr1 = dummyStr1;
//        this.dummyStr2 = dummyStr2;
//    }
//
//    public String getDummyStr1() {
//        return dummyStr1;
//    }
//
//    public void setDummyStr1(String dummyStr1) {
//        this.dummyStr1 = dummyStr1;
//    }
//
//    public String getDummyStr2() {
//        return dummyStr2;
//    }
//
//    public void setDummyStr2(String dummyStr2) {
//        this.dummyStr2 = dummyStr2;
//    }
}
