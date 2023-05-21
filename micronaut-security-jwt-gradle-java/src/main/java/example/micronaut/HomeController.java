package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;
import java.util.Map;
import java.util.Random;
import java.util.Optional;

@Secured(SecurityRule.IS_AUTHENTICATED) // <1>
@Controller  // <2>
public class HomeController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get // <3>
    public String index(Principal principal, @QueryValue("sleep")Optional<Integer> sleep) {  // <4>
        if( sleep.isPresent()){
            try{
                Thread.sleep(sleep.get()*1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return principal.getName();

    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/user-details") // <3>
    public HttpResponse<String> userDetails(Principal principal) {  // <4>
        return
                HttpResponse
                        .ok(principal.getName())
                        .headers(
                                Map.of(
                                        "x-user-id", new Random().nextInt()+"",
                                        "x-user-name", principal.getName(),
                                        "x-random", new Random().nextInt()+""
                                )
                        );
    }


}
