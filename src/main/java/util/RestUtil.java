package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.UserInfo;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import java.lang.reflect.Type;
import java.util.List;

public class RestUtil {
    
    public static ValidatableResponse sendDelete(String url){
        ValidatableResponse response = RestAssured.delete(url).then();
        return response;
    }
    
    public static ValidatableResponse sendGet(String url){
        ValidatableResponse response = RestAssured.get(url).then();
        return response;
    }

    public static UserInfo parseToUserInfo(String jsonAsString){
        Gson gson = new Gson();
        return gson.fromJson(jsonAsString, UserInfo.class);
    }

    public static List<UserInfo> parseJsonToUserInfoList(String jsonAsString){
        Gson gson = new Gson();

        Type listType = new TypeToken<List<UserInfo>>() {}.getType();
        List<UserInfo> yourList = new Gson().fromJson(jsonAsString, listType);
        return yourList;
    }
}
