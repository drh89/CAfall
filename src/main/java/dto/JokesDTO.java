/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Jokes;

/**
 *
 * @author Dennis
 */
public class JokesDTO {
    
    private Long id;
    private String title;
    private String joke;
    
    private String type;
    
    public JokesDTO(Jokes joke){
        this.id = joke.getId();
        this.title = joke.getTitle();
        this.joke = joke.getJoke();
        this.type = joke.getType();
        
    }

    public String getJoke() {
        return joke;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
