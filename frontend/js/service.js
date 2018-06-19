$(document).ready(function(){
    $("#listAllServices").on('click', listAllServices);
    $("#listAllServices").on('click', clearResultsDivs);
    $('#findServiceForm').on('submit', submitFindServiceForm);
    $('#findServiceForm').on('submit', clearResultsDivs);
    $('#addServiceForm').on('submit', submitAddServiceForm);
    $('#addServiceForm').on('submit', clearResultsDivs);

});

function submitFindServiceForm(e){
    e.preventDefault();
    var formData = getFormData(this);
    findService(formData);
}

function submitAddServiceForm(e){
    e.preventDefault();
    var formData = getFormData(this);
    addService(formData);
}

function findService(formData){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "GET",
        url: "http://localhost:8080//v1/api/service/" + formData['id'],
        success: function (data) {
            console.log(data);
            var newRow = "<tr><td>" + data.id + "</td><td>" + data.name + "</td><td>" + data.price + "</td></tr>";
            displayResult(newRow);
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}

function listAllServices(){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "GET",
        url: "http://localhost:8080/v1/api/service",
        success: function (data) {
            if (data.length == 0){
                displayError("No service found");
                return;
            }
            $.each(data, function(index) {
                var newRow = "<tr><td>" + data[index].id + "</td><td>" + data[index].name + "</td><td>" + data[index].price + "</td></tr>";
                displayResult(newRow);
            });
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}

function addService(formData){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "POST",
        url: "http://localhost:8080/v1/api/service",
        data: JSON.stringify(formData),
        success: function (data) {
            var newRow = "<tr><td>" + data.id + "</td><td>" + data.name + "</td><td>" + data.price + "</td></tr>";
            displayResult(newRow);
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}