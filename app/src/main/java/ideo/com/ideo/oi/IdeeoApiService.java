package ideo.com.ideo.oi;

import ideo.com.ideo.oi.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by USUARIO on 21/07/2018.
 */

public interface IdeeoApiService {

    @GET(ApiConstants.URI_LOGIN)
    Call<LoginResponse> getLogin(
            @Query(value = ApiConstants.PARAM_LOGIN_USERNAME) String user,
            @Query(value = ApiConstants.PARAM_LOGIN_PASSWORD) String pass);

}
