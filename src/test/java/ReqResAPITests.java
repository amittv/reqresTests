import APIActions.APIReq;
import RequestPOJO.CreateUpdateUserRequestBody;
import RequestPOJO.RegisterRequestBody;
import ResponsePOJO.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyUtils;


public class ReqResAPITests {

    PropertyUtils propertyUtils = new PropertyUtils();
    APIReq apiReq = new APIReq();

    @Test
    public void listUsersAPI() {
        ListUsersResponseBody listUsersResponseBody = apiReq.listUsersClient();
        Assert.assertNotNull(listUsersResponseBody.getData());
        Assert.assertNotNull(listUsersResponseBody.getSupport());
        Assert.assertEquals(listUsersResponseBody.getStatusCode(), 200);
    }

    @Test
    public void singleUserGet() {
        SingleUserResponseBody singleUserResponseBody = apiReq.getSingleUserData(2);
        Assert.assertNotNull(singleUserResponseBody.getData());
        Assert.assertEquals(singleUserResponseBody.getData().getId(), 2);
        Assert.assertNotNull(singleUserResponseBody.getSupport());
        Assert.assertEquals(singleUserResponseBody.getStatusCode(), 200);
    }

    @Test
    public void singleUserNotFound() {
        SingleUserResponseBody singleUserResponseBody = apiReq.getSingleUserData(23);
        Assert.assertEquals(singleUserResponseBody.getStatusCode(), 404);
    }

    @Test
    public void listResourceAPI() {
        ListResourcesResponseBody listResourcesResponseBody = apiReq.listResourcesClient();
        Assert.assertNotNull(listResourcesResponseBody.getData());
        Assert.assertNotNull(listResourcesResponseBody.getData().get(0));
        Assert.assertNotNull(listResourcesResponseBody.getSupport());
        Assert.assertEquals(listResourcesResponseBody.getStatusCode(), 200);
    }

    @Test
    public void singleResourceAPI() {
        SingleResourceResponseBody singleResourceResponseBody = apiReq.getSingleResourceBody(2);

        Assert.assertNotNull(singleResourceResponseBody.getData());
        Assert.assertEquals(singleResourceResponseBody.getData().getId(), 2);
        Assert.assertNotNull(singleResourceResponseBody.getSupport());
        Assert.assertEquals(singleResourceResponseBody.getStatusCode(), 200);
    }

    @Test
    public void singleResourceNotFoundAPI() {
        SingleResourceResponseBody singleResourceResponseBody = apiReq.getSingleResourceBody(23);
        Assert.assertEquals(singleResourceResponseBody.getStatusCode(), 404);
    }

    @Test
    public void createUserAPI(){
        propertyUtils.loadPropertyFile();
        String name = apiReq.getRandomString(), job = apiReq.getRandomString();
        CreateUpdateUserRequestBody createUpdateUserRequestBody = CreateUpdateUserRequestBody.builder()
                .name(name).job(job).build();

        CreateUserResponseBody createUserResponseBody = apiReq.createUser(createUpdateUserRequestBody);
        propertyUtils.setPropertyValue("CREATE_USER_ID", createUserResponseBody.getId());

        Assert.assertNotNull(createUserResponseBody);
        Assert.assertEquals(createUserResponseBody.getName(), name);
        Assert.assertEquals(createUserResponseBody.getJob(), job);
        Assert.assertEquals(createUserResponseBody.getStatusCode(), 201);
    }

    @Test
    public void updateUserAPI(){
        propertyUtils.loadPropertyFile();
        String name = apiReq.getRandomString(), job = apiReq.getRandomString();

        CreateUpdateUserRequestBody createUpdateUserRequestBody = CreateUpdateUserRequestBody.builder()
                .name(name).job(job).build();

        UpdatePatchUserResponseBody updatePatchUserResponseBody = apiReq.updatePatchUser(createUpdateUserRequestBody,
                Integer.parseInt(propertyUtils.getValue("CREATE_USER_ID")), "UPDATE");
        Assert.assertNotNull(updatePatchUserResponseBody);
        Assert.assertEquals(updatePatchUserResponseBody.getName(), name);
        Assert.assertEquals(updatePatchUserResponseBody.getJob(), job);
        Assert.assertNotNull(updatePatchUserResponseBody.getUpdatedAt());
        Assert.assertEquals(updatePatchUserResponseBody.getStatusCode(), 200);
    }

    @Test
    public void patchUserAPI(){
        propertyUtils.loadPropertyFile();
        String name = "morpheus", job = "leader";
        CreateUpdateUserRequestBody createUpdateUserRequestBody = CreateUpdateUserRequestBody.builder()
                .name(name).job(job).build();

        UpdatePatchUserResponseBody updatePatchUserResponseBody = apiReq.updatePatchUser(createUpdateUserRequestBody,
                Integer.parseInt(propertyUtils.getValue("CREATE_USER_ID")), "PATCH");
        Assert.assertNotNull(updatePatchUserResponseBody);
        Assert.assertEquals(updatePatchUserResponseBody.getName(), name);
        Assert.assertEquals(updatePatchUserResponseBody.getJob(), job);
        Assert.assertNotNull(updatePatchUserResponseBody.getUpdatedAt());
        Assert.assertEquals(updatePatchUserResponseBody.getStatusCode(), 200);
    }

    @Test
    public void deleteUserTest(){
        propertyUtils.loadPropertyFile();
        Response res = apiReq.deleteUserAPI(Integer.parseInt(propertyUtils.getValue("CREATE_USER_ID")));
        Assert.assertEquals(res.statusCode(), 204);
    }

    @Test
    public void registerAPITest(){
        propertyUtils.loadPropertyFile();
        String email = "eve.holt@reqres.in", password = "pistol";
        RegisterRequestBody registerRequestBody = RegisterRequestBody.builder()
                .email(email).password(password).build();

        RegisterResponseBody registerResponseBody = apiReq.registerAPI(registerRequestBody);

        Assert.assertNotNull(registerResponseBody.getToken());
        Assert.assertEquals(registerResponseBody.getId(), 4);
    }

    @Test
    public void registerUnsuccessfulAPITest(){
        String email = "eve.holt@reqres.in";
        RegisterRequestBody registerRequestBody = RegisterRequestBody.builder().email(email).build();
        RegisterResponseBody registerResponseBody = apiReq.registerAPI(registerRequestBody);
        Assert.assertEquals(registerResponseBody.getStatusCode(), 400);
    }

    @Test
    public void logInAPITest(){
        String email = "eve.holt@reqres.in", password = "cityslicka";
        RegisterRequestBody registerRequestBody = RegisterRequestBody.builder().email(email).password(password).build();
        Response response = apiReq.logInAPI(registerRequestBody);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.body().jsonPath().get("token"));
    }

    @Test
    public void logInFailureAPITest(){
        String email = "eve.holt@reqres.in";
        RegisterRequestBody registerRequestBody = RegisterRequestBody.builder().email(email).build();
        Response response = apiReq.logInAPI(registerRequestBody);
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("error"), "Missing password");
    }

    @Test
    public void logInFailureEmailAPITest(){
        String password = "citysli";
        RegisterRequestBody registerRequestBody = RegisterRequestBody.builder().password(password).build();
        Response response = apiReq.logInAPI(registerRequestBody);
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("error"), "Missing email or username");
    }

    @Test
    public void logInFailureUserNotFoundAPITest(){
        String password = "citysli";
        String email = "eveerd@reqres.in";
        RegisterRequestBody registerRequestBody = RegisterRequestBody.builder().email(email).password(password).build();
        Response response = apiReq.logInAPI(registerRequestBody);
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("error"), "user not found");
    }
}
