$(document).ready(function(){
    insertOptionsInForm("http://localhost:8080//v1/api/invoice/type", "type", " Failed to load invoice types");
    insertOptionsInForm("http://localhost:8080//v1/api/contractor", "contractor", " Failed to load contractors");
    insertOptionsInForm("http://localhost:8080//v1/api/product", "products", " Failed to load products");
    insertOptionsInForm("http://localhost:8080//v1/api/service", "services", " Failed to load services");
    $('#addInvoiceForm select[name=type]').on('change', hideInvoiceFormElement);
    $("#listInvoicesForm").on('submit', submitListInvoicesForm);
    $("#listInvoicesForm").on('submit', clearResultsDivs);
    $('#findInvoiceForm').on('submit', submitFindInvoiceForm);
    $('#findInvoiceForm').on('submit', clearResultsDivs);
    $('#addInvoiceForm').on('submit', submitAddInvoiceForm);
    $('#addInvoiceForm').on('submit', clearResultsDivs);

});

function insertOptionsInForm(url, selectName, errorMessage){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "GET",
        url: url,
        success: function (data) {
            addDataToFormSelects(data, selectName);
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status + errorMessage);
        }
    });
}

function addDataToFormSelects(data, selectName){
    $.each(data, function(index) {
        var value;
        var text;
        if (selectName == "type"){
            value = data[index];
            text = data[index];
        } else{
            value = data[index].id;
            text = data[index].name;
        }
        $('select[name=' + selectName + ']').append($("<option/>", {
            value: value,
            text: text,
        }));
    });
}

function getTypeOptions(){
    var typeOptions = $('#addInvoiceForm select[name=type] option');

    var values = $.map(typeOptions ,function(option) {
        if (option.value != "Type"){
            return option.value.toLowerCase() + "s";
        }
    });
    return values;
}

function hideInvoiceFormElement(){
    var typeOptions = getTypeOptions();

    var currentValue = this.value.toLowerCase() + "s";

    $.each(typeOptions, function(index) {
        var selectName = typeOptions[index];
        if (selectName == currentValue){
            $('#addInvoiceForm select[name=' + selectName + ']').removeClass("d-none");
            $('#addInvoiceForm select[name=' + selectName + ']').attr("required", true);
        } else{
            $('#addInvoiceForm select[name=' + selectName + ']').addClass("d-none");
            $('#addInvoiceForm select[name=' + selectName + ']').attr("required", false);
        }
    });
}

function submitFindInvoiceForm(e){
    e.preventDefault();
    var formData = getFormData(this);
    findInvoice(formData);
}

function submitAddInvoiceForm(e){
    e.preventDefault();
    var formData = getFormDataWithMultipleOptions(this, ["contractor", "services", "products"]);
    console.log(formData);
    addInvoice(formData);
    this.reset();
}

function submitListInvoicesForm(e){
    e.preventDefault();
    var formData = getFormData(this);
    listInvoices(formData);
}

function findInvoice(formData){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "GET",
        url: "http://localhost:8080//v1/api/invoice/" + formData['id'],
        success: function (data) {
            var newRow = "<tr><td>" + data.id + "</td><td>" + data.dataOfIssue + "</td><td>" + data.deadline + 
                "</td><td>" + data.paid + "</td><td>" + data.amount + "</td><td>" + data.type + "</td><td>" + data.contractor.name + 
                "</td><td>" + generateStringItemsList(data.products) + "</td><td>" + generateStringItemsList(data.services) + "</td></tr>";
            displayResult(newRow);
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}


function generateStringItemsList(array, type){
    if (array.length == 0){
        return "Not available";
    }
    var str = "";
    $.each(array, function(index){
        str += array[index].name + ", ";
    })
    return str;
}


function listInvoices(formData){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "GET",
        url: "http://localhost:8080/v1/api/invoice/type/" + formData['type'],
        success: function (data) {
            if (data.length == 0){
                displayError("No invoice found");
                return;
            }
            $.each(data, function(index) {
                var newRow = "<tr><td>" + data[index].id + "</td><td>" + data[index].dataOfIssue + "</td><td>" + data[index].deadline + 
                    "</td><td>" + data[index].paid + "</td><td>" + data[index].amount + "</td><td>" + data[index].type + "</td><td>" + data[index].contractor.name + 
                    "</td><td>" + generateStringItemsList(data[index].products) + "</td><td>" + generateStringItemsList(data[index].services) + "</td></tr>";
                displayResult(newRow);
            });
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}

function addInvoice(formData){
    $.ajax({
        headers: {          
            Accept: "application/json",         
            "Content-Type": "application/json",
        },     
        type: "POST",
        url: "http://localhost:8080/v1/api/invoice/",
        data: JSON.stringify(formData),
        success: function (data) {
            var newRow = "<tr><td>" + data.id + "</td><td>" + data.dataOfIssue + "</td><td>" + data.deadline + 
                "</td><td>" + data.paid + "</td><td>" + data.amount + "</td><td>" + data.type + "</td><td>" + data.contractor.name + 
                "</td><td>" + generateStringItemsList(data.products) + "</td><td>" + generateStringItemsList(data.services) + "</td></tr>";
            displayResult(newRow);
        },
        error: function (xhr, ajaxOptions, thorwnError) {
            displayError("Code: " + xhr.status);
        }
    });
}