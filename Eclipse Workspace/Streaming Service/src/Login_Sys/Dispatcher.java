/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;
import java.util.HashMap;
import java.util.*;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Dispatcher implements DispatcherInterface {
    HashMap<String, Object> ListOfObjects;
    

    public Dispatcher()
    {
        ListOfObjects = new HashMap<String, Object>();
    }
    
    /* 
    * dispatch: Executes the remote method in the corresponding Object
    * @param request: Request: it is a Json file
    {
        "remoteMethod":"getSongChunk",
        "objectName":"SongServices",
        "param":
          {
              "song":490183,
              "fragment":2
          }
    }
    */
    public String dispatch(String request)
    {
        JSONObject jsonReturn = new JSONObject();
        JSONParser parser = new JSONParser();
       // JSONObject jsonRequest;
      
        
        try {
            JSONObject jsonRequest = (JSONObject) parser.parse(request);
            // Obtains the object pointing to SongServices
            Object object = ListOfObjects.get(jsonRequest.get("objectName").toString());
            Method[] methods = object.getClass().getMethods();
            Method method = null;
            // Obtains the method
            for (int i=0; i<methods.length; i++)
            {   
                if (methods[i].getName().equals(jsonRequest.get("remoteMethod").toString())){
                    method = methods[i];
                }
            }
            if (method == null)
            {
                jsonReturn.put("error", "Method does not exist");
                return jsonReturn.toString();
            }
            // Prepare the  parameters 
            Class[] types =  method.getParameterTypes();
            Object[] parameter = new Object[types.length];
            String[] strParam = new String[types.length];
            JSONObject jsonParam = (JSONObject) parser.parse(jsonRequest.get("param").toString());
            int j = 0;
            for (Map.Entry<String, JSONObject>  entry  : (Set<Map.Entry>) jsonParam.entrySet())
            {
                JSONObject data = new JSONObject();
                data.put("data", entry.getValue());
                System.out.println(data.get("data").toString());

                strParam[j++] = data.get("data").toString();
                System.out.println("passed");
            }
            // Prepare parameters
            for (int i=0; i<types.length; i++)
            {
                switch (types[i].getCanonicalName())
                {
                    case "java.lang.Long":
                        parameter[i] =  Long.parseLong(strParam[i]);
                        break;
                    case "java.lang.Integer":
                        parameter[i] =  Integer.parseInt(strParam[i]);
                        break;
                    case "java.lang.String":
                        
                        parameter[i] = new String(strParam[i]);
                        System.out.println("parameter[i]: " + parameter[i]);
                        break;
                }
            }
            // Prepare the return
            Class returnType = method.getReturnType();
            
            String ret = "";
            switch (returnType.getCanonicalName())
                {
                    case "java.lang.Long":
                        ret = method.invoke(object, parameter).toString();
                        break;
                    case "java.lang.Integer":
                        ret = method.invoke(object, parameter).toString();
                        break;
                    case "java.lang.String":
                        ret = (String)method.invoke(object, parameter);
                        break;
                }
                jsonReturn.put("ret", ret);
   
        } catch (InvocationTargetException | IllegalAccessException e)
        {
        //    System.out.println(e);
           // jsonReturn.addProperty("error", "Error on " + jsonRequest.get("objectName").toString() + "." + jsonRequest.get("remoteMethod").getAsString());
        } catch (ParseException ex) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return jsonReturn.toString();
    }

    /* 
    * registerObject: It register the objects that handle the request
    * @param remoteMethod: It is the name of the method that 
    *  objectName implements. 
    * @objectName: It is the main class that contaions the remote methods
    * each object can contain several remote methods
    */
    public void registerObject(Object remoteMethod, String objectName)
    {
        ListOfObjects.put(objectName, remoteMethod);
    }
    
    //////////////////
    /* Just for Testing */
    //////////////////
    public static void main(String[] args) {
        // Instance of the Dispatcher
        Dispatcher dispatcher = new Dispatcher();
        // Instance of the services that te dispatcher can handle
        SongDispatcher songDispatcher = new SongDispatcher();
        
        dispatcher.registerObject(songDispatcher, "SongDispatcher");  
    
        // Testing  the dispatcher function
        // First we read the request. In the final implementation the jsonRequest
        // is obtained from the communication module
        try {
            
            String jsonRequest = new String(Files.readAllBytes(Paths.get("./playSong.json")));
            System.out.println(jsonRequest);
            String ret = dispatcher.dispatch(jsonRequest);
            JSONParser parser = new JSONParser();
             JSONObject json = (JSONObject) parser.parse(ret);
            String s = json.get("ret").toString();
            System.out.println(ret);
            byte[] b = Base64.getDecoder().decode(s);
            System.out.println(Base64.getEncoder().encodeToString(b));
            
            
            /*JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(ret);
            String s = json.get("ret").get();
            System.out.println(json.get("ret"));

            System.out.println(jsonRequest);
            byte[] decodedBytes = Base64.getDecoder().decode(json.get("ret").toString());
            String decodedString = new String(8decodedBytes);
            System.out.println(decodedString);*/
        } catch (Exception e)
        {
            System.out.println(e);
        }
        
    } 
}