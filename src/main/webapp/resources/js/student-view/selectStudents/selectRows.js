$(document).ready(function() {
    var table =  $('#example1').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": false,
        "autoWidth": false,
        "colReorder": true
    });

    var studentList = [];

    $('#example1 tbody').on( 'click', 'tr', function () {
        studentList.push($(this).data("toggle"));
        $(this).toggleClass('selected');
    } );

    $('#assignButton').click( function () {
        alert( studentList );
        // alert( table.rows('.selected').data().length +' row(s) selected' );
    } );


    $("#assignOnPracticeForm").submit(function(event) {
        event.preventDefault();
        ajaxPost(studentList);
    });
} );

function ajaxPost(studentList){

    // PREPARE FORM DATA

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: window.location + "/assignOnPractice",
        data: JSON.stringify(studentList),
        dataType: 'json',
        success: function (result) {
            $('#assignOnPracticeModal').modal('hide');
            callSuccessAlert('You assigned students!');
            console.log(result);
        },
        error: function (e) {
            alert("Error!");
            console.log("ERROR: ", e);
        }
    });

    // Reset FormData after Posting
    // resetData();
}