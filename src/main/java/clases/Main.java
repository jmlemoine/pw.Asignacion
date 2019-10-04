package clases;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class Main {

    public static String renderFreemarker(Map<String, Object> model, String templatePath) {
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/resources/templates/");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
        return freeMarkerEngine.render(new ModelAndView(model, templatePath));
    }

    public static void main(String[] args){

        /*Configuration configuration=new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/resources/templates/");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
*/

        get("/hello", (req, res) -> "Hello World");

        //get("/formulario/", Main::handle);



        /*before("/",(request, response) -> {
            Usuario usuario=request.session(true).attribute("usuario");
            if(usuario==null){
                //parada del request, enviando un codigo.

                    response.redirect("/login/");

                ;
            }
        });*/
        post("/procesarFormulario/", (request, response) -> {
            //obteniendo la matricula.

            String usuario =request.queryParams("usuario");
            String clave =request.queryParams("clave");

            Usuario user= new Usuario(usuario, clave);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("usuario", usuario);
            return renderFreemarker(attributes, "formulario.ftl");
            //enviando los parametros a la vista.

        }); //

        get("/login/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Formulario en FreeMarker");
            return renderFreemarker(attributes, "login.ftl");
        });
    }


}
