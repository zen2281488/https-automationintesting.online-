package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static utils.properties.ConfProperties.getCommonProperty;

public class ApiUtils {
    public static List<Object> getBookingid(String token) {
        Response response = getRequestSpecification(token).get(getCommonProperty("BASE_API_URL") + "/booking").then().log().all().extract().response();

        return response.jsonPath().getList("bookings.bookingid");
    }

    public static RequestSpecification getRequestSpecification(String token) {
        return RestAssured.given().cookie("token", token);
    }

    public static void deleteAllBooking(List<Object> bookingIds, String token) {
        for (Object bookingId : bookingIds) {
            getRequestSpecification(token).delete(getCommonProperty("BASE_API_URL") + "/booking/" + bookingId).then().extract().response();
            System.out.println("Deleting booking with ID: " + bookingId);
        }
    }

    public static String getBookingDate(String token) {
        Response response = getRequestSpecification(token).get(getCommonProperty("BASE_API_URL") + "/booking").then().log().all().extract().response();
        return response.jsonPath().getString("bookings.bookingdates.checkin") + " - " + response.jsonPath().getList("bookings.bookingdates.checkout");
    }

    public static String getBookingDates(String token) {

        Response response = getRequestSpecification(token).get(getCommonProperty("BASE_API_URL") + "/booking").then().log().all().extract().response();


        List<Map<String, Object>> data = response.jsonPath().getList("bookings");

        StringBuilder result = new StringBuilder();
        for (Map<String, Object> booking : data) {
            String name = (String) booking.get("firstname");
            if (getCommonProperty("testFirstName").equals(name)) {
                Map<String, String> bookingDates = (Map<String, String>) booking.get("bookingdates");
                String checkin = bookingDates.get("checkin");
                String checkout = bookingDates.get("checkout");
                result.append(checkin).append(" - ").append(checkout).append("\n");
            }
        }
        return result.toString().replaceAll("\\s+$", "");
    }

}