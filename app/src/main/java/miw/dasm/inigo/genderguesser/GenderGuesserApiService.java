package miw.dasm.inigo.genderguesser;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface GenderGuesserApiService {

    @Headers({
            "X-Mashape-Key : WmVAvvVROsmsh7ogM7Z0nxfVRcS1p1e5J2DjsnNDQbqKNV6hV7",
            "Accept: text/plain"
    })
    @GET("/")
    Call<FirstName> getFirstname(@Query("name") String name);

}
