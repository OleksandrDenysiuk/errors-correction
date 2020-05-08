$(function () {
    var typeCrc = $("#typeCRC");

    setDataForm(typeCrc.val());

    typeCrc.change(function() {
        setDataForm($(this).val());
    });

    $('#predefinedCrc').click(function () {
        setDataForm(typeCrc.val());
    });

    $('#customCrc').click(function () {
        $('#name').val("CustomCrc");
    });

    $('#submitBtn').click(function () {
        $("#customCrcParameters").prop("disabled", false);
    });

});

function setDataForm(id) {
    $.getJSON('http://localhost:8080/CrcCommand/' + id, function (response) {

        var bitLength = Number(response.bitLength);

        $("#name").val(response.name);

        if(bitLength === 16){
            $("#bit16").prop("checked", true);
        }else if(bitLength === 32){
            $("#bit32").prop("checked", true);
        }

        var inputReflected = response.inputReflected;
        if(inputReflected === true){
            $("#inputReflected").prop("checked", true);
        }else {
            $("#inputReflected").prop("checked", false);
        }

        var resultReflected = response.resultReflected;
        if(resultReflected === true){
            $("#resultReflected").prop("checked", true);
        }else{
            $("#resultReflected").prop("checked", false);
        }

        $("#polynomial").val(response.polynomial);
        $("#initialValue").val(response.initialValue);
        $("#finalXorValue").val(response.finalXorValue);

    });
}

