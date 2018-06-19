function displayResult(newRow){
    $("#resultTable > tbody").append(newRow);
    $("#result").removeClass("d-none");
}

function displayError(message){
    $("#errorContent").text(message);
    $("#error").removeClass("d-none");
}

function clearResultsDivs(){
    $("#resultTable > tbody").empty();
    $("#error").addClass("d-none");
    $("#result").addClass("d-none");
}

function getFormData(form){
    var formData = $(form).serializeArray();
    var formattedFormData = {};
    var multipleSelects = {};
    $.each(formData, function(i, v) {
        formattedFormData[v.name] = v.value;
    });
    $.each(multipleSelects, function(key, value) {
        formattedFormData[key] = value;
    });
    return formattedFormData;
}

function getFormDataWithMultipleOptions(form, convertToObject){
    var formData = $(form).serializeArray();
    var formattedFormData = {};
    var multipleSelects = {};
    $.each(formData, function(i, v) {
        if (convertToObject.includes(v.name)){
            if (multipleSelects[v.name] === undefined){
                v.name === "contractor"? multipleSelects[v.name] = {"id": v.value} : multipleSelects[v.name] = [{"id": v.value}];
            } else{
                multipleSelects[v.name].push({"id": v.value});
            }
        } else{
            formattedFormData[v.name] = v.value;
        }
    });
    $.each(multipleSelects, function(key, value) {
        formattedFormData[key] = value;
    });
    return formattedFormData;
}