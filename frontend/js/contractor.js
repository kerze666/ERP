$(document).ready(function(){
    $("#listAllContractors").on('click', listAllContractors);
    $("#listAllContractors").on('click', clearResultsDivs);
    $('#findContractorForm').on('submit', submitFindContractorForm);
    $('#findContractorForm').on('submit', clearResultsDivs);
    $('#addContractorForm').on('submit', submitAddContractorForm);
    $('#addContractorForm').on('submit', clearResultsDivs);

});

function submitFindContractorForm(e){
    e.preventDefault();
    var formData = getFormData(this);
    findContractor(formData);
}

function submitAddContractorForm(e){
    e.preventDefault();
    var formData = getFormData(this);
    addContractor(formData);
}

function findContractor(formData){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "GET",
        url: "http://localhost:8080/v1/api/contractor/" + formData['id'],
        success: function (data) {
            var newRow = "<tr><td>" + data.id + "</td><td>" + data.name + "</td></tr>";
            displayResult(newRow);
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}

function listAllContractors(){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "GET",
        url: "http://localhost:8080/v1/api/contractor",
        success: function (data) {
            if (data.length == 0){
                displayError("No contractor found");
                return;
            }
            $.each(data, function(index) {
                var newRow = "<tr><td>" + data[index].id + "</td><td>" + data[index].name + "</td></tr>";
                displayResult(newRow);
            });
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}

function addContractor(formData){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "POST",
        url: "http://localhost:8080/v1/api/contractor",
        data: JSON.stringify(formData),
        success: function (data) {
            var newRow = "<tr><td>" + data.id + "</td><td>" + data.name + "</td></tr>";
            displayResult(newRow);
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}