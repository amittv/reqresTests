package APIActions;

import RequestPOJO.CreateUpdateUserRequestBody;
import RequestPOJO.RegisterRequestBody;
import ResponsePOJO.*;
import io.restassured.response.Response;
import utils.PropertyUtils;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class APIReq {
    PropertyUtils propertyUtils = new PropertyUtils();

    public String getBaseUrl(){
        propertyUtils.loadPropertyFile();
        return propertyUtils.getValue("BASE_URL");
    }

    public ListUsersResponseBody listUsersClient() {

        Response response = given().contentType("application/json").get(getBaseUrl() + "/api/users?page=1");

        ListUsersResponseBody listUsersResponseBody = response.as(ListUsersResponseBody.class);
        listUsersResponseBody.setStatusCode(response.getStatusCode());
        return listUsersResponseBody;
    }

    public SingleUserResponseBody getSingleUserData(int id) {

        Response response = given().contentType("application/json").get(getBaseUrl() + "/api/users/" + id);
        SingleUserResponseBody singleUserResponseBody = response.as(SingleUserResponseBody.class);
        singleUserResponseBody.setStatusCode(response.getStatusCode());

        return singleUserResponseBody;
    }

    public ListResourcesResponseBody listResourcesClient() {

        Response response = given().contentType("application/json").get(getBaseUrl() + "/api/unknown");

        ListResourcesResponseBody listUsersResponseBody = response.as(ListResourcesResponseBody.class);
        listUsersResponseBody.setStatusCode(response.getStatusCode());
        return listUsersResponseBody;
    }

    public SingleResourceResponseBody getSingleResourceBody(int resourceID) {

        Response response = given().contentType("application/json").get(getBaseUrl() + "/api/unknown/" + resourceID);

        SingleResourceResponseBody singleResourceResponseBody = response.as(SingleResourceResponseBody.class);
        singleResourceResponseBody.setStatusCode(response.getStatusCode());
        return singleResourceResponseBody;
    }

    public CreateUserResponseBody createUser(CreateUpdateUserRequestBody body) {
        Response response = given().contentType("application/json").body(body).post(getBaseUrl() + "/api/users");

        PropertyUtils propertyUtils = new PropertyUtils();
        propertyUtils.loadPropertyFile();
//        propertyUtils.setPropertyValue();

        CreateUserResponseBody createUserResponseBody = response.as(CreateUserResponseBody.class);
        createUserResponseBody.setStatusCode(response.statusCode());
        return createUserResponseBody;

    }

    public UpdatePatchUserResponseBody updatePatchUser(CreateUpdateUserRequestBody body,int userId, String apiAction) {
        Response response = null;
        if(apiAction.equals("UPDATE")){
            response = given().contentType("application/json").body(body).put(getBaseUrl() + "/api/users/" + userId);
        }
        else if(apiAction.equals("PATCH")){
            response = given().contentType("application/json").body(body).patch(getBaseUrl() + "/api/users/" + userId);
        }

        UpdatePatchUserResponseBody updatePatchUserResponseBody = response.as(UpdatePatchUserResponseBody.class);
        updatePatchUserResponseBody.setStatusCode(response.statusCode());
        return updatePatchUserResponseBody;
    }

    public Response deleteUserAPI(int userId) {
        return given().contentType("application/json").delete(getBaseUrl() + "/api/users/" + userId);
    }

    public RegisterResponseBody registerAPI(RegisterRequestBody body){
        Response response = given().contentType("application/json").body(body).post(getBaseUrl() + "/api/register");

        RegisterResponseBody registerResponseBody = response.as(RegisterResponseBody.class);
        registerResponseBody.setStatusCode(response.statusCode());
        return registerResponseBody;
    }

    public Response logInAPI(RegisterRequestBody body){
        Response response = given().contentType("application/json").body(body).post(getBaseUrl() + "/api/login");
        return response;
    }

    public String getRandomString(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 8;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
