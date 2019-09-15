
var div = document.getElementById("div");
;
var jokesInput = document.getElementById("jokesInput");
var btnJokes = document.getElementById("btnJokes");

var btnRandJoke = document.getElementById("btnRandJoke");

let dom = "http://localhost:8080/CAfall/api/jokes";


onload = getAllJokes();
btnJokes.addEventListener("click", getJokeById);
btnRandJoke.addEventListener("click", getRandomJoke);



function getRandomJoke(){
    event.preventDefault();
    var conf ={method: "get"};
    var url = dom + "/random";
    var promise = fetch(url, conf);
    
    promise.then(res => res.json())
            .then(function (data){
                var html = setUpJoke(data);
                div.innerHTML = html;
    })
}

function getJokeById(){
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/" + jokesInput.value;
    var promise = fetch(url, conf);
    
    promise.then(res => res.json())
            .then(function (data){
                var html = setUpJoke(data);
                div.innerHTML = html;
              
    })
}

function getAllJokes() {
//    event.preventDefault();

    var conf = {method: "get"};
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var html = generateTable(data);
                div.innerHTML = html;

            });
}

function setUpJoke(data){
    var html = "<h2>" + data.title + "</h2>" + "<p>" + data.joke + "</p>";
    html += "<br><br><a href=\"jokes.html\">Back to jokes</a>";
    return html;
    
}

function generateTable(data) {
    var newData = data.map(function (j) {
        return "<tr><td>" + j.id + "</td>" + "<td>" + j.title + "</td>" +
                "<td>" + j.type + "</td></tr>";
    });
    return "<table><tr><th>#</th><th>Title</th><th>Type</th><tr>" + newData.join("") + "</table>";


}