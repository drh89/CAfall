

var table = document.getElementById("table");
var btnAll = document.getElementById("btnAll");
var btnFilter = document.getElementById("btnFilter");
var input = document.getElementById("input");
var btnPrice = document.getElementById("btnPrice");
var inputPrice = document.getElementById("inputPrice");
var dom = "https://cphbusines.dk/CAfall/api/cars";




btnAll.addEventListener("click", getAllCars);
btnFilter.addEventListener("click", getCarsByMake);
btnPrice.addEventListener("click", getCarsByPrice);

table.addEventListener("click", function (e) {
    var target = e.target;
    if (target.id === "make") {
        sortByMake();
    }
    if (target.id === "model") {
        sortByModel();
    }
    if (target.id === "registration") {
        sortByRegistrationDate();
    }
    if (target.id === "modelyear") {
        sortByModelYear();
    }
    if (target.id === "horsepower") {
        sortByHorsepower();
    }
    if (target.id === "mileage") {
        sortByMileage();
    }
    if (target.id === "doors") {
        sortByDoors();
    }
    if (target.id === "price") {
        sortByPrice();
    }
});


function getCarsByPrice() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";
    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.filter(c => c.price <= inputPrice.value);
                newData.sort(function(a,b){
                   return sortMakeModelPrice(a,b); 
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}


// "Filter" SQL
function getCarsByMake() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/makename/" + input.value;
    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return sortMakeModelPrice(a, b);
                });
                var html = generateTable(newData);
                table.innerHTML = html;

            });

}



function sortByHorsepower() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";
    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.horsepower - b.horsepower;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });

}

function sortByDoors() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";


    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.doors - b.doors;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });

}
function sortByMileage() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";


    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.mileage - b.mileage;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });

}

function sortByRegistrationDate() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return compareProperty(a.registrationDate, b.registrationDate);
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}


function sortByModelYear() {
    event.preventDefault();
    var conf = {method: "get"};
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.modelYear - b.modelYear;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}

function sortByMake() {
    event.preventDefault();

    var conf = {method: "get"};
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return sortMakeModelPrice(a, b);
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}


function sortByModel() {
    event.preventDefault();

    var conf = {method: "get"};
    var url = dom + "/all";


    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return compareProperty(a.model, b.model);
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}



function sortByPrice() {
    event.preventDefault();

    var conf = {method: "get"};
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var newData = data.sort(function (a, b) {
                    return a.price - b.price;
                });
                var html = generateTable(newData);
                table.innerHTML = html;
            });
}


function getAllCars() {
    event.preventDefault();

    var conf = {method: "get"};
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var html = generateTable(data);
                table.innerHTML = html;

            });
}


function compareProperty(a, b) {
    return a.localeCompare(b);
}

function sortMakeModelPrice(a, b) {
    return compareProperty(a.make, b.make) || compareProperty(a.model, b.model) || a.price - b.price;
}



function generateTable(data) {
    var newData = data.map(function (c) {
        return "<tr><td>" + c.make + "</td>" + "<td>" + c.model + "</td>" +
                "<td>" + c.registrationDate + "</td>" + "<td>" + c.modelYear + "</td>" +
                "<td>" + c.horsepower + "</td>" + "<td>" + c.mileage + "</td>" +
                "<td>" + c.doors + "</td>" + "<td>" + c.price + "</td></tr>";
    });
    return "<tr><th id = \"make\">Make</th>" + "<th id = \"model\">Model</th>" + "<th id = \"registration\">Registration Date</th>" +
            "<th id = \"modelyear\">Model Year</th>" + "<th id = \"horsepower\">Horsepower</th>" +
            "<th id = \"mileage\">Mileage</th>" + "<th id = \"doors\">Doors</th>" + "<th id = \"price\">Price</th></tr>" + newData.join("");


}