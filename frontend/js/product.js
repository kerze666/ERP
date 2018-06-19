$(document).ready(function(){
    $("#listAllProducts").on('click', listAllProducts);
    $("#listAllProducts").on('click', clearResultsDivs);
    $('#findProductForm').on('submit', submitFindProductForm);
    $('#findProductForm').on('submit', clearResultsDivs);
    $('#addProductForm').on('submit', submitAddProductForm);
    $('#addProductForm').on('submit', clearResultsDivs);

});

function submitFindProductForm(e){
    e.preventDefault();
    var formData = getFormData(this);
    findProduct(formData);
}

function submitAddProductForm(e){
    e.preventDefault();
    var formData = getFormData(this);
    addProduct(formData);
}

function findProduct(formData){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "GET",
        url: "http://localhost:8080//v1/api/product/" + formData['id'],
        success: function (data) {
            var newRow = "<tr><td>" + data.id + "</td><td>" + data.name + "</td><td>" + data.cost + "</td><td>" + data.purchaseOfData + "</td><td>" + data.warranty + "</td></tr>";
            displayResult(newRow);
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}

function listAllProducts(){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "GET",
        url: "http://localhost:8080/v1/api/product",
        success: function (data) {
            if (data.length == 0){
                displayError("No product found");
                return;
            }
            $.each(data, function(index) {
                var newRow = "<tr><td>" + data[index].id + "</td><td>" + data[index].name + "</td><td>" + data[index].cost + "</td><td>" + data[index].purchaseOfData + "</td><td>" + data[index].warranty + "</td></tr>";
                displayResult(newRow);
            });
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}

function addProduct(formData){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "POST",
        url: "http://localhost:8080/v1/api/product",
        data: JSON.stringify(formData),
        success: function (data) {
            var newRow = "<tr><td>" + data.id + "</td><td>" + data.name + "</td><td>" + data.cost + "</td><td>" + data.purchaseOfData + "</td><td>" + data.warranty + "</td></tr>";
            displayResult(newRow);
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}