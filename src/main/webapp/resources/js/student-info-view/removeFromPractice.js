$(document).ready(function() {
    var removeTable =  $('#removeFromPracticeTable').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": false,
        "autoWidth": false,
        "colReorder": true
    });

    var removeFromPracticeList = [];

    $('#removeFromPracticeTable tbody').on( 'click', 'tr', function () {
        removeFromPracticeList.push($(this).data("toggle"));
        $(this).toggleClass('selected');
    } );


    $("#deleteStudentFromPracticeForm").submit(function(event) {
        callDeleteAlert();
        event.preventDefault();
        removeFromPractice(removeFromPracticeList);
    });
} );

function removeFromPractice(removeFromPracticeList){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: window.location + "/removeFromPractice",
        data: JSON.stringify(removeFromPracticeList),
        dataType: 'json',
        success: function (result) {
            $('#deleteStudentFromPracticeModal').modal('hide');
            // callSuccessAlert('You removed students from practice!');
            console.log(result);
        },
        error: function (e) {
            alert("Error!");
            console.log("ERROR: ", e);
        }
    });
}