
var table = document.getElementById("studentTable");
var btnReload = document.getElementById("btnReload");



let dom = "https://cphbusines.dk/CAfall/api/groupmembers";



onload = getAllMembers();
btnReload.addEventListener("click",getAllMembers);


function getAllMembers() {
//    event.preventDefault();

    var conf = {method: "get"};
    var url = dom + "/all";

    var promise = fetch(url, conf);

    promise.then(res => res.json())
            .then(function (data) {
                var html = generateTable(data);
                table.innerHTML = html;

            });
}


function generateTable(data) {
    var newData = data.map(function (m) {
        return "<tr><td>" + m.name + "</td>" + "<td>" + m.studentId + "</td>" +
                "<td>" + m.color + "</td></tr>";
    });
    return "<tr><th>Name</th><th>Student ID</th><th>Color</th><tr>" + newData.join("");


}