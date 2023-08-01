package com.bestbuy.TestSuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI= "http://localhost";
        RestAssured.port=3030;
        response= given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
   //1. Extract the limit
    @Test
    public void test001(){
int limit =response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Limit : " +limit);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the total
    @Test
    public void test002(){
     int total=response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Tota : " +total);
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the name of 5th store
    @Test
    public void test003(){
String name=response.extract().path("data[4].name");
        System.out.println(name);
    }
    //4. Extract the names of all the store
    @Test
    public void test004(){
        List<String> name= response.extract().path("data.name");
        System.out.println(name);
    }
    //5. Extract the storeId of all the store
    @Test
    public void test005(){
List<Integer> StoreId =response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the storeId of all the store: " +StoreId);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Print the size of the data list
    @Test
    public void test006(){
int datasize= response.extract().path("data.size");
        System.out.println(datasize);
    }
    //7. Get all the value of the store where store data[*].name = St Cloud
    @Test
    public void test007(){
    // response.extract().path("data.findAll{it.name="St Cloud"},value);
    }
    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008(){
      response.extract().path("data.findAll{it.name=('Rochester')}.address");
    }
    //9. Get all the services of 8th store
    @Test
    public void test009(){
     List<String> services=    response.extract().path("data[7].services");
        System.out.println("Alll the services of the 8th Store :"+services);
    }
    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<HashMap<String,?>> storeServicesWindowsStore = response.extract().path("data.services[*].findAll{it.name == 'Windows Store'}.storeservices");

    }
    //11. Get all the storeId of all the store
    @Test
    public void test0011(){
List<Integer> sId=response.extract().path("data.services.storeservices.storeId");
        System.out.println("All storeId of all the stores"+sId);
    }
    //12. Get id of all the store
    @Test
            public void test0012(){
    List<Integer> Id = response.extract().path("data.id");
        System.out.println("ID of all stores :"+Id);
    }

    //13. Find the store names Where state = ND
    @Test
    public void test0013(){
    List<String> name = response.extract().path("data.findAll{it.state equalTo('ND')}.name") ;
        System.out.println(name);
    }
    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test0014(){
      response.extract().path("data.findAll{it.name.equalTo('Rochester')}.services") ;

    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test0015(){
        response.extract().path("data.findAll{it.name=='Windows Store'}.createdAt");
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test0016() {
response.extract().path("data.findAll{it.name =='Fargo'}.services.name");
    }

        //17. Find the zip of all the store
    @Test
    public void test0017() {
     List<Long> zipOfStore=  response.extract().path("data.zip");
        System.out.println("Zip of all stores  :"+zipOfStore);
    }
    //18. Find the zip of store name = Roseville
@Test
public void test0018() {
        response.extract().path("data.findAll{it.name=='Roseville'}.zip");
}

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test0019(){
     List<Object> StoreService=  response.extract().path("data.services.findAll{it.name==('Magnolia Home Theater')}.storeservices");

        System.out.println("Storeservice Details of services name=Magnolia Home Theater :" +StoreService);
    }
    //20. Find the lat of all the stores
    @Test
    public void test0020(){
        List<Double> Lat = response.extract().path("data.lat");
        System.out.println("LAt for all Stores"+Lat);
    }
}

//List<String> productName = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
//@Test
//    public void test20() {
//        response.body("data.find{it.id==346575}.categories.size", equalTo(5));
//    }