package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.request.booking.BookingReq;

public class APIUtils {

    public static Response sendPostRequest(RequestSpecification request, String endpoint, Object payload){
        return request
                .contentType("application/json")
                .body(payload)
                .when()
                .accept("application/json")
                .log().all()
                .post(endpoint);
    }

    public static Response sendGetRequest(RequestSpecification request, String endpoint){
        return request
                .when()
                .log().all()
                .get(endpoint);
    }


    public static Response sendPutRequest(RequestSpecification request, String endpoint, BookingReq payload) {
        return request
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(payload)
                .when()
                .log().all()
                .put(endpoint);
    }

    public static Response sendDeleteRequest(RequestSpecification request, String endpoint) {
        return request
                .when()
                .log().all()
                .delete(endpoint);
    }
}
