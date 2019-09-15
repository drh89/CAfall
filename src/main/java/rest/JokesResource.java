/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.JokesDTO;
import entities.Jokes;
import facades.JokesFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Dennis
 */
@Path("jokes")
public class JokesResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CAfall",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final JokesFacade FACADE =  JokesFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    /**
     * Creates a new instance of JokesResource
     */
    public JokesResource() {
    }

   
    @GET
    @Path("/all")
    public Response getAllJokes(){
        List<Jokes> jokes = FACADE.getAllJokes();
        List<JokesDTO> jokesDTO = new ArrayList();
        jokes.forEach((joke) -> {
            jokesDTO.add(new JokesDTO(joke));
        });
        return Response.ok().entity(GSON.toJson(jokesDTO)).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getJokeById(@PathParam("id") int id){
        Jokes joke = FACADE.getJokeById(id);
        JokesDTO jokeDTO = new JokesDTO(joke);
        return Response.ok().entity(GSON.toJson(jokeDTO)).build();
    }
    
    @GET
    @Path("/random")
    public Response getRandomJoke(){
        Jokes joke = FACADE.getRandomJoke();
        JokesDTO jokeDTO = new JokesDTO(joke);
        return Response.ok().entity(GSON.toJson(jokeDTO)).build();
    }
    
//    @GET
//    @Path("/populate")
//    public String populate(){
//        Jokes joke1 = new Jokes(1L,"Annoying Dad", "A boy to his dad: Dad I am hungry. The Dad: I am dad. The boy: Dad I am serious. The dad: Are you Serious, I thought you were Hungry?. The Boy: Are you serious? The dad: No I am still dad", "Dad", "Dad jokes");
//        Jokes joke2 = new Jokes(1L,"Blind Guy", "A blind guy walked into a bar, and a chair, and a table....", "Mean", "Blind jokes");
//        Jokes joke3 = new Jokes(1L,"Mean Blind Joke", "Why do blind people love bread with poppy seeds? They love the stories on them", "Mean", "Blind jokes");
//        Jokes joke4 = new Jokes(1L,"Weird Knock Knock Joke", "Knock Knock. Who is there? It is Ken Lee. Ken Lee who? Keeeen Leeeee, ken liber diber dout you, Ken Leeeeeeee, Ken liber diber dout you!", "Weird", "Knock knock jokes");
//        Jokes joke5 = new Jokes(1L,"Mean Joke", "Today at the bank, an old lady asked me to help check her balance. So i pushed her over", "Mean", "Old lady jokes");
//        Jokes joke6 = new Jokes(1L,"Uncle Joke ", "My boss told me to have a good day... so i went home", "Weird", "Boss jokes");
//        Jokes joke7 = new Jokes(1L,"Lame Peter Pan", "Why is Peter Pan always flying? He neverlands", "Peter Pan", "Peter Pan jokes");
//        Jokes joke8 = new Jokes(1L,"Crazy Skydiver", "Why do blind people hate skydiving? It scares the shit out of their dogs", "Mean", "Blind jokes");
//      
//        FACADE.addJoke(joke1);
//        FACADE.addJoke(joke2);
//        FACADE.addJoke(joke3);
//        FACADE.addJoke(joke4);
//        FACADE.addJoke(joke5);
//        FACADE.addJoke(joke6);
//        FACADE.addJoke(joke7);
//        FACADE.addJoke(joke8);
//        return "Success!!";
//    }
}
