package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarsDTO;
import entities.Cars;
import utils.EMF_Creator;
import facades.CarsFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("cars")
public class CarsResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CAfall",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final CarsFacade FACADE =  CarsFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
//    @Path("count")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getRenameMeCount() {
//        long count = FACADE.getRenameMeCount();
//        //System.out.println("--------------->"+count);
//        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
//    }
    
     @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllCars() {
        List<Cars> cars = FACADE.getCars();
        List<CarsDTO> carsDTO = new ArrayList();
        cars.forEach((car) -> {
            carsDTO.add(new CarsDTO(car));
        });
        return Response.ok().entity(GSON.toJson(carsDTO)).build();
    }
    @Path("/makename/{name}")
    @GET
    public Response getCarsByMake(@PathParam("name")String name){
        List<Cars> cars = FACADE.getCarsByMake(name);
        List<CarsDTO> carsDTO = new ArrayList();
        cars.forEach((car) -> {
            carsDTO.add(new CarsDTO(car));
            
        });
        return Response.ok().entity(GSON.toJson(carsDTO)).build();
    }
    
    @GET
    @Path("/populate")
    public String populate(){
        Cars c1 = new Cars(1L, "Mercedes-benz", "E350", "10/04-2016", 2017L, 268L, 16000L, 5L, 719000L);
        Cars c2 = new Cars(2L, "VW", "Golf", "03/07-2010", 2010L, 115L, 206000L, 5L, 130000L);
        Cars c3 = new Cars(3L, "Mitsubishi", "Lancer", "25/06-1995", 1994L, 116L, 344000L, 4L, 16000L);
        Cars c4 = new Cars(4L, "BMW", "330i", "06/03-2013", 2012L, 178L, 5000L, 4L, 370000L);
        Cars c5 = new Cars(5L, "BMW", "330i", "21/08-2013", 2012L, 178L, 250000L, 5L, 278999L);
        Cars c6 = new Cars(6L, "BMW", "320i", "10/02-2016", 2017L, 130L, 11000L, 4L, 499995L);
        Cars c7 = new Cars(7L, "BMW", "320i", "03/06-2011", 2010L, 122L, 350500L, 4L, 78000L);
        Cars c8 = new Cars(8L, "BMW", "M5", "05/12-2008", 2008L, 420L, 166000L, 5L, 300000L);
        Cars c9 = new Cars(9L, "BMW", "540i", "06/08-2018", 2019L, 333L, 1000L, 4L, 711895L);
        Cars c10 = new Cars(10L, "Mercedes-benz", "A200", "07/08-2008", 2006L, 73L, 410000L, 5L, 40000L);
        Cars c11 = new Cars(11L, "Mercedes-benz", "C270", "06/03-2002", 2000L, 170L, 255000L, 5L, 60000L);
        Cars c12 = new Cars(12L, "Mercedes-benz", "C350", "28/05-2013", 2012L, 253L, 401000L, 4L, 160000L);
        Cars c13 = new Cars(13L, "Mercedes-benz", "E220d", "21/03-2015", 2014L, 170L, 55000L, 5L, 199000L);
        Cars c14 = new Cars(14L, "Mercedes-benz", "E350", "16/09-2010", 2008L, 245L, 365000L, 4L, 80500L);
        Cars c15 = new Cars(15L, "Mercedes-benz", "SL300", "14/06-1983", 1980L, 150L, 116000L, 2L, 400000L);
        Cars c16 = new Cars(16L, "Mercedes-benz", "E63s AMG", "11/09-2019", 2019L, 612L, 0L, 5L, 2479000L);
        Cars c17 = new Cars(17L, "VW", "UP!", "06/03-2014", 2012L, 60L, 150000L, 3L, 55000L);
        Cars c18 = new Cars(18L, "VW", "Passat", "18/06-2001", 2000L, 150L, 300000L, 4L, 178000L);
        Cars c19 = new Cars(19L, "VW", "Passat", "23/11-2010", 2011L, 150L, 10000L, 5L, 300000L);
        Cars c20 = new Cars(20L, "VW", "Golf", "01/05-2013", 2012L, 115L, 120000L, 5L, 150000L);
        Cars c21 = new Cars(21L, "VW", "Sirocco", "06/11-2013", 2012L, 210L, 29000L, 3L, 290000L);
        Cars c22 = new Cars(22L, "VW", "Touran", "14/06-2008", 2008L, 155L, 345000L, 5L, 200000L);
        Cars c23 = new Cars(23L, "VW", "Tiguan", "01/02-2014", 2012L, 190L, 145000L, 5L, 278000L);
        Cars c24 = new Cars(24L, "VW", "Tiguan", "25/12-2013", 2012L, 150L, 300000L, 5L, 210000L);
        Cars c25 = new Cars(25L, "VW", "Touareg", "07/12-2017", 2018L, 368L, 93000L, 5L, 613000L);
        Cars c26 = new Cars(26L, "Skoda", "Citigo", "23/08-2017", 2016L, 60L, 18000L, 5L, 78000L);
        Cars c27 = new Cars(27L, "Skoda", "Citigo", "30/10-2016", 2012L, 75L, 205000L, 3L, 70000L);
        Cars c28 = new Cars(28L, "Skoda", "Fabia", "27/10-1998", 1999L, 70L, 310000L, 5L, 23000L);
        Cars c29 = new Cars(29L, "Skoda", "Fabia", "23/03-2016", 2016L, 90L, 50000L, 5L, 199000L);
        Cars c30 = new Cars(30L, "Skoda", "Octavia", "06/03-2013", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c31 = new Cars(31L, "Skoda", "Octavia", "22/03-2013", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c32 = new Cars(32L, "Skoda", "Octavia", "23/03-2013", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c33 = new Cars(33L, "Skoda", "Octavia", "24/03-2013", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c34 = new Cars(34L, "Skoda", "Superb", "06/08-2013", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c35 = new Cars(35L, "Skoda", "Superb", "06/09-2013", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c36 = new Cars(36L, "Skoda", "Superb", "06/10-2013", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c37 = new Cars(37L, "Skoda", "Kodiaq", "06/03-2013", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c38 = new Cars(38L, "Volvo", "330i", "06/03-2011", 2012L, 178L, 5000L, 4L, 20000L);
        Cars c39 = new Cars(39L, "Volvo", "330i", "06/05-2016", 2012L, 178L, 5000L, 4L, 160000L);
        Cars c40 = new Cars(40L, "Volvo", "330i", "06/03-2017", 2012L, 178L, 5000L, 4L, 360000L);
        Cars c41 = new Cars(41L, "Volvo", "330i", "06/01-2018", 2012L, 178L, 5000L, 4L, 60000L);
        Cars c42 = new Cars(42L, "Volvo", "230i", "06/11-2013", 2012L, 178L, 5000L, 4L, 6000L);
        Cars c43 = new Cars(43L, "Volvo", "130i", "06/02-2019", 2012L, 178L, 5000L, 4L, 160000L);
        Cars c44 = new Cars(44L, "Volvo", "130i", "06/03-2019", 2012L, 178L, 5000L, 4L, 360000L);
        Cars c45 = new Cars(45L, "Volvo", "230i", "06/03-2013", 2012L, 178L, 5000L, 4L, 110000L);
        Cars c46 = new Cars(46L, "Volvo", "230i", "06/03-2013", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c47 = new Cars(47L, "Nissan", "330i", "06/03-2015", 2012L, 178L, 5000L, 4L, 360000L);
        Cars c48 = new Cars(48L, "Nissan", "330i", "06/03-2012", 2012L, 178L, 5000L, 4L, 660000L);
        Cars c49 = new Cars(49L, "Nissan", "330i", "05/03-2011", 2012L, 178L, 5000L, 4L, 260000L);
        Cars c50 = new Cars(50L, "Nissan", "330i", "06/02-2013", 2012L, 178L, 5000L, 4L, 260000L);
        
        
        
        FACADE.addCar(c1);
        FACADE.addCar(c2);
        FACADE.addCar(c3);
        FACADE.addCar(c4);
        FACADE.addCar(c5);
        FACADE.addCar(c6);
        FACADE.addCar(c7);
        FACADE.addCar(c8);
        FACADE.addCar(c9);
        FACADE.addCar(c10);
        FACADE.addCar(c11);
        FACADE.addCar(c12);
        FACADE.addCar(c13);
        FACADE.addCar(c14);
        FACADE.addCar(c15);
        FACADE.addCar(c16);
        FACADE.addCar(c17);
        FACADE.addCar(c18);
        FACADE.addCar(c19);
        FACADE.addCar(c20);
        FACADE.addCar(c21);
        FACADE.addCar(c22);
        FACADE.addCar(c23);
        FACADE.addCar(c24);
        FACADE.addCar(c25);
        FACADE.addCar(c26);
        FACADE.addCar(c27);
        FACADE.addCar(c28);
        FACADE.addCar(c29);
        FACADE.addCar(c30);
        FACADE.addCar(c31);
        FACADE.addCar(c32);
        FACADE.addCar(c33);
        FACADE.addCar(c34);
        FACADE.addCar(c35);
        FACADE.addCar(c36);
        FACADE.addCar(c37);
        FACADE.addCar(c38);
        FACADE.addCar(c39);
        FACADE.addCar(c40);
        FACADE.addCar(c41);
        FACADE.addCar(c42);
        FACADE.addCar(c43);
        FACADE.addCar(c44);
        FACADE.addCar(c45);
        FACADE.addCar(c46);
        FACADE.addCar(c47);
        FACADE.addCar(c48);
        FACADE.addCar(c49);
        FACADE.addCar(c50);
        return "Success!!!";
    }

 
}
