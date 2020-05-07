$("#predefinedCrc").click(function () {
    $("#customCrcParameters").prop("disabled", true);
    $("#listCrcTypes").prop("disabled", false);
});
$("#customCrc").click(function () {
    $("#listCrcTypes").prop("disabled", true);
    $("#customCrcParameters").prop("disabled", false);
});