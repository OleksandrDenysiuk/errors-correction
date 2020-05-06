$(function () {
    var typeCrc = $( "#typeCRC" );

    getCrcCommand(typeCrc.val());

    typeCrc.change(function() {
        getCrcCommand($(this).val());
    });
});

function getCrcCommand(id) {
    $.getJSON('http://localhost:8080/CrcCommand/' + id, function (response) {
        console.log(response);
        $("#polynomial").val(response.polynomial);
        $("#initialValue").val(response.initialValue);
        $("#finalXorValue").val(response.finalXorValue);
    });
}


