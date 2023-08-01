package com.bestbuy.TestSuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void test0021() {
        int limit = response.extract().path("limit");
        System.out.println("Limit :" + limit);
    }

    //22. Extract the total
    @Test
    public void test0022() {
        int Total = response.extract().path("total");
        System.out.println("Total :" + Total);
    }

    //23. Extract the name of 5th product
    @Test
    public void test0023() {
        String name = response.extract().path("data[4].name");
        System.out.println("the name of 5th product" + name);
    }

    //24. Extract the names of all the products
    @Test
    public void test0024() {
        List<String> AllProductName = response.extract().path("data.name");
        System.out.println("All product name :" + AllProductName);
    }

    //25. Extract the productId of all the products
    @Test
    public void test0025() {
        List<Integer> ID = response.extract().path("data.id");
        System.out.println("the productId of all the products" + ID);
    }

    //26. Print the size of the data list
    @Test
    public void test0026() {
        int size = response.extract().path("data.size");
        System.out.println("the size of the data list" + size);
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
    //Pack)
    @Test
    public void test0027() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4=//Pack)'}");
        System.out.println("AllThe value of the poduct where product name = Energizer - MAX Batteries AA (4-\n" +
                "    //Pack)  :"+values);
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
    //Pack)
    @Test
    public void test0028() {
        String name = response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-//Pack'}.model");
        System.out.println("Name :" + name);
    }


    //29. Get all the categories of 8th products
    @Test
    public void test0029() {
        List<String> CategorieName = response.extract().path("data[7].categories");

    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test0030() {
        response.extract().path("data.findAll{it.id='150115'}.categories");
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test0031() {
      List<String> AllDescription =  response.extract().path("data.description");
        System.out.println("All description   :"+AllDescription);
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test0032() {
        List<Integer> id =response.extract().path("data.categories.id");
        System.out.println("id of all the all categories of all the products  :"+id);
    }

    // 33. Find the product names Where type = HardGood
    @Test
    public void test0033() {
       List<String> name = response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println("the product names Where type = HardGood  :" + name);
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA
    //1.5V CopperTop Batteries (4-Pack)

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test0035() {
        String CreatedAt = response.extract().path("data.{it.price<'5.49'}.createdAt");
        System.out.println(CreatedAt);
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
    //Pack)”
    //37. Find the manufacturer of all the products
    @Test
    public void test0037() {
        response.extract().path("data.manufacturer");
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test0038() {
        response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test0039() {
        response.extract().path("data.findAll{it.price>'5.99'}.categories.createdAt");
    }

    //40. Find the uri of all the products
    @Test
    public void test0040() {

    }
}