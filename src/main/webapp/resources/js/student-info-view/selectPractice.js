$(document).ready(function() {
    var assignTable =  $('#setOnPracticeTable').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": false,
        "autoWidth": false,
        "colReorder": true
    });

    var assignPracticeList = [];

    $('#setOnPracticeTable tbody').on( 'click', 'tr', function () {
        assignPracticeList.push($(this).data("toggle"));
        $(this).toggleClass('selected');
    } );


    $("#studentOnPracticeForm").submit(function(event) {
        event.preventDefault();
        setOnPractice(assignPracticeList);
    });
} );

function setOnPractice(practiceList){

    // PREPARE FORM DATA

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: window.location + "/assignOnPractice",
        data: JSON.stringify(practiceList),
        dataType: 'json',
        success: function (result) {
            $('#studentOnPracticeModal').modal('hide');
            callSuccessAlert('You assigned students!');
            console.log(result);
        },
        error: function (e) {
            alert("Error!");
            console.log("ERROR: ", e);
        }
    });;
}