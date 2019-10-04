

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;

public class Main {

    public static void main(String[] args){

        /*get("/login/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Formulario en FreeMarker");
            return renderFreemarker(attributes, "Login.ftl");
        });*/


                get("/hello", (req, res) -> "Hello World");


        before("/",(request, response) -> {
            Usuario usuario=request.session(true).attribute("usuario");
            if(usuario==null){
                //parada del request, enviando un codigo.

                    response.redirect("/mal");

                ;
            }
        });

    }

}
