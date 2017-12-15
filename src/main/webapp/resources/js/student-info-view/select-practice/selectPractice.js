$(document).ready(function() {
    var table =  $('#setOnPracticeTable').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": false,
        "autoWidth": false,
        "colReorder": true
    });

    var studentList = [];

    $('#setOnPracticeTable tbody').on( 'click', 'tr', function () {
        studentList.push($(this).data("toggle"));
        $(this).toggleClass('selected');
    } );

    // $('#assignButton').click( function () {
        // alert( studentList );
        // alert( table.rows('.selected').data().length +' row(s) selected' );
    // } );


    $("#studentOnPracticeForm").submit(function(event) {
        event.preventDefault();
        ajaxPost(studentList);
    });
} );

function ajaxPost(studentList){

    // PREPARE FORM DATA

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: window.location + "/setStudentOnPractice",
        data: JSON.stringify(studentList),
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
    });

    // Reset FormData after Posting
    // resetData();
}